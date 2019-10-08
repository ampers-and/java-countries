package com.ampersand.countries;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping("/data")
public class CountryController
{
    //localhost:2020/data/names/all <- all alphabetically
    @GetMapping(value = "/names/all",
                produces = {"application/json"})
    public ResponseEntity<?> getAllCountries()
    {
        CountriesApplication.thisCountryList.countryList.sort((c1, c2) -> c1.getName().compareToIgnoreCase(c2.getName()));
        return new ResponseEntity<>(CountriesApplication.thisCountryList.countryList, HttpStatus.OK);
    }

    //localhost:2020/data/names/start/{letter} <- alphabetically all that start with char {letter}
    @GetMapping(value = "/names/start/{letter}",
                produces = {"application/json"})
    public ResponseEntity<?> getCountryByFirstLetter(
            @PathVariable
                    char letter)
    {
        ArrayList<Country> rtnCountry = CountriesApplication.thisCountryList.findCountries(c -> c.getName()
                .toUpperCase()
                .charAt(0) == Character.toUpperCase(letter));
        rtnCountry.sort((c1, c2) -> c1.getName()
                .compareToIgnoreCase(c2.getName()));
        return new ResponseEntity<>(rtnCountry,
                HttpStatus.OK);
    }

    //localhost:2020/data/names/size/{number} <- all alphabetically where name.length >= int {number}
    @GetMapping(value = "/names/size/{number}",
            produces = {"application/json"})
    public ResponseEntity<?> getCountryByNameLength(
            @PathVariable
                    int number)
    {
        ArrayList<Country> rtnCountry = CountriesApplication.thisCountryList.findCountries(c -> c.getName()
                .length() >= number);
        rtnCountry.sort((c1, c2) -> c1.getName()
                .compareToIgnoreCase(c2.getName()));
        return new ResponseEntity<>(rtnCountry,
                HttpStatus.OK);
    }

    //localhost:2020/data/population/size/{people} <- all countries w/ .population >= int {people}
    @GetMapping(value = "/population/size/{people}",
            produces = {"application/json"})
    public ResponseEntity<?> getCountriesByPopulation(
            @PathVariable
                    int people)
    {
        ArrayList<Country> rtnCountry = CountriesApplication.thisCountryList.findCountries(c -> c.getPopulation() >= people);
        rtnCountry.sort((c1, c2) -> (int) (c1.getPopulation() - c2.getPopulation()));
        return new ResponseEntity<>(rtnCountry,
                HttpStatus.OK);
    }

    //localhost:2020/data/population/min <- smallest population country
    @GetMapping(value = "/population/min",
            produces = {"application/json"})
    public ResponseEntity<?> getCountryWithMinPopulation()
    {
        ArrayList<Country> rtnCountry = CountriesApplication.thisCountryList.findCountries(c -> c.getPopulation() >= 0);
        rtnCountry.sort((c1, c2) -> (int) (c1.getPopulation() - c2.getPopulation()));
        return new ResponseEntity<>(rtnCountry.get(0),
                HttpStatus.OK);
    }

    //localhost:2020/data/population/max <- largest population country
    @GetMapping(value = "/population/max",
            produces = {"application/json"})
    public ResponseEntity<?> getCountryWithMaxPopulation()
    {
        ArrayList<Country> rtnCountry = CountriesApplication.thisCountryList.findCountries(c -> c.getPopulation() >= 0);
        rtnCountry.sort((c1, c2) -> (int) (c1.getPopulation() - c2.getPopulation()));
        return new ResponseEntity<>(rtnCountry.get(rtnCountry.size() - 1),
                HttpStatus.OK);
    }

    //localhost:2020/data/population/median <- STRETCH; country w/ median population

    //localhost:2020/data/age/age/{age} <- countries w/ .medianAge >= int {age}
    @GetMapping(value = "/age/age/{age}",
            produces = {"application/json"})
    public ResponseEntity<?> getCountriesByMedianAge(
            @PathVariable
                    int age)
    {
        ArrayList<Country> rtnCountry = CountriesApplication.thisCountryList.findCountries(c -> c.getMedianAge() >= age);
        rtnCountry.sort((c1, c2) -> (int) (c1.getMedianAge() - c2.getMedianAge()));
        return new ResponseEntity<>(rtnCountry,
                HttpStatus.OK);
    }

    //localhost:2020/data/age/min <- smallest medianAge country
    @GetMapping(value = "/age/min",
            produces = {"application/json"})
    public ResponseEntity<?> getCountryWithMinMedianAge()
    {
        ArrayList<Country> rtnCountry = CountriesApplication.thisCountryList.findCountries(c -> c.getMedianAge() >= 0);
        rtnCountry.sort((c1, c2) -> (int) (c1.getMedianAge() - c2.getMedianAge()));
        return new ResponseEntity<>(rtnCountry.get(0),
                HttpStatus.OK);
    }

    //localhost:2020/data/age/max <- largest medianAge country
    @GetMapping(value = "/age/max",
            produces = {"application/json"})
    public ResponseEntity<?> getCountryWithMaxMedianAge()
    {
        ArrayList<Country> rtnCountry = CountriesApplication.thisCountryList.findCountries(c -> c.getMedianAge() >= 0);
        rtnCountry.sort((c1, c2) -> (int) (c1.getMedianAge() - c2.getMedianAge()));
        return new ResponseEntity<>(rtnCountry.get(rtnCountry.size() - 1),
                HttpStatus.OK);
    }

    //localhost:2020/data/age/median <- STRETCH; median medianAge country
}
