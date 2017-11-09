/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superhero.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.validation.constraints.Digits;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Value;

/**
 *
 * @author ritheenhep
 */
public class Location {

    private int locationId;
    @NotEmpty(message = "You must supply a value for locaton name.")
    @Length(max = 50, message = "First Name must be no more than 50 characters in length.")
    private String locationName;
    @NotEmpty(message = "You must supply a value for street.")
    @Length(max = 50, message = "First Name must be no more than 50 characters in length.")
    private String street;
    @NotEmpty(message = "You must supply a value for city.")
    @Length(max = 50, message = "First Name must be no more than 50 characters in length.")
    private String city;
    @NotEmpty(message = "You must supply a value for state.")
    @Length(max = 50, message = "First Name must be no more than 50 characters in length.")
    private String state;
    //@NotEmpty(message = "You must supply a value for zip.")
    private int zip;
    private String description;
    private double longitude;
    private double latitude;
    private List<SuperPerson> superPerson = new ArrayList<>();

    public int getLocationId() {
        return locationId;
    }

    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getZip() {
        return zip;
    }

    public void setZip(int zip) {
        this.zip = zip;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public List<SuperPerson> getSuperPerson() {
        return superPerson;
    }

    public void setSuperPerson(List<SuperPerson> superPerson) {
        this.superPerson = superPerson;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 41 * hash + this.locationId;
        hash = 41 * hash + Objects.hashCode(this.locationName);
        hash = 41 * hash + Objects.hashCode(this.street);
        hash = 41 * hash + Objects.hashCode(this.city);
        hash = 41 * hash + Objects.hashCode(this.state);
        hash = 41 * hash + this.zip;
        hash = 41 * hash + Objects.hashCode(this.description);
        hash = 41 * hash + (int) (Double.doubleToLongBits(this.longitude) ^ (Double.doubleToLongBits(this.longitude) >>> 32));
        hash = 41 * hash + (int) (Double.doubleToLongBits(this.latitude) ^ (Double.doubleToLongBits(this.latitude) >>> 32));
        hash = 41 * hash + Objects.hashCode(this.superPerson);
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
        final Location other = (Location) obj;
        if (this.locationId != other.locationId) {
            return false;
        }
        if (this.zip != other.zip) {
            return false;
        }
        if (Double.doubleToLongBits(this.longitude) != Double.doubleToLongBits(other.longitude)) {
            return false;
        }
        if (Double.doubleToLongBits(this.latitude) != Double.doubleToLongBits(other.latitude)) {
            return false;
        }
        if (!Objects.equals(this.locationName, other.locationName)) {
            return false;
        }
        if (!Objects.equals(this.street, other.street)) {
            return false;
        }
        if (!Objects.equals(this.city, other.city)) {
            return false;
        }
        if (!Objects.equals(this.state, other.state)) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }

        if (!Objects.equals(this.superPerson, other.superPerson)) {
            return false;
        }
        return true;
    }

}
