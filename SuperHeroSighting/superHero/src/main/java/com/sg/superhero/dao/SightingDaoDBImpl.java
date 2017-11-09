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
import com.sg.superhero.model.Sighting;
import com.sg.superhero.model.SuperPerson;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
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
public class SightingDaoDBImpl implements SightingDao {

    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void addSighting(Sighting sighting) {
        jdbcTemplate.update(SQL_INSERT_SIGHTING,
                sighting.getDate().toString(),
                sighting.getDescription(),
                sighting.getLocation().getLocationId());
        sighting.setSightingId(jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class));
        insertSupePersonForSighting(sighting);
       // insertLocationForSighting(sighting);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void deletSigting(int sightingId) {
        jdbcTemplate.update(SQL_DELETE_SIGHTING_SUPER_PERSON, sightingId);
        jdbcTemplate.update(SQL_DELETE_LOCATION_FROM_SIGHTING, sightingId);
        jdbcTemplate.update(SQL_DELETE_SIGHTING, sightingId);

    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void updateSighting(Sighting sighting) {
        jdbcTemplate.update(SQL_UPDATE_SIGHTING,
                sighting.getDate().toString(),
                sighting.getDescription(),
                sighting.getLocation().getLocationId(),
                sighting.getSightingId());
        //   jdbcTemplate.update(SQL_DELETE_LOCATION_FROM_SIGHTING, sighting.getLocation().getLocationId());
        jdbcTemplate.update(SQL_DELETE_SIGHTING_SUPER_PERSON, sighting.getSightingId());
        insertSupePersonForSighting(sighting);
    }

    @Override
    public Sighting getSightingById(int sightingId) {

        try {
            Sighting sighting = jdbcTemplate.queryForObject(SLQ_SELECT_SIGHTING_BY_ID, new SightingMapper(), sightingId);
            sighting.setSuperPerson(findSuperPersonForSighting(sighting));
            sighting.setLocation(findLocationForSighting(sighting));
            return sighting;
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<Sighting> getSightingByDate(LocalDate date) {

        List<Sighting> sightingByDate = jdbcTemplate.query(SQL_SELECT_ALL_SIGHTING_BY_DATE, new SightingMapper(), date.toString());
        return associateSightingAndLocationForSuperPerson(sightingByDate);
    }

    @Override
    public List<Sighting> getAllSighting() {
        List<Sighting> allSightings = jdbcTemplate.query(SQL_SELECT_ALL_SIGHTING,
                new SightingMapper());
        return associateSightingAndLocationForSuperPerson(allSightings);
    }

    @Override
    public List<Sighting> getLatestSighting() {
        List<Sighting> latestTen = jdbcTemplate.query(SLQ_GET_LATEST_10,
                new SightingMapper());
        return associateSightingAndLocationForSuperPerson(latestTen);
    }

    //helper Methods
    private void insertSupePersonForSighting(Sighting sighting) {
        final int sightingId = sighting.getSightingId();
        final List<SuperPerson> superPerson = sighting.getSuperPerson();

        for (SuperPerson currentPerson : superPerson) {
            jdbcTemplate.update(SQL_INSERT_SUPER_PERSON_SIGHTING,
                    currentPerson.getSuperPersonId(), sightingId);
        }
    }
//    private void insertLocationForSighting(Sighting sighting){
//        final int sightingId = sighting.getSightingId();
//       Location locations = sighting.getLocation(); 
//        jdbcTemplate.update(SQL_INSERT_LOCATION,  sightingId, locations.getLocationId());
//   
//    }

    private Location findLocationForSighting(Sighting sighting) {
        return jdbcTemplate.queryForObject(SQL_SELECT_ALL_LOCATION_BY_SIGHTING,
                new LocationMapper(), sighting.getSightingId());
    }

    private List<SuperPerson> findSuperPersonForSighting(Sighting sighting) {
        return jdbcTemplate.query(SQL_SELECT_SUPER_PERSON_BY_SIGHTING,
                new SuperPersonMapper(), sighting.getSightingId());

    }

    private List<Sighting> associateSightingAndLocationForSuperPerson(List<Sighting> sightingList) {
        for (Sighting currentSighting : sightingList) {
            currentSighting.setSuperPerson(findSuperPersonForSighting(currentSighting));
            currentSighting.setLocation(findLocationForSighting(currentSighting));

        }
        return sightingList;

    }

}
