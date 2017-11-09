/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superhero.dao;

import com.sg.superhero.model.Location;
import com.sg.superhero.model.Sighting;
import java.util.List;

/**
 *
 * @author ritheenhep
 */
public interface LocationDao {
    
    public void addLocation(Location location);
    
    public void deleteLocation(int locationId);
    
    public void updateLocation(Location location);
    
    public Location getLocationById(int locationId);
    
    public List<Location> getAllLocation();
    

    
    
    
}
