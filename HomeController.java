package com.company.controller;

import com.company.model.Country;
import com.company.model.Organization;
import com.company.service.CountryService;
import com.company.service.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/")
public class HomeController {

    private final OrganizationService organizationService;
    private final CountryService countryService;
    private String message;

    @Autowired
    public HomeController(final OrganizationService organizationService, final CountryService countryService) {
        this.organizationService = organizationService;
        this.countryService = countryService;
    }

    @RequestMapping(value="/add", method=RequestMethod.GET)
    public ModelAndView addOrganization() {
        ModelAndView modelAndView = new ModelAndView("add");
        modelAndView.addObject("organization", new Organization());
        List<Country> countries = countryService.listOfCountries();
        modelAndView.addObject("countries", countries);
        return modelAndView;
    }

    @RequestMapping(value="/add", method=RequestMethod.POST)
    public ModelAndView addingConfirm(@ModelAttribute Organization organization) {
        ModelAndView modelAndView = new ModelAndView("confirm");
        if(validateIfEmptyField(organization)) {
            organizationService.addOrganization(organization);
            message = "Organization was successfully added.";
        } else {
            message = "Organization wasn't added. Please, insert all requested data";
        }
        modelAndView.addObject("message", message);
        return modelAndView;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView list() {
        ModelAndView modelAndView = new ModelAndView("index");
        List<Organization> organizations = organizationService.listOfOrganizations();
        modelAndView.addObject("organizations", organizations);
        return modelAndView;
    }

    @RequestMapping(value="/edit/{id}", method=RequestMethod.GET)
    public ModelAndView editOrganization(@PathVariable Integer id) {
        ModelAndView modelAndView = new ModelAndView("edit");
        Organization organization = organizationService.getOrganization(id);
        modelAndView.addObject("organization", organization);
        List<Country> countries = countryService.listOfCountries();
        modelAndView.addObject("countries", countries);
        return modelAndView;
    }

    @RequestMapping(value="/edit/{id}", method=RequestMethod.POST)
    public ModelAndView editConfirm(@ModelAttribute Organization organization, @PathVariable Integer id) {
        ModelAndView modelAndView = new ModelAndView("index");
        if(validateIfEmptyField(organization)) {
            organizationService.editOrganization(organization);
        }
        List<Organization> organizations = organizationService.listOfOrganizations();
        List<Country> countries = countryService.listOfCountries();
        modelAndView.addObject("countries", countries);
        modelAndView.addObject("organizations", organizations);
        return modelAndView;
    }

    @RequestMapping(value="/remove/{id}", method=RequestMethod.GET)
    public ModelAndView removeOrganization(@PathVariable Integer id) {
        ModelAndView modelAndView = new ModelAndView("confirm");
        organizationService.removeOrganization(id);
        message = "Organization was successfully deleted.";
        modelAndView.addObject("message", message);
        return modelAndView;
    }

    private boolean validateIfEmptyField(Organization organization) {
        if(organization.getName().equals("") || organization.getAddress().equals("")) return false;
        if (organization.getMarket_cap() == null || organization.getPhone().equals("")) return false;
        if (organization.getCountry().getId() == null) return false;
        return true;
    }
}