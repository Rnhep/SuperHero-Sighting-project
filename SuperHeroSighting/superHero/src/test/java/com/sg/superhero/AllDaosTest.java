/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superhero;

import com.sg.superhero.dao.LocationDao;
import com.sg.superhero.dao.OrganizationDao;
import com.sg.superhero.dao.SightingDao;
import com.sg.superhero.dao.SuperPersonDao;
import com.sg.superhero.model.Location;
import com.sg.superhero.model.Organization;
import com.sg.superhero.model.Sighting;
import com.sg.superhero.model.SuperPerson;
import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author ritheenhep
 */
public class AllDaosTest {

    private OrganizationDao organizationDao;
    private SuperPersonDao dao;
    private LocationDao locationDao;
    private SightingDao sightingDao;

    public AllDaosTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        ApplicationContext ctx
                = new ClassPathXmlApplicationContext("test-applicationContext.xml");

        organizationDao = ctx.getBean("organizationDao", OrganizationDao.class);
        dao = ctx.getBean("superPersonDao", SuperPersonDao.class);
        locationDao = ctx.getBean("locationDao", LocationDao.class);
        sightingDao = ctx.getBean("sightingDao", SightingDao.class);

        // delete all books
        List<Organization> organization = organizationDao.getAllOrganization();
        for (Organization currentOrganization : organization) {
            organizationDao.deleteOrganization(currentOrganization.getOrganizationId());
        }
        List<SuperPerson> superPerson = dao.getAllSuperPerson();
        for (SuperPerson currentPerson : superPerson) {
            dao.deleteSuperPerson(currentPerson.getSuperPersonId());
        }
        List<Sighting> sighting = sightingDao.getAllSighting();
        for (Sighting currentSighting : sighting) {
            sightingDao.deletSigting(currentSighting.getSightingId());
        }
        List<Location> location = locationDao.getAllLocation();
        for (Location currentlLocation : location) {
            locationDao.deleteLocation(currentlLocation.getLocationId());
        }

    }

    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    @Test
    public void testAddGetSuperPerson() {
   
        SuperPerson person = new SuperPerson();
        person.setName("Flash");
        person.setPower("Speed");
        person.setSuperHeroSide(true);
        person.setDescription("fast");
        
        List<Organization> organizations = new ArrayList<>();
        person.setOrganizations(organizations);
        
        List<Sighting> sightings = new ArrayList<>();
        person.setSightings(sightings);
        
        dao.addSuperPerson(person);
        SuperPerson fromDao = dao.getSuperPersonById(person.getSuperPersonId());
        assertEquals(fromDao, person);

    }

    @Test
    public void testDeletSuperPerson() {
        SuperPerson person = new SuperPerson();
        person.setName("Flash");
        person.setPower("Speed");
        person.setSuperHeroSide(true);
        person.setDescription("fast");

        List<Organization> organizations = new ArrayList<>();
        person.setOrganizations(organizations);

        
        List<Sighting> sightings = new ArrayList<>();
        person.setSightings(sightings);
        dao.addSuperPerson(person);

        SuperPerson fromDao = dao.getSuperPersonById(person.getSuperPersonId());

        assertEquals(fromDao, person);
        dao.deleteSuperPerson(person.getSuperPersonId());
        assertNull(dao.getSuperPersonById(person.getSuperPersonId()));

    }

    @Test
    public void testTetallSuperPerson() {
        SuperPerson person = new SuperPerson();
        person.setName("Flash");
        person.setPower("Speed");
        person.setSuperHeroSide(true);
        person.setDescription("fast");

        List<Organization> organizations = new ArrayList<>();
        person.setOrganizations(organizations);

        List<Sighting> sightings = new ArrayList<>();
        person.setSightings(sightings);
        dao.addSuperPerson(person);

        SuperPerson person1 = new SuperPerson();
        person1.setName("jojo");
        person1.setPower("nothing");
        person1.setSuperHeroSide(true);
        person1.setDescription("normal people");

        List<Organization> organization1 = new ArrayList<>();
        person1.setOrganizations(organizations);

        List<Sighting> sighting1 = new ArrayList<>();
        person1.setSightings(sightings);
        dao.addSuperPerson(person1);

        List<SuperPerson> supersList = new ArrayList<>();
        supersList = dao.getAllSuperPerson();
        assertEquals(2, supersList.size());

    }

    @Test
    public void testUpdateSuperPerson() {

        SuperPerson person = new SuperPerson();
        person.setName("Flash");
        person.setPower("Speed");
        person.setSuperHeroSide(true);
        person.setDescription("fast");

        List<Organization> organizations = new ArrayList<>();
        person.setOrganizations(organizations);

        List<Sighting> sightings = new ArrayList<>();
        person.setSightings(sightings);
        dao.addSuperPerson(person);
       
        String name = "not flash";
        
        SuperPerson updateName = dao.getSuperPersonById(person.getSuperPersonId());
        updateName.setName(name);
        dao.updateSuperPerson(updateName);
        updateName = dao.getSuperPersonById(person.getSuperPersonId());

        assertEquals(name, updateName.getName());

    }

    @Test
    public void testGetsuperPersonById() {

        SuperPerson person = new SuperPerson();
        person.setName("Flash");
        person.setPower("Speed");
        person.setSuperHeroSide(true);
        person.setDescription("fast");

        List<Organization> organizations = new ArrayList<>();
        person.setOrganizations(organizations);

        List<Sighting> sightings = new ArrayList<>();
        person.setSightings(sightings);
        dao.addSuperPerson(person);


        SuperPerson superPersonById = dao.getSuperPersonById(person.getSuperPersonId());
        assertEquals(person, superPersonById);

    }

    @Test
    public void testGetAllsuperPersonByLocation() {

        SuperPerson person = new SuperPerson();
        person.setName("Flash");
        person.setPower("Speed");
        person.setSuperHeroSide(true);
        person.setDescription("fast");

        dao.addSuperPerson(person);
        List<SuperPerson> listofSuper = new ArrayList<>();
        listofSuper.add(person);
        Location location = new Location();
        location.setLocationName("hometown");
        location.setStreet("123 hometwon");
        location.setCity("burnsville");
        location.setState("MN");
        location.setZip(4435);
        location.setLatitude(1);
        location.setLongitude(1);
        locationDao.addLocation(location);

        Sighting sighting = new Sighting();
        sighting.setDate(LocalDate.now());
        sighting.setSuperPerson(listofSuper);
        sighting.setLocation(location);

        sightingDao.addSighting(sighting);

        SuperPerson person1 = new SuperPerson();
        person1.setName("Flash");
        person1.setPower("Speed");
        person1.setSuperHeroSide(true);
        person1.setDescription("fast");
        dao.addSuperPerson(person1);
        List<SuperPerson> listofSuper1 = new ArrayList<>();
        listofSuper1.add(person1);

        Sighting sighting1 = new Sighting();
        sighting.setDate(LocalDate.now());
        sighting.setLocation(location);
        sighting.setSuperPerson(listofSuper1);
        sightingDao.addSighting(sighting);

        List< SuperPerson> allSupers = dao.getAllSuperPersonByLocation(location);
        assertEquals(2, allSupers.size());

    }

    @Test
    public void testaddsighting() {

        SuperPerson person = new SuperPerson();
        person.setName("Flash");
        person.setPower("Speed");
        person.setSuperHeroSide(true);
        person.setDescription("fast");
        dao.addSuperPerson(person);

        List<SuperPerson> supersList = new ArrayList<>();
        supersList.add(dao.getSuperPersonById(person.getSuperPersonId()));

        Location location = new Location();
        location.setLocationName("hometown");
        location.setStreet("123 hometwon");
        location.setCity("burnsville");
        location.setState("MN");
        location.setZip(4435);
        location.setLatitude(4);
        location.setLongitude(5);
        locationDao.addLocation(location);

        Sighting sighting = new Sighting();
        sighting.setDate(LocalDate.now());
        sighting.setLocation(location);
        sighting.setSuperPerson(supersList);
        sightingDao.addSighting(sighting);

        Sighting fromDao = sightingDao.getSightingById(sighting.getSightingId());
        assertEquals(fromDao, sighting);
    }//check full object

    @Test
    public void testDeleteSighting() {

        SuperPerson person = new SuperPerson();
        person.setName("Flash");
        person.setPower("Speed");
        person.setSuperHeroSide(true);
        person.setDescription("fast");
        dao.addSuperPerson(person);

        List<SuperPerson> supersList = new ArrayList<>();
        supersList.add(dao.getSuperPersonById(person.getSuperPersonId()));

        Location location = new Location();
        location.setLocationName("hometown");
        location.setStreet("123 hometwon");
        location.setCity("burnsville");
        location.setState("MN");
        location.setZip(4435);
        location.setLatitude(1);
        location.setLongitude(3);
        locationDao.addLocation(location);

        Sighting sighting = new Sighting();
        sighting.setDate(LocalDate.now());
        sighting.setLocation(location);
        sighting.setSuperPerson(supersList);
        sightingDao.addSighting(sighting);

        Sighting fromDao = sightingDao.getSightingById(sighting.getSightingId());
        assertEquals(fromDao.getSightingId(), sighting.getSightingId());
        
        sightingDao.deletSigting(sighting.getSightingId());
        assertNull(sightingDao.getSightingById(sighting.getSightingId()));
    }

    @Test
    public void testUpdateSight() {

        Location location = new Location();
        location.setLocationName("hometown");
        location.setStreet("123 hometwon");
        location.setCity("burnsville");
        location.setState("MN");
        location.setZip(4435);
        location.setLatitude(1);
        location.setLongitude(1);
        locationDao.addLocation(location);

        SuperPerson person = new SuperPerson();
        person.setName("Flash");
        person.setPower("Speed");
        person.setSuperHeroSide(true);
        person.setDescription("fast");
        dao.addSuperPerson(person);

        List<SuperPerson> supersList = new ArrayList<>();
        supersList.add(dao.getSuperPersonById(person.getSuperPersonId()));

        Sighting sighting = new Sighting();
        sighting.setDate(LocalDate.now());
        sighting.setLocation(location);
        sighting.setSuperPerson(supersList);
        sightingDao.addSighting(sighting);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String date = "2018-01-12";
        LocalDate sightingDate = LocalDate.parse(date);

        Sighting updateSighting = sightingDao.getSightingById(sighting.getSightingId());
        updateSighting.setDate(sightingDate);
        sightingDao.updateSighting(updateSighting);

        updateSighting = sightingDao.getSightingById(sighting.getSightingId());
        assertEquals(updateSighting.getDate(), sightingDate);

    }

    @Test
    public void testGetallSighting() {
        SuperPerson person = new SuperPerson();
        person.setName("Flash");
        person.setPower("Speed");
        person.setSuperHeroSide(true);
        person.setDescription("fast");
        dao.addSuperPerson(person);

        List<SuperPerson> supersList = new ArrayList<>();
        supersList.add(dao.getSuperPersonById(person.getSuperPersonId()));

        Location location = new Location();
        location.setLocationName("hometown");
        location.setStreet("123 hometwon");
        location.setCity("burnsville");
        location.setState("MN");
        location.setZip(4435);
        location.setLatitude(2);
        location.setLongitude(1);
        locationDao.addLocation(location);

        Sighting sighting = new Sighting();
        sighting.setDate(LocalDate.now());
        sighting.setLocation(location);
        sighting.setSuperPerson(supersList);

        sightingDao.addSighting(sighting);
        List<Sighting> allSighting = new ArrayList<>();
        Sighting fromDao = sightingDao.getSightingById(sighting.getSightingId());
        allSighting.add(fromDao);

        assertEquals(1, allSighting.size());

    }

    @Test
    public void testGetsightingById() {
        SuperPerson person = new SuperPerson();
        person.setName("Flash");
        person.setPower("Speed");
        person.setSuperHeroSide(true);
        person.setDescription("fast");
        dao.addSuperPerson(person);

        List<SuperPerson> supersList = new ArrayList<>();
        supersList.add(dao.getSuperPersonById(person.getSuperPersonId()));

        Location location = new Location();
        location.setLocationName("hometown");
        location.setStreet("123 hometwon");
        location.setCity("burnsville");
        location.setState("MN");
        location.setZip(4435);
        location.setLatitude(3);
        location.setLongitude(1);
        locationDao.addLocation(location);

        Sighting sighting = new Sighting();
        sighting.setDate(LocalDate.now());
        sighting.setLocation(location);
        sighting.setSuperPerson(supersList);
        sightingDao.addSighting(sighting);
        
        Sighting fromDao = sightingDao.getSightingById(sighting.getSightingId());
        assertEquals(sighting, fromDao);
    }//check full object

    @Test
    public void getSightingByDate() {

        SuperPerson person = new SuperPerson();
        person.setName("Flash");
        person.setPower("Speed");
        person.setSuperHeroSide(true);
        person.setDescription("fast");
        dao.addSuperPerson(person);

        List<SuperPerson> supersList = new ArrayList<>();
        supersList.add(dao.getSuperPersonById(person.getSuperPersonId()));

        Location location = new Location();
        location.setLocationName("hometown");
        location.setStreet("123 hometwon");
        location.setCity("burnsville");
        location.setState("MN");
        location.setZip(4435);
        location.setLatitude(2);
        location.setLongitude(4);
        locationDao.addLocation(location);

        Sighting sighting = new Sighting();
        sighting.setDate(LocalDate.now());
        sighting.setLocation(location);
        sighting.setSuperPerson(supersList);
        sightingDao.addSighting(sighting);

        List<Sighting> fromDaoList = new ArrayList<>();
        fromDaoList = sightingDao.getSightingByDate(LocalDate.now());

        assertEquals(1, fromDaoList.size());
    }

    @Test
    public void testaddgetOrganization() {

        SuperPerson person = new SuperPerson();
        person.setName("Flash");
        person.setPower("Speed");
        person.setSuperHeroSide(true);
        person.setDescription("fast");
        dao.addSuperPerson(person);

        List<SuperPerson> supersList = new ArrayList<>();
        supersList.add(dao.getSuperPersonById(person.getSuperPersonId()));

        Location location = new Location();
        location.setLocationName("hometown");
        location.setStreet("123 hometwon");
        location.setCity("burnsville");
        location.setState("MN");
        location.setZip(4435);
        location.setLatitude(9);
        location.setLongitude(9);
        locationDao.addLocation(location);

        Organization organization = new Organization();
        organization.setName("the winner");
        organization.setPhone("343-34343-34343");
        organization.setMember(supersList);
        organization.setLocation(location);
        organization.setDesciption("something");
        organizationDao.addOrganization(organization);
        Organization fromDao = organizationDao.getOrganizationById(organization.getOrganizationId());
        assertEquals(fromDao, organization);

    }//test object

    @Test
    public void testDeleteOrganization() {

        SuperPerson person = new SuperPerson();
        person.setName("Flash");
        person.setPower("Speed");
        person.setSuperHeroSide(true);
        person.setDescription("fast");
        dao.addSuperPerson(person);
        List<SuperPerson> supersList = new ArrayList<>();
        supersList.add(dao.getSuperPersonById(person.getSuperPersonId()));

        Location location = new Location();
        location.setLocationName("hometown");
        location.setStreet("123 hometwon");
        location.setCity("burnsville");
        location.setState("MN");
        location.setZip(4435);
        location.setLatitude(1);
        location.setLongitude(9);
        locationDao.addLocation(location);

        Organization organization = new Organization();
        organization.setName("the winner");
        organization.setPhone("343-34343-34343");
        organization.setDesciption("something");
        organization.setLocation(location);
        organizationDao.addOrganization(organization);
        
        Organization fromDao = organizationDao.getOrganizationById(organization.getOrganizationId());
        assertEquals(fromDao.getOrganizationId(), organization.getOrganizationId());
        organizationDao.deleteOrganization(organization.getOrganizationId());
        assertNull(organizationDao.getOrganizationById(organization.getOrganizationId()));

    }

    @Test
    public void testUpdateOrganization() {

        SuperPerson person = new SuperPerson();
        person.setName("Flash");
        person.setPower("Speed");
        person.setSuperHeroSide(true);
        person.setDescription("fast");
        dao.addSuperPerson(person);
        List<SuperPerson> supersList = new ArrayList<>();
        supersList.add(dao.getSuperPersonById(person.getSuperPersonId()));
        Location location = new Location();
        location.setLocationName("hometown");
        location.setStreet("123 hometwon");
        location.setCity("burnsville");
        location.setState("MN");
        location.setZip(4435);
        location.setLatitude(1);
        location.setLongitude(1);
        locationDao.addLocation(location);
        Location locationId = locationDao.getLocationById(location.getLocationId());
        Organization organization = new Organization();
        organization.setName("the winner");
        organization.setPhone("343-34343-34343");
        organization.setDesciption("something");
        organization.setLocation(locationId);
        organizationDao.addOrganization(organization);
        
        String OrganizationName = "the supers";
        Organization newName = organizationDao.getOrganizationById(organization.getOrganizationId());
        newName.setName(OrganizationName);
        organizationDao.updateOrganization(newName);
        newName = organizationDao.getOrganizationById(organization.getOrganizationId());
        assertEquals(OrganizationName, newName.getName());

    }

    @Test
    public void testGetOrganizationById() {

        Location location = new Location();
        location.setLocationName("hometown");
        location.setStreet("123 hometwon");
        location.setCity("burnsville");
        location.setState("MN");
        location.setZip(4435);
        location.setLatitude(1);
        location.setLongitude(1);
        locationDao.addLocation(location);

        Organization organization = new Organization();
        organization.setName("the winner");
        organization.setPhone("343-34343-34343");
        organization.setDesciption("something");
        organization.setLocation(location);
        organizationDao.addOrganization(organization);
        
        Organization byId = organizationDao.getOrganizationById(organization.getOrganizationId());
        assertEquals(organization.getName(), byId.getName());

    }//object

    @Test
    public void tesGetAllSuperByOrganization() {// need to check

        SuperPerson person = new SuperPerson();
        person.setName("Flash");
        person.setPower("Speed");
        person.setSuperHeroSide(true);
        person.setDescription("fast");
        dao.addSuperPerson(person);

        SuperPerson person1 = new SuperPerson();
        person1.setName("zoom");
        person1.setPower("Speed");
        person1.setSuperHeroSide(true);
        person1.setDescription("fast");
        dao.addSuperPerson(person1);
        List<SuperPerson> listOfSuperPerson = new ArrayList();
        listOfSuperPerson = dao.getAllSuperPerson();

        Location location = new Location();
        location.setLocationName("hometown");
        location.setStreet("123 hometwon");
        location.setCity("burnsville");
        location.setState("MN");
        location.setZip(4435);
        location.setLatitude(11);
        location.setLongitude(2);
        locationDao.addLocation(location);

        Organization organization = new Organization();
        organization.setName("the winner");
        organization.setPhone("343-34343-34343");
        organization.setDesciption("something");
        organization.setMember(listOfSuperPerson);
        organization.setLocation(location);
        organization.setMember(listOfSuperPerson);
        organizationDao.addOrganization(organization);
        
        Organization listofmember = organizationDao.getOrganizationById(organization.getOrganizationId());
        assertEquals(listOfSuperPerson, listofmember.getMember());

    }

    @Test
    public void testAddGetLocation() {
        Location location = new Location();
        location.setLocationName("hometown");
        location.setStreet("123 hometwon");
        location.setCity("burnsville");
        location.setState("MN");
        location.setZip(4435);
        location.setLatitude(12);
        location.setLongitude(34);
        locationDao.addLocation(location);
        
        Location fromDao = locationDao.getLocationById(location.getLocationId());
        assertEquals(fromDao, location);

    }

    @Test
    public void testDeleteLocation() {
        Location location = new Location();
        location.setLocationName("hometown");
        location.setStreet("123 hometwon");
        location.setCity("burnsville");
        location.setState("MN");
        location.setZip(4435);
        location.setLatitude(12);
        location.setLongitude(34);
        locationDao.addLocation(location);
        
        Location fromDao = locationDao.getLocationById(location.getLocationId());
        assertEquals(fromDao, location);
        
        locationDao.deleteLocation(location.getLocationId());
        assertNull(locationDao.getLocationById(location.getLocationId()));
    }

    @Test
    public void testUpdateLocation() {
        Location location = new Location();
        location.setLocationName("hometown");
        location.setStreet("123 hometwon");
        location.setCity("burnsville");
        location.setState("MN");
        location.setZip(4435);
        location.setLatitude(12);
        location.setLongitude(34);
        locationDao.addLocation(location);
        
        Location fromDao = locationDao.getLocationById(location.getLocationId());
        assertEquals(fromDao, location);
        
        String locationName = "The grid";
        Location updateName = locationDao.getLocationById(location.getLocationId());
        updateName.setLocationName(locationName);
        locationDao.addLocation(location);
        
        assertEquals(locationName, updateName.getLocationName());
    }

    @Test
    public void TestGetAllLocation() {
        Location location = new Location();
        location.setLocationName("hometown");
        location.setStreet("123 hometwon");
        location.setCity("burnsville");
        location.setState("MN");
        location.setZip(4435);
        location.setLatitude(12);
        location.setLongitude(34);
        locationDao.addLocation(location);

        Location location1 = new Location();
        location1.setLocationName("hometown");
        location1.setStreet("123 hometwon");
        location1.setCity("burnsville");
        location1.setState("MN");
        location1.setZip(4435);
        location1.setLatitude(12);
        location1.setLongitude(34);
        locationDao.addLocation(location1);
        
        List<Location> allLocation = new ArrayList<>();
        allLocation = locationDao.getAllLocation();
        assertEquals(2, allLocation.size());

    }

    @Test
    public void testGetLocationById() {
        Location location = new Location();
        location.setLocationName("hometown");
        location.setStreet("123 hometwon");
        location.setCity("burnsville");
        location.setState("MN");
        location.setZip(4435);
        location.setLatitude(12);
        location.setLongitude(34);
        locationDao.addLocation(location);
        
        Location fromDao = locationDao.getLocationById(location.getLocationId());
        assertEquals(fromDao, location);

    }//object
}
