package ro.fasttrackit.countrydemo.service;

import org.springframework.stereotype.Service;
import ro.fasttrackit.countrydemo.domain.Continent;
import ro.fasttrackit.countrydemo.domain.Country;
import ro.fasttrackit.countrydemo.domain.CountryFileReader;
import ro.fasttrackit.countrydemo.exception.CountryNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CountryService {
    private final List<Country> countries;

    public CountryService() {
        this.countries = new CountryFileReader().readCountries();
    }

    public List<Country> getAllCountries() {
        return new ArrayList<>(countries);
    }

    public List<String> getAllCountriesNames() {
        return countries.stream()
                .map(Country::getName)
                .collect(Collectors.toList());
    }

    public String getCapitalByCountryId(UUID countryId) {
        return getCountryById(countryId).getCapital();
    }

    public long getPopulationByCountryId(UUID countryId) {
        return getCountryById(countryId).getPopulation();
    }

    public List<Country> getCountriesByContinent(Continent continentName) {
        return countries.stream()
                .filter(country -> country.getContinent().equals(continentName))
                .collect(Collectors.toList());
    }

    public List<String> getCountryNeighboursById(UUID countryId) {
        return getCountryById(countryId).getNeighbours();
    }

    public List<Country> getCountriesInContinentWithPopulationLargerThan(Continent continentName, long population) {
        return getCountriesByContinent(continentName).stream()
                .filter(country -> country.getPopulation() >= population)
                .collect(Collectors.toList());
    }

    public List<Country> getCountriesWithNeighbours(String includedNeighbourCode, String excludedNeighbourCode) {
        return countries.stream()
                .filter(country -> country.getNeighbours().contains(includedNeighbourCode) && !country.getNeighbours().contains(excludedNeighbourCode))
                .collect(Collectors.toList());
    }

    public Map<String, Long> getMapFromCountryToPopulation() {
        return countries.stream()
                .collect(Collectors.toMap(Country::getName, Country::getPopulation));
    }

    public Map<Continent, List<Country>> getMapFromContinentToCountries() {
        return countries.stream()
                .collect(Collectors.groupingBy(Country::getContinent));
    }

    private Country getCountryById(UUID countryId) {
        return countries.stream()
                .filter(country -> country.getId().equals(countryId))
                .findFirst()
                .orElseThrow(() -> new CountryNotFoundException(countryId));
    }
}
