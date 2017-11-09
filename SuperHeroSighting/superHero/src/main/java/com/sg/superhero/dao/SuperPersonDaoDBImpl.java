/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superhero.dao;

import static com.sg.superhero.dao.RowMapper.*;
import com.sg.superhero.model.Location;
import com.sg.superhero.model.Organization;
import com.sg.superhero.model.Sighting;
import com.sg.superhero.model.SuperPerson;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author ritheenhep
 */
public class SuperPersonDaoDBImpl implements SuperPersonDao {

    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
//author related prepare statement
//super-person
    private static final String SQL_INSERT_SUPER_PERSON
            = "insert into super_person "
            + "(name, super_power, super_person_side, description) "
            + "values(?, ?, ?, ?)";

    private static final String SQL_UPDATE_SUPER_PERSON
            = "update super_person set name = ?, super_power = ? , super_person_side = ?, description= ? "
            + "where super_person_id = ?";

    private static final String SQL_DELETE_SUPER_PERSON
            = "delete from super_person where super_person_id = ?";

    private static final String SQL_SELECT_SUPER_PERSON
            = "select * from super_person where super_person_id =?";

    private static final String SQL_SELECT_ALL_SUPER_PERSON
            = "select * from super_person";

    private static final String SQL_SELECT_SUPER_PERSON_BY_SIDE_ID//AME
            = "select * from super_person sp"
            + "where sp.super_person_side= ?";

    private static final String SQL_SELECT_ALL_SUPER_PERSON_BY_DATE
            = "select * from super_person sp"
            + "inner join sighting s "
            + "on sp.super_person_id= s.sighting_id"
            + "where s.date = ? ";

//location
    private static final String SQL_SELECT_ALL_LOCATION_BY_SUPER_PERSON_ID
            = "select * "
            + "from super_person s "
            + "inner join  super_person_sighting sps "
            + "on s.super_person_id = sps.super_person_id "
            + "inner join sighting "
            + "on sps.sighting_id = sighting.sighting_id "
            + "inner join location l "
            + "on l.location_id = ? ";

    private static final String SQL_INSERT_LOCATION
            = "insert into location "
            + "(location_name , street, city, state, zip, latitude,longitude, description )"
            + "values(?,?,?,?,?,?,?,?)";

    private static final String SQL_SELECT_LOCATION_BY_ID
            = "select * from location where location_id = ?";
    //sighting
    private static final String SQL_SELECT_ALL_SUPER_PERSON_BY_LOCATION
            = "select distinct sp.* "
            + " from location l "
            + " inner join sighting s on l.location_id = s.location_id "
            + " inner join super_person_sighting sps on s.Sighting_id = sps.Sighting_id "
            + " inner join super_person sp on sps.super_person_id = sp.Super_Person_id "
            + "  where l.location_id = ? ";

    private static final String SQL_SELECT_SIGHTING_BY_ID
            = "select * from sighting where sighting_id = ?";

    private static final String SQL_SELECT_ALL_SIGHTING
            = "select * from sighting";

    //SUPER-ORGANIZATION BRIDGE 
    private static final String SQL_DELETE_SUPER_PERSON_ORGANIZATION
            = "delete from super_person_organization where super_person_id = ?";
    private static final String SQL_DELETE_ORGANIZATION_SUPER
            = "delete from super_person_organization where organization_id = ? ";

    private static final String SQL_INSERT_SUPER_PERSON_ORGANIZATION
            = "insert into super_person_organization "
            + "(super_person_id, organization_id)"
            + "values (?, ?)";

    private static final String SQL_SELECT_ALL_ORGANIZATION
            = "select * from organization";

    private static final String SQL_SELECT_ALL_SUPER_PERSON_ORGANIZATION
            = "select * from organization o "
            + "join super_person_organization spo "
            + "on spo.organization_id = o.organization_id "
            + "where spo.super_person_id = ? ";

    //SUPER-SIGHTING BRIDGE TABLE
    private static final String SQL_INSERT_SUPER_PERSON_SIGHTING
            = "insert into super_person_sighting (super_person_id, sighting_id)value(?,?)";

    private static final String SQL_DELETE_SUPER_PERSON_SIGHTING
            = "delete from super_person_sighting where super_person_id = ?";

    private static final String SQL_SELECT_ALL_SUPER_PERSON_SIGHTING
            = "select * from sighting s "
            + "inner join super_person_sighting sps "
            + "on sps.sighting_id = s.sighting_id "
            + "where sps.sighting_id = ? ";

    //helper method
    private void insertSuperPersonSighting(SuperPerson superPerson) {
        final int superPersonId = superPerson.getSuperPersonId();
        final List<Sighting> sighting = superPerson.getSightings();

        for (Sighting currentSighting : sighting) {
            jdbcTemplate.update(SQL_INSERT_SUPER_PERSON_SIGHTING,
                    superPersonId, currentSighting.getSightingId());
        }
    }

