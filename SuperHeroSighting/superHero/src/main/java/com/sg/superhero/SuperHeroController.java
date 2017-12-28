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
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


/**
 *
 * @author ritheenhep
 */
@Controller
public class SuperHeroController {

    SuperPersonDao supersDao;
    SightingDao sightingDao;
    LocationDao locationDao;
    OrganizationDao organizationDao;

    @Inject
    public SuperHeroController(SuperPersonDao supersDao, SightingDao sightingDao, LocationDao locationDao, OrganizationDao organizationDao) {
        this.supersDao = supersDao;
        this.sightingDao = sightingDao;
        this.locationDao = locationDao;
        this.organizationDao = organizationDao;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home(HttpServletRequest request, Model model) {
        List<Sighting> sightingList = sightingDao.getLatestSighting();
        model.addAttribute("sightingList", sightingList);
        return "index";
    }

    @RequestMapping(value = "/displaySuperPeoplePage", method = RequestMethod.GET)
    public String displayAllSuperPeople(HttpServletRequest request, Model model) {
        List<Organization> organizationList = organizationDao.getAllOrganization();
        List<SuperPerson> heroList = supersDao.getAllSuperPerson();
        model.addAttribute("heroList", heroList);
        model.addAttribute("organizationList", organizationList);
        return "superHero";

    }

    @RequestMapping(value = "/displaySuperPersonDetails", method = RequestMethod.GET)
    public String displaySuperHeroDetail(HttpServletRequest request, Model model) {
        String superHeroIdParameter = request.getParameter("superPersonId");
        int superPersonId = Integer.parseInt(superHeroIdParameter);
        SuperPerson superPerson = supersDao.getSuperPersonById(superPersonId);
        model.addAttribute("superPerson", superPerson);
        return "superHeroDetails";
    }

    @RequestMapping(value = "/addSuperPerson", method = RequestMethod.POST)
    public String addSuperPerson(HttpServletRequest rq, Model model) {

        List<Organization> organizationList = new ArrayList<>();
        List<Integer> organizationsId = new ArrayList<>();
        String[] organizationIdParameter = rq.getParameterValues("organization");
        int[] orgId = new int[organizationIdParameter.length];

        for (int i = 0; i < organizationIdParameter.length; i++) {
            orgId[i] = Integer.parseInt(organizationIdParameter[i]);
            organizationsId.add(orgId[i]);
        }
        for (int organizationid : organizationsId) {
            Organization organization = organizationDao.getOrganizationById(organizationid);
            organizationList.add(organization);
        }

        SuperPerson sp = new SuperPerson();
        sp.setName(rq.getParameter("name"));
        sp.setPower(rq.getParameter("power"));
        sp.setSuperHeroSide(Boolean.parseBoolean(rq.getParameter("side")));
        sp.setDescription(rq.getParameter("description"));
        sp.setOrganizations(organizationList);
        supersDao.addSuperPerson(sp);
        return "redirect:displaySuperPeoplePage";

    }

    @RequestMapping(value = "/deleteSuperPerson", method = RequestMethod.GET)
    public String deleteSuperPerson(HttpServletRequest rq, Model model) {
        String superPersonIdParameter = rq.getParameter("superPersonId");
        int superPersonId = Integer.parseInt(superPersonIdParameter);
        supersDao.deleteSuperPerson(superPersonId);
        return "redirect:displaySuperPeoplePage";

    }

    @RequestMapping(value = "/displayEditSuperPersonForm", method = RequestMethod.GET)
    public String displayEditSuperhero(HttpServletRequest rq, Model model) {
        String superHeroIdParameter = rq.getParameter("superPersonId");
        int superPersonId = Integer.parseInt(superHeroIdParameter);
        List<Organization> organizationList = organizationDao.getAllOrganization();
        SuperPerson superPerson = supersDao.getSuperPersonById(superPersonId);
        model.addAttribute("superPerson", superPerson);
        model.addAttribute("organizationList", organizationList);
        return "editSuperHero";

    }

    @RequestMapping(value = "/editSuperPerson", method = RequestMethod.POST)
    public String editSuperPerson(HttpServletRequest rq, @Valid @ModelAttribute("superPerson") SuperPerson superPerson,
            BindingResult result) {
        if (result.hasErrors()) {
            return "editSuperHero";
        }
        List<Organization> organizationList = new ArrayList<>();
        List<Integer> organizationsId = new ArrayList<>();
        String[] organizationIdParameter = rq.getParameterValues("organization");
        int[] orgId = new int[organizationIdParameter.length];

        for (int i = 0; i < organizationIdParameter.length; i++) {
            orgId[i] = Integer.parseInt(organizationIdParameter[i]);
            organizationsId.add(orgId[i]);
        }
        for (int organizationid : organizationsId) {
            Organization organization = organizationDao.getOrganizationById(organizationid);
            organizationList.add(organization);
        }
        superPerson.setOrganizations(organizationList);
        superPerson.setSuperHeroSide(Boolean.parseBoolean(rq.getParameter("side")));
        supersDao.updateSuperPerson(superPerson);
        return "redirect:displaySuperPeoplePage";
    }
    // end of super person

    //start of location
    @RequestMapping(value = "/displayLocationPage", method = RequestMethod.GET)
    public String DisplayAllLocation(HttpServletRequest rq, Model model) {
        List<Location> locationList = locationDao.getAllLocation();
        model.addAttribute("locationList", locationList);
        return "location";

    }

    @RequestMapping(value = "/displayLocationDetails", method = RequestMethod.GET)
    public String displayLocationDetail(HttpServletRequest request, Model model) {
        String locationIdParameter = request.getParameter("locationId");
        int locationId = Integer.parseInt(locationIdParameter);
        Location location = locationDao.getLocationById(locationId);
        model.addAttribute("location", location);
        return "locationDetails";
    }

    @RequestMapping(value = "/addLocation", method = RequestMethod.POST)
    public String addLocation(HttpServletRequest rq, Model model) {
        Location location = new Location();
        location.setLocationName(rq.getParameter("locationName"));
        location.setStreet(rq.getParameter("street"));
        location.setCity(rq.getParameter("city"));
        location.setState(rq.getParameter("state"));
        location.setZip(Integer.parseInt(rq.getParameter("zip")));
        location.setLatitude(Double.parseDouble(rq.getParameter("latitude")));
        location.setLongitude(Double.parseDouble(rq.getParameter("longitude")));
        location.setDescription("description");
        locationDao.addLocation(location);
        return "redirect:displayLocationPage";

    }

    @RequestMapping(value = "/deleteLocation", method = RequestMethod.GET)
    public String deleteLocation(HttpServletRequest rq, Model model) {
        String locationIdparameter = rq.getParameter("locationId");
        int locationId = Integer.parseInt(locationIdparameter);
        locationDao.deleteLocation(locationId);
        return "redirect:displayLocationPage";

    }

    @RequestMapping(value = "/displayEditLocationForm", method = RequestMethod.GET)
    public String displayEditLocationForm(HttpServletRequest rq, Model model) {
        String locationIdParameter = rq.getParameter("locationId");
        int locationId = Integer.parseInt(locationIdParameter);
        Location location = locationDao.getLocationById(locationId);
        model.addAttribute("location", location);
        return "editLocation";

    }

    @RequestMapping(value = "/editLocation", method = RequestMethod.POST)
    public String editLocation(@Valid @ModelAttribute("location") Location location, BindingResult result) {
        if (result.hasErrors()) {
            return "editLocation";
        }
        locationDao.updateLocation(location);
        return "redirect:displayLocationPage";
    }

    //@sighting
    @RequestMapping(value = "/displaySightingPage", method = RequestMethod.GET)
    public String displaySighting(HttpServletRequest rq, Model model) {
        List<Sighting> sightingList = sightingDao.getAllSighting();
        List<SuperPerson> heroList = supersDao.getAllSuperPerson();
        List<Location> locationList = locationDao.getAllLocation();
        model.addAttribute("sightingList", sightingList);
        model.addAttribute("heroList", heroList);
        model.addAttribute("locationList", locationList);

        return "sighting";
    }

    @RequestMapping(value = "/displaySightingDetails", method = RequestMethod.GET)
    public String displaySightingDetail(HttpServletRequest request, Model model) {
        String sightingIdParameter = request.getParameter("sightingId");
        int sightingId = Integer.parseInt(sightingIdParameter);
        Sighting sighting = sightingDao.getSightingById(sightingId);
        model.addAttribute("sighting", sighting);
        return "sightingDetails";
    }

    @RequestMapping(value = "/addSighting", method = RequestMethod.POST)
    public String addSighting(HttpServletRequest rq, Model model) {
        // DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");

        Location location = locationDao.getLocationById(Integer.parseInt(rq.getParameter("location")));

        List<SuperPerson> supersList = new ArrayList<>();
        List<Integer> supersIdList = new ArrayList<>();
        String[] superPersonIds = rq.getParameterValues("superPersonId");
        int[] superIds = new int[superPersonIds.length];
        for (int i = 0; i < superPersonIds.length; i++) {
            superIds[i] = Integer.parseInt(superPersonIds[i]);
            supersIdList.add(superIds[i]);
        }

        for (int supersid : supersIdList) {
            SuperPerson superPerson = supersDao.getSuperPersonById(supersid);
            supersList.add(superPerson);
        }

        Sighting sighting = new Sighting();
        sighting.setDate(LocalDate.parse(rq.getParameter("date")));
        sighting.setDescription(rq.getParameter("description"));
        sighting.setLocation(location);
        sighting.setSuperPerson(supersList);
        sightingDao.addSighting(sighting);
        return "redirect:displaySightingPage";
    }

    @RequestMapping(value = "/deleteSighting", method = RequestMethod.GET)
    public String deleteSighting(HttpServletRequest rq, Model model) {
        String sightingIdParameter = rq.getParameter("sightingId");
        int sightingId = Integer.parseInt(sightingIdParameter);
        sightingDao.deletSigting(sightingId);
        return "redirect:displaySightingPage";

    }

    @RequestMapping(value = "/displayEditSightingForm", method = RequestMethod.GET)
    public String displayEditSightingForm(HttpServletRequest rq, Model model) {
        String sightingIdParameter = rq.getParameter("sightingId");
        int sightingId = Integer.parseInt(sightingIdParameter);
        List<Location> locationList = locationDao.getAllLocation();
        List<SuperPerson> heroList = supersDao.getAllSuperPerson();
        model.addAttribute("locationList", locationList);
        model.addAttribute("heroList", heroList);
        Sighting sighting = sightingDao.getSightingById(sightingId);
        model.addAttribute(sighting);

        return "editSighting";
    }

    @RequestMapping(value = "/editSighting", method = RequestMethod.POST)
    public String editSighting(HttpServletRequest rq, @Valid @ModelAttribute("sighting") Sighting sighting, BindingResult result) {
        if (result.hasErrors()) {
            return "editSighting";
        }
        List<SuperPerson> supersList = new ArrayList<>();
        List<Integer> supersIdList = new ArrayList<>();
        String[] superPersonIds = rq.getParameterValues("superPersonId");
        int[] superIds = new int[superPersonIds.length];

        for (int i = 0; i < superPersonIds.length; i++) {
            superIds[i] = Integer.parseInt(superPersonIds[i]);
            supersIdList.add(superIds[i]);
        }

        for (int supersId : supersIdList) {
            SuperPerson superPerson = supersDao.getSuperPersonById(supersId);
            supersList.add(superPerson);
        }

        sighting.setSuperPerson(supersList);
        sightingDao.updateSighting(sighting);
        return "redirect:displaySightingPage";
    }

    //organization 
    @RequestMapping(value = "/displayOrganizationPage", method = RequestMethod.GET)
    public String displayOrganizaton(HttpServletRequest rq, Model model) {
        List<Location> locationList = locationDao.getAllLocation();
        List<Organization> OrganizationList = organizationDao.getAllOrganization();
        //   List<SuperPerson> heroList = supersDao.getAllSuperPerson();
        //  model.addAttribute("heroList", heroList);
        model.addAttribute("OrganizationList", OrganizationList);
        model.addAttribute("locationList", locationList);

        return "organizations";
    }

    @RequestMapping(value = "/addOrganization", method = RequestMethod.POST)
    public String addOrganization(HttpServletRequest rq, Model model) {
        Organization organization = new Organization();
        Location location = locationDao.getLocationById(Integer.parseInt(rq.getParameter("location")));
        organization.setName(rq.getParameter("name"));
        organization.setPhone(rq.getParameter("phone"));
        organization.setDesciption(rq.getParameter("description"));
        organization.setLocation(location);
        organizationDao.addOrganization(organization);

        return "redirect:displayOrganizationPage";
    }

    @RequestMapping(value = "/deleteOrganization", method = RequestMethod.GET)
    public String deleteOrganization(HttpServletRequest rq, Model model) {
        String organizationIdParameter = rq.getParameter("organizationId");
        int organizationId = Integer.parseInt(organizationIdParameter);
        // Organization organization = organizationDao.getOrganizationById(organizationId);
        organizationDao.deleteOrganization(organizationId);
        return "redirect:displayOrganizationPage";
    }

    @RequestMapping(value = "/displayEditOrganizationForm", method = RequestMethod.GET)
    public String displayEditOrganizationForm(HttpServletRequest rq, Model model) {
        String organizationIdParameter = rq.getParameter("organizationId");
        int organizationId = Integer.parseInt(organizationIdParameter);
        List<Location> locationList = locationDao.getAllLocation();
        model.addAttribute("locationList", locationList);
        Organization organization = organizationDao.getOrganizationById(organizationId);
        model.addAttribute("organization", organization);

        return "editOrganization";

    }

    @RequestMapping(value = "/editOrganization", method = RequestMethod.POST)
    public String editOrganization(@Valid @ModelAttribute("organization") Organization organizaton, BindingResult result) {
        if (result.hasErrors()) {

            return "editOrganization";
        }
        organizationDao.updateOrganization(organizaton);
        return "redirect:displayOrganizationPage";
    }

    @RequestMapping(value = "/displayOrganizationDetails", method = RequestMethod.GET)
    public String displayOrganizationDetail(HttpServletRequest rq, Model model) {
        String organizationIdParameter = rq.getParameter("organizationId");
        int OrganizationId = Integer.parseInt(organizationIdParameter);
        Organization organization = organizationDao.getOrganizationById(OrganizationId);
        model.addAttribute("organization", organization);
        return "organizationDetails";
    }
}
