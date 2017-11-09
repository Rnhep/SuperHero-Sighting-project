/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superhero.dao;


import com.sg.superhero.model.Location;
import com.sg.superhero.model.Organization;
import com.sg.superhero.model.SuperPerson;

import java.util.List;

/**
 *
 * @author ritheenhep
 */


public interface SuperPersonDao {
    
    public void addSuperPerson(SuperPerson person);
    
    public void deleteSuperPerson(int personId);
    
    public void updateSuperPerson(SuperPerson person);
    
    public SuperPerson getSuperPersonById(int superPersonId);
    
    public List<SuperPerson> getAllSuperPerson();
    
    public List<SuperPerson> getAllSuperPersonSide(boolean superHero);
    
    public List<SuperPerson> getAllSuperPersonByDate(String date);
            
    public List<SuperPerson> getAllSuperPersonByLocation(Location location); 
    
    public List<SuperPerson> getAllOranizationBySuperPerson(Organization orgId);
}
