/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superhero.dao;

/**
 *
 * @author ritheenhep
 */
public class PrepareStatement {

    //SUPER PERSON
    protected static final String SQL_INSERT_SUPER_PERSON
            = "insert into super_person "
            + "(name, super_power, super_person_side, description) "
            + "values(?, ?, ?, ?)";

    protected static final String SQL_SELECT_ALL_SUPER_PERSON_BY_LOCATION
            = "select distinct sp.* "
            + " from location l "
            + " inner join sighting s on l.location_id = s.location_id "
            + " inner join super_person_sighting sps on s.Sighting_id = sps.Sighting_id "
            + " inner join super_person sp on sps.super_person_id = sp.Super_Person_id "
            + "  where l.location_id = ? ";

//sighting
    protected static final String SLQ_GET_LATEST_10
            = "select *  from sighting ORDER BY date desc limit 10 ";

    protected static final String SLQ_SELECT_SIGHTING_BY_ID
            = " select * from sighting where sighting_id = ? ";

    protected static final String SQL_DELETE_SIGHTING_SUPER_PERSON
            = "delete from super_person_sighting where sighting_id = ?";

    protected static final String SQL_DELETE_LOCATION_FROM_SIGHTING
            = "delete from sighting where location_id= ?";

    protected static final String SQL_INSERT_SIGHTING
            = "insert into sighting(date, description, location_id )values(?, ?, ?)";

    protected static final String SQL_UPDATE_SIGHTING
            = "update sighting set date = ?, description = ?, location_id = ? "
            + "where sighting_id = ?";

    protected static final String SQL_DELETE_SIGHTING
            = "delete from sighting where sighting_id = ? ";

    protected static final String SQL_INSERT_SUPER_PERSON_BY_SIGHTING
            = "insert into super_person_sighting"
            + "(super_person_id, sighting_id)value(?,?)";

    protected static final String SQL_SELECT_ALL_LOCATION_BY_SIGHTING
            = "select l.* "
            + "from location l "
            + "inner join sighting s "
            + "on s.location_id = l.location_id "
            + "where s.sighting_id = ? ";
    protected static final String SQL_SELECT_SIGHTING_FOR_LOCATION
            = "select s.*"
            + "from sighting s "
            + "inner join location l on l.location_id = s.location_id "
            + "where l.location_id = ? ";
    
    protected static final String SQL_SELECT_ORGANIZATION_FOR_LOCATION
            ="select o.* from organization o "
            +" inner join location l on l.location_id = o.location_id "
            + " where l.location_id= ?";

    protected static final String SQL_SELECT_All_SIGHTING_BY_LOCATION
            = " select s.* "
            + "from sighting s "
            + "inner join location l "
            + "on l.sighting_id = s.sighting_id "
            + "where l.location_id = ? ";

    protected static final String SQL_SELECT_SUPER_PERSON_BY_SIGHTING
            = " select s.* "
            + " from super_person s "
            + " inner join  super_person_sighting sps "
            + " on s.super_person_id = sps.super_person_id "
            + " inner join sighting "
            + " on sps.sighting_id = sighting.sighting_id "
            + " where sighting.sighting_id =  ?";

    protected static final String SQL_SELECT_ALL_SIGHTING_BY_DATE
            = "select * from sighting "
            + "where date = ?";

    protected static final String SQL_SELECT_ALL_SIGHTING
            = " select * from sighting ";

//bridge
    protected static final String SQL_INSERT_SUPER_PERSON_SIGHTING
            = "insert into super_person_sighting(super_person_id, sighting_id )values(?,?)";

    protected static final String SQL_INSERT_SUPER_PERSON_ORGANIZATION
            = "insert into super_person_organization(super_person_id, organization_id)values(?,?)";

    protected static final String SQL_DELETE_ORGANIZATION_SUPER_PERSON
            = "delete from super_person_organization where organization_id = ? ";

    //location
    //   protected static final String SQL_INSERT_LOCATION_BY_SIGHTING
    //          = " select * from location where location_id = ?";
    protected static final String SQL_DELETE_LOCATION_FROM_ORGANIZATION
            = "delete from organization where location_id = ? ";
    protected static final String SQL_INSERT_LOCATION
            = "insert into location "
            + "(location_name , street, city, state, zip, latitude,longitude, description )"
            + "values(?,?,?,?,?,?,?,?)";
    protected static final String SQL_DELETE_LOCATION
            = "delete from location where location_id = ? ";

    protected static final String SQL_SELECT_ALL_LOCATION
            = "select * from location ";

    protected static final String SLQ_SELECT_LOCATION_BY_ID
            = " select * from location where location_id = ? ";

    protected static final String SQL_UPDATE_LOCATION
            = "update location set "
            + "  location_name = ? , street = ?, city = ?, state = ?, "
            + "zip = ?, latitude = ?,longitude = ?, description = ?  "
            + "where location_id =?";

    //Organization
    protected static final String SQL_SELECT_ORGANIZATION_BY_ID
            = "select * from organization where organization_id = ?";

    protected static final String SQL_DELETE_ORGANIZATION
            = "delete from organization where organization_id = ? ";

    protected static final String SQL_INSERT_ORGANIZATION
            = "insert into organization(organization_name, phone, description, location_id)"
            + "values(?,?,?,?)";
    protected static final String SQL_UPDATE_ORGANIZATION
            = "update organization set organization_name = ?, phone = ?, description = ?, location_id = ? "
            + "where organization_id = ?";

    protected static final String SQL_SELECT_ALL_ORGANIZATION
            = "select * from organization ";

    protected static final String SQL_SELECT_ALL_SUPER_PERSON_BY_ORGANIZATION
            = "  select * from super_person sp "
            + "join super_person_organization spo "
            + " on spo.super_person_id = sp.super_person_id "
            + " where spo.organization_Id = ?";
    protected static final String SQL_SELECT_ALL_LOCATION_BY_ORGANIZATION
            = " select * from location l "
            + "inner join organization o "
            + "on o.location_id = l.location_id "
            + "where o.organization_id = ?";

//   protected static final String SQL_SELECT_LOCATION_BY_ORGANIZATION
//           = "select * from location l"
//           + "inner join organization o "
//           + "on l.location_id = o.organization_id "
//           + "where o.organiza"
}
