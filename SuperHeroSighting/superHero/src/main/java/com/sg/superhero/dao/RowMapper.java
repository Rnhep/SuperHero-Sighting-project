/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superhero.dao;

import com.sg.superhero.model.Location;
import com.sg.superhero.model.Organization;
import com.sg.superhero.model.Sighting;
import com.sg.superhero.model.SuperPerson;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author ritheenhep
 */
public class RowMapper {

    protected static final class SuperPersonMapper implements org.springframework.jdbc.core.RowMapper<SuperPerson> {

        @Override
        public SuperPerson mapRow(ResultSet rs, int i) throws SQLException {
            SuperPerson sp = new SuperPerson();
            sp.setName(rs.getString("name"));
            sp.setPower(rs.getString("super_power"));
            sp.setDescription(rs.getString("description"));
            sp.setSuperHeroSide(rs.getBoolean("super_person_side"));
            sp.setSuperPersonId(rs.getInt("Super_person_id"));
            return sp;
        }
    }

    protected static final class SightingMapper implements org.springframework.jdbc.core.RowMapper<Sighting> {

        @Override
        public Sighting mapRow(ResultSet rs, int i) throws SQLException {

            Sighting st = new Sighting();
            st.setSightingId(rs.getInt("sighting_id"));
            st.setDescription(rs.getString("description"));
            st.setDate(rs.getTimestamp("date").toLocalDateTime().toLocalDate());

            return st;
        }
    }

    protected static final class LocationMapper implements org.springframework.jdbc.core.RowMapper<Location> {

        @Override
        public Location mapRow(ResultSet rs, int i) throws SQLException {
            Location l = new Location();
            l.setLocationName(rs.getString("location_name"));
            l.setStreet(rs.getString("street"));
            l.setCity(rs.getString("city"));
            l.setState(rs.getString("state"));
            l.setZip(rs.getInt("zip"));
            l.setLatitude(rs.getDouble("latitude"));
            l.setLongitude(rs.getDouble("longitude"));
            l.setDescription(rs.getString("description"));
            l.setLocationId(rs.getInt("location_id"));
            return l;
        }
    }

    protected static final class OrganizationMapper implements org.springframework.jdbc.core.RowMapper<Organization> {

        @Override
        public Organization mapRow(ResultSet rs, int i) throws SQLException {
            Organization org = new Organization();
            org.setName(rs.getString("organization_name"));
            org.setPhone(rs.getString("phone"));
            org.setDesciption(rs.getString("description"));
            org.setOrganizationId(rs.getInt("organization_id"));
            return org;
        }
    }

}
