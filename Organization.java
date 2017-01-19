package com.company.model;

import javax.persistence.*;

@Entity
public class Organization {

    @Id
    @GeneratedValue
    private Integer id;
    private String name;
    @OneToOne(cascade=CascadeType.PERSIST)
    @JoinColumn(name="country")
    private Country country;
    private String address;
    private String phone;
    private Long market_cap;

    public Organization(Integer id, String name, Country country,  String address, String phone, Long market_cap) {
        this.id = id;
        this.name = name;
        this.country = country;
        this.address = address;
        this.phone = phone;
        this.market_cap = market_cap;
    }

    public Organization() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Long getMarket_cap() {
        return market_cap;
    }

    public void setMarketCap(Long market_cap) {
        this.market_cap = market_cap;
    }
}