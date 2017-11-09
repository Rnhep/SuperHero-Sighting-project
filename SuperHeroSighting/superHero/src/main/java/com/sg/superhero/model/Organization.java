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
public class Organization {

    private int organizationId;
    @NotEmpty(message = "You must supply a value for organization name.")
    @Length(max = 50, message = "First Name must be no more than 50 characters in length.")
    private String name;
    @NotEmpty(message = "You must supply a value for pone.")
    @Length(max = 50, message = "First Name must be no more than 50 characters in length.")
    private String phone;
    private String desciption;
    private Location location;
    private List<SuperPerson> member = new ArrayList<>();

    public int getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(int organizationId) {
        this.organizationId = organizationId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDesciption() {
        return desciption;
    }

    public void setDesciption(String desciption) {
        this.desciption = desciption;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public List<SuperPerson> getMember() {
        return member;
    }

    public void setMember(List<SuperPerson> member) {
        this.member = member;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + this.organizationId;
        hash = 53 * hash + Objects.hashCode(this.name);
        hash = 53 * hash + Objects.hashCode(this.phone);
        hash = 53 * hash + Objects.hashCode(this.desciption);
        hash = 53 * hash + Objects.hashCode(this.location);
        hash = 53 * hash + Objects.hashCode(this.member);
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
        final Organization other = (Organization) obj;
        if (this.organizationId != other.organizationId) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.phone, other.phone)) {
            return false;
        }
        if (!Objects.equals(this.desciption, other.desciption)) {
            return false;
        }
        if (!Objects.equals(this.location, other.location)) {
            return false;
        }
        if (!Objects.equals(this.member, other.member)) {
            return false;
        }
        return true;
    }

    
    
}
