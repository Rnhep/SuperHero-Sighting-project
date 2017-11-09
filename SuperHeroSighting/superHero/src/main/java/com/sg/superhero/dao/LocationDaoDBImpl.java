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
import java.util.List;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 * @author ritheenhep
 */
public class LocationDaoDBImpl implements LocationDao {

    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void addLocation(Location location) {
        jdbcTemplate.update(SQL_INSERT_LOCATION,
                location.getLocationName(),
                location.getStreet(),
                location.getCity(),
                location.getState(),
                location.getZip(),
                location.getLatitude(),
                location.getLongitude(),
                location.getDescription());
        location.setLocationId(jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class));
      insertSuperPersonIntoLcaiton(location);

    }

    @Override
    public void deleteLocation(int locationId) {
        
    List<Sighting> sightingList = jdbcTemplate.query(SQL_SELECT_SIGHTING_FOR_LOCATION, new SightingMapper(), locationId);
    
    List<Organization> orginazationList = jdbcTemplate.query(SQL_SELECT_ORGANIZATION_FOR_LOCATION, new OrganizationMapper(), locationId);
    for(Sighting sighting: sightingList){
        jdbcTemplate.update(SQL_DELETE_SIGHTING_SUPER_PERSON, sighting.getSightingId()); 
        
   }  
    for(Organization organization: orginazationList){
        jdbcTemplate.update(SQL_DELETE_ORGANIZATION_SUPER_PERSON, organization.getOrganizationId());
    }
      jdbcTemplate.update(SQL_DELETE_LOCATION_FROM_SIGHTING, locationId);
      jdbcTemplate.update(SQL_DELETE_LOCATION_FROM_ORGANIZATION, locationId);
      jdbcTemplate.update(SQL_DELETE_LOCATION, locationId); 
    }

    @Override
    public void updateLocation(Location location) {
        jdbcTemplate.update(SQL_UPDATE_LOCATION,
                location.getLocationName(),
                location.getStreet(),
                location.getCity(),
                location.getState(),
                location.getZip(),
                location.getLatitude(),
                location.getLongitude(),
                location.getDescription(),
                location.getLocationId());

               
    }
   
    @Override
    public Location getLocationById(int locationId) {

        try {
            Location location = jdbcTemplate.queryForObject(SLQ_SELECT_LOCATION_BY_ID, new LocationMapper(), locationId);
            location.setSuperPerson(findSuperPersonForlocation(location));
            return location;
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    
    }
    @Override
    public List<Location> getAllLocation() {
        List<Location> locations = jdbcTemplate.query(SQL_SELECT_ALL_LOCATION, new LocationMapper());
        return locations;
    }
    

    private void insertSuperPersonIntoLcaiton(Location location){
        final int locationId= location.getLocationId();
        List<SuperPerson> supersList = location.getSuperPerson();
        
        for(SuperPerson currentSuper: supersList){
            jdbcTemplate.update(SQL_INSERT_SUPER_PERSON, currentSuper.getSuperPersonId(), locationId);
        }
    }

    private  List<SuperPerson> findSuperPersonForlocation(Location location){
        return jdbcTemplate.query(SQL_SELECT_ALL_SUPER_PERSON_BY_LOCATION,
                new SuperPersonMapper(), location.getLocationId());
    }
    
    private List<Location> associatedLocatonAndSuperPerson(List<Location> locationList){
        for(Location currentLocation : locationList){
            currentLocation.setSuperPerson(findSuperPersonForlocation(currentLocation));
        }
        return locationList;
    }
    
}