    private List<Sighting> findSightingForSuperPerson(SuperPerson superPerson) {

        return jdbcTemplate.query(SQL_SELECT_SIGHTING_BY_ID,
                new SightingMapper(), superPerson.getSightings());

    }

    private void insertSuperPersonOrganization(SuperPerson superPerson) {
        final int superPersonId = superPerson.getSuperPersonId();
        final List<Organization> org = superPerson.getOrganizations();
        for (Organization currentOrg : org) {
            jdbcTemplate.update(SQL_INSERT_SUPER_PERSON_ORGANIZATION,
                    superPersonId, currentOrg.getOrganizationId());
        }
    }
    
    private List<Organization> findOrganizationForSuperPerson(SuperPerson superPerson) {

        return jdbcTemplate.query(SQL_SELECT_ALL_SUPER_PERSON_ORGANIZATION,//check query
                new OrganizationMapper(), superPerson.getSuperPersonId());
    }
    private List<SuperPerson> associateSightingAndOrganizationForSuperPerson(List<SuperPerson> personList) {
        for (SuperPerson currentPerson : personList) {
            currentPerson.setSightings(findSightingForSuperPerson(currentPerson));
            currentPerson.setOrganizations(findOrganizationForSuperPerson(currentPerson));
        }
        return personList;
    }
//end of helper method. 
//--------------------  

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void addSuperPerson(SuperPerson person) {
        jdbcTemplate.update(SQL_INSERT_SUPER_PERSON,
                person.getName(),
                person.getPower(),
                person.isSuperHeroSide(),
                person.getDescription());

        person.setSuperPersonId(jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class));
        insertSuperPersonOrganization(person);
        insertSuperPersonSighting(person);

    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void deleteSuperPerson(int personId) {
        jdbcTemplate.update(SQL_DELETE_SUPER_PERSON_ORGANIZATION, personId);
        jdbcTemplate.update(SQL_DELETE_SUPER_PERSON_SIGHTING, personId);
        jdbcTemplate.update(SQL_DELETE_SUPER_PERSON, personId);

    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void updateSuperPerson(SuperPerson person) {
        jdbcTemplate.update(SQL_UPDATE_SUPER_PERSON,
                person.getName(),
                person.getPower(),
                person.isSuperHeroSide(),
                person.getDescription(),
                person.getSuperPersonId());
         jdbcTemplate.update(SQL_DELETE_SUPER_PERSON_ORGANIZATION, person.getSuperPersonId());
         jdbcTemplate.update(SQL_DELETE_ORGANIZATION_SUPER, person.getSuperPersonId());
        insertSuperPersonOrganization(person);
        insertSuperPersonSighting(person);
    }

    @Override
    public SuperPerson getSuperPersonById(int superPersonId) {
        try {
            SuperPerson superPerson = jdbcTemplate.queryForObject(SQL_SELECT_SUPER_PERSON,
                    new SuperPersonMapper(), superPersonId);
            superPerson.setSightings(findSightingForSuperPerson(superPerson));
            superPerson.setOrganizations(findOrganizationForSuperPerson(superPerson));

            return superPerson;
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<SuperPerson> getAllSuperPerson() {
        List<SuperPerson> superPerson = jdbcTemplate.query(SQL_SELECT_ALL_SUPER_PERSON,
                new SuperPersonMapper());
        return associateSightingAndOrganizationForSuperPerson(superPerson);
    }

    @Override
    public List<SuperPerson> getAllSuperPersonByLocation(Location locationId) {
        List<SuperPerson> allSuperPerson = jdbcTemplate.query(SQL_SELECT_ALL_SUPER_PERSON_BY_LOCATION,
                new SuperPersonMapper(), locationId.getLocationId());
        return associateSightingAndOrganizationForSuperPerson(allSuperPerson);
    }

    @Override
    public List<SuperPerson> getAllOranizationBySuperPerson(Organization orgId) {
        List<SuperPerson> allOrganizatonBySuperPerson = jdbcTemplate.query(SQL_SELECT_ALL_ORGANIZATION,
                new SuperPersonMapper(), orgId);
        return associateSightingAndOrganizationForSuperPerson(allOrganizatonBySuperPerson);
    }

    @Override
    public List<SuperPerson> getAllSuperPersonSide(boolean superHero) {
        try {
            List<SuperPerson> allSuperPersonSide = jdbcTemplate.query(SQL_SELECT_SUPER_PERSON_BY_SIDE_ID,
                    new SuperPersonMapper(), superHero);
            return associateSightingAndOrganizationForSuperPerson(allSuperPersonSide);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }

    }

    @Override
    public List<SuperPerson> getAllSuperPersonByDate(String date) {
        try {
            List<SuperPerson> allSuperPersonByDate = jdbcTemplate.query(SQL_SELECT_ALL_SUPER_PERSON_BY_DATE,
                    new SuperPersonMapper(), date);
            return associateSightingAndOrganizationForSuperPerson(allSuperPersonByDate);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }
 
}
