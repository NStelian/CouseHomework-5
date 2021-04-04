package ro.fasttrackit.countrydemo.controller;

import org.springframework.web.bind.annotation.*;
import ro.fasttrackit.countrydemo.domain.Continent;
import ro.fasttrackit.countrydemo.domain.Country;
import ro.fasttrackit.countrydemo.service.CountryService;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
public class CountryController {
    private final CountryService service;

    public CountryController(CountryService service) {
        this.service = service;
    }

    @GetMapping("countries")
    List<Country> getAllCountries() {
        return service.getAllCountries();
    }

    @GetMapping("countries/names")
    List<String> getAllCountriesNames() {
        return service.getAllCountriesNames();
    }

    @GetMapping("countries/{countryId}/capital")
    String getCapitalByCountryId(@PathVariable UUID countryId) {
        return service.getCapitalByCountryId(countryId);
    }

    @GetMapping("countries/{countryId}/population")
    long getPopulationByCountryId(@PathVariable UUID countryId) {
        return service.getPopulationByCountryId(countryId);
    }

    @GetMapping("continents/{continentName}/countries")
    List<Country> getPopulationByCountryId(@PathVariable Continent continentName) {
        return service.getCountriesByContinent(continentName);
    }

    @GetMapping("countries/{countryId}/neighbours")
    List<String> getCountryNeighboursById(@PathVariable UUID countryId) {
        return service.getCountryNeighboursById(countryId);
    }

    @GetMapping("continents/{continentName}")
    List<Country> getCountriesInContinentWithPopulationLargerThan(@PathVariable Continent continentName, @RequestParam long population) {
        return service.getCountriesInContinentWithPopulationLargerThan(continentName, population);
    }

    @GetMapping("countries/neighbours")
    List<Country> getCountriesWithNeighbours(@RequestParam String includedNeighbourCode, @RequestParam String excludedNeighbourCode) {
        return service.getCountriesWithNeighbours(includedNeighbourCode, excludedNeighbourCode);
    }

    @GetMapping("countries/population")
    Map<String, Long> getMapFromCountryToPopulation() {
        return service.getMapFromCountryToPopulation();
    }

    @GetMapping("continents/countries")
    Map<Continent, List<Country>> getMapFromContinentToCountries() {
        return service.getMapFromContinentToCountries();
    }
}
