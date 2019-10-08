package com.ampersand.countries;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CountriesApplication
{
    static CountryList thisCountryList;

    public static void main(String[] args)
    {
        thisCountryList = new CountryList();
        SpringApplication.run(CountriesApplication.class, args);
    }

}
