/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superhero.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

/**
 *
 * @author ritheenhep
 */
public class Sighting {
    private int sightingId;
    
    @NotEmpty(message = "You must supply a value for sighting.")
    @Length(max = 50, message = "First Name must be no more than 50 characters in length.")
    private String sighting;
    
    private String description;
    @DateTimeFormat(iso = ISO.DATE)
    private LocalDate date;
    private Location location;
    private List<SuperPerson> superPerson = new ArrayList<>();

    public int getSightingId() {
        return sightingId;
    }

    public void setSightingId(int sightingId) {
        this.sightingId = sightingId;
    }

    public String getSighting() {
        return sighting;
    }

    public void setSighting(String sighting) {
        this.sighting = sighting;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public List<SuperPerson> getSuperPerson() {
        return superPerson;
    }

    public void setSuperPerson(List<SuperPerson> superPerson) {
        this.superPerson = superPerson;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + this.sightingId;
        hash = 29 * hash + Objects.hashCode(this.sighting);
        hash = 29 * hash + Objects.hashCode(this.description);
        hash = 29 * hash + Objects.hashCode(this.date);
        hash = 29 * hash + Objects.hashCode(this.location);
        hash = 29 * hash + Objects.hashCode(this.superPerson);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Sighting other = (Sighting) obj;
        if (this.sightingId != other.sightingId) {
            return false;
        }
        if (!Objects.equals(this.sighting, other.sighting)) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        if (!Objects.equals(this.date, other.date)) {
            return false;
        }
        if (!Objects.equals(this.location, other.location)) {
            return false;
        }
        if (!Objects.equals(this.superPerson, other.superPerson)) {
            return false;
        }
        return true;
    }

   

    
  

}
