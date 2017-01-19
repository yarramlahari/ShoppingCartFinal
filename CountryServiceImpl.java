package com.company.service.impl;

import com.company.model.Country;
import com.company.service.CountryService;
import com.company.dao.CountryDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service("countryService")
public class CountryServiceImpl implements CountryService {

    @Autowired
    private CountryDAO countryDAO;

    @Transactional(readOnly = true)
    public List<Country> listOfCountries() {
            return countryDAO.listOfCountry();
        }
}
