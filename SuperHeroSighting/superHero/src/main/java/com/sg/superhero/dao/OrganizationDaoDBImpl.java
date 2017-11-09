/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superhero.dao;

import static com.sg.superhero.dao.PrepareStatement.*;
import static com.sg.superhero.dao.RowMapper.*;
import com.sg.superhero.model.Location;
import com.sg.superhero.model.Organization;
import com.sg.superhero.model.SuperPerson;
import java.util.List;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author ritheenhep
 */
public class OrganizationDaoDBImpl implements OrganizationDao {

    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void addOrganization(Organization organization) {
        jdbcTemplate.update(SQL_INSERT_ORGANIZATION,
                organization.getName(),
                organization.getPhone(),
                organization.getDesciption(),
                organization.getLocation().getLocationId());

        organization.setOrganizationId(jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class));
        inserSupertPersonOrgaization(organization);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void deleteOrganization(int organizationId) {
        jdbcTemplate.update(SQL_DELETE_ORGANIZATION_SUPER_PERSON, organizationId);
        jdbcTemplate.update(SQL_DELETE_ORGANIZATION, organizationId);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void updateOrganization(Organization organization) {
        jdbcTemplate.update(SQL_UPDATE_ORGANIZATION,
                organization.getName(),
                organization.getPhone(),
                organization.getDesciption(),
                organization.getLocation().getLocationId(),
                organization.getOrganizationId());
        jdbcTemplate.update(SQL_DELETE_ORGANIZATION_SUPER_PERSON, organization.getOrganizationId());
        inserSupertPersonOrgaization(organization);
    }

    @Override
    public Organization getOrganizationById(int organizationId) {
        try {
            Organization organization = jdbcTemplate.queryForObject(SQL_SELECT_ORGANIZATION_BY_ID, new OrganizationMapper(), organizationId);
            organization.setMember(findSuperPersonFoOrganization(organization));
            organization.setLocation(findLocationForOrganization(organization));
            return organization;
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<Organization> getAllSuperPersonByOrganization(SuperPerson superPersonId) {
        List<Organization> superPersonList
                = jdbcTemplate.query(SQL_SELECT_ALL_SUPER_PERSON_BY_ORGANIZATION,
                        new OrganizationMapper(), superPersonId);
        return associateLocationAndOrganizationAndSuperPerson(superPersonList);
    }

    @Override
    public Location getLocationByOrganization(Location location) {

        return jdbcTemplate.queryForObject(SQL_SELECT_ALL_LOCATION_BY_ORGANIZATION,
                new LocationMapper(), location.getLocationId());
    }

    @Override
    public List<Organization> getAllOrganization() {
        List<Organization> allOrganizaton = jdbcTemplate.query(SQL_SELECT_ALL_ORGANIZATION, new OrganizationMapper());
        return associateLocationAndOrganizationAndSuperPerson(allOrganizaton);
    }

    //helper Method
    public void inserSupertPersonOrgaization(Organization organization) {
        final int organizationId = organization.getOrganizationId();
        final List<SuperPerson> superPerson = organization.getMember();
        for (SuperPerson currentSuperPerson : superPerson) {
            jdbcTemplate.update(SQL_INSERT_SUPER_PERSON_ORGANIZATION, currentSuperPerson.getSuperPersonId(), organizationId);
        }
    }

 

    public List<SuperPerson> findSuperPersonFoOrganization(Organization organization) {
        return jdbcTemplate.query(SQL_SELECT_ALL_SUPER_PERSON_BY_ORGANIZATION,
                new SuperPersonMapper(), organization.getOrganizationId());
    }

    public Location findLocationForOrganization(Organization organization) {
        return jdbcTemplate.queryForObject(SQL_SELECT_ALL_LOCATION_BY_ORGANIZATION,
                new LocationMapper(), organization.getOrganizationId());

    }

    private List<Organization> associateLocationAndOrganizationAndSuperPerson(List<Organization> organizationList) {
        for (Organization currentOrganization : organizationList) {
            currentOrganization.setMember(findSuperPersonFoOrganization(currentOrganization));
            currentOrganization.setLocation(findLocationForOrganization(currentOrganization));
        }
        return organizationList;
    }
}
