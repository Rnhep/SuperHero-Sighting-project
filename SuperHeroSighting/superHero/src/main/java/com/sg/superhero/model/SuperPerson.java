/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superhero.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author ritheenhep
 */
public class SuperPerson {
    
    private int superPersonId;
    @NotEmpty(message = "You must supply a value for power.")
    @Length(max = 50, message = "First Name must be no more than 50 characters in length.")
    private String power;
    @NotEmpty(message = "You must supply a value for her name.")
    @Length(max = 50, message = "First Name must be no more than 50 characters in length.")
    private String name;
    private boolean superHeroSide;
    private String description;
    
    private List<Sighting> sightings = new ArrayList<>();
    private List<Organization> organizations = new ArrayList<>();

    public int getSuperPersonId() {
        return superPersonId;
    }

    public void setSuperPersonId(int superPersonId) {
        this.superPersonId = superPersonId;
    }

    public String getPower() {
        return power;
    }

    public void setPower(String power) {
        this.power = power;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isSuperHeroSide() {
        return superHeroSide;
    }

    public void setSuperHeroSide(boolean superHeroSide) {
        this.superHeroSide = superHeroSide;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Sighting> getSightings() {
        return sightings;
    }

    public void setSightings(List<Sighting> sightings) {
        this.sightings = sightings;
    }

    public List<Organization> getOrganizations() {
        return organizations;
    }

    public void setOrganizations(List<Organization> organizations) {
        this.organizations = organizations;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 31 * hash + this.superPersonId;
        hash = 31 * hash + Objects.hashCode(this.power);
        hash = 31 * hash + Objects.hashCode(this.name);
        hash = 31 * hash + (this.superHeroSide ? 1 : 0);
        hash = 31 * hash + Objects.hashCode(this.description);
        hash = 31 * hash + Objects.hashCode(this.sightings);
        hash = 31 * hash + Objects.hashCode(this.organizations);
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
        final SuperPerson other = (SuperPerson) obj;
        if (this.superPersonId != other.superPersonId) {
            return false;
        }
        if (this.superHeroSide != other.superHeroSide) {
            return false;
        }
        if (!Objects.equals(this.power, other.power)) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        if (!Objects.equals(this.sightings, other.sightings)) {
            return false;
        }
        if (!Objects.equals(this.organizations, other.organizations)) {
            return false;
        }
        return true;
    }
   

}
