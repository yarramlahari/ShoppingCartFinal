package com.company.dao;

import com.company.model.Organization;
import java.util.List;

public interface OrganizationDAO {
    List<Organization> listOfOrganizations();
    void editOrganization(Organization organization);
    void addOrganization(Organization organization);
    void removeOrganization(int id);
    Organization getOrganization(int id);
}

