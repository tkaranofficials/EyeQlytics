package com.example.EyeQlytics.EyeQlytics.service;

import com.example.EyeQlytics.EyeQlytics.entity.Country;
import com.example.EyeQlytics.EyeQlytics.exception.ApiRequestException;
import com.example.EyeQlytics.EyeQlytics.exception.DatabaseOperationException;
import com.example.EyeQlytics.EyeQlytics.exception.ResourceNotFoundException;
import com.example.EyeQlytics.EyeQlytics.repository.CountryRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.client.RestClientException;

import java.util.List;
import java.util.Optional;

@Service
public class CountryService {
    private final CountryRepository countryRepository;
    private final RestTemplate restTemplate;

    public CountryService(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
        this.restTemplate = new RestTemplate();
    }

    public List<Country> fetchAndSaveCountries() {
        String apiUrl = "https://restcountries.com/v3.1/all";
        try {
            Country[] countries = restTemplate.getForObject(apiUrl, Country[].class);

            if (countries == null || countries.length == 0) {
                throw new ApiRequestException("No country data received from the API.");
            }

            return countryRepository.saveAll(List.of(countries));

        } catch (RestClientException e) {
            throw new ApiRequestException("Failed to fetch countries from external API: " + e.getMessage());
        } catch (Exception e) {
            throw new DatabaseOperationException("Error saving countries to the database: " + e.getMessage());
        }
    }

    public List<Country> getAllCountries(int page, int size) {
        PageRequest pageable = PageRequest.of(page, size);
        Page<Country> countryPage = countryRepository.findAll(pageable);

        if (countryPage.isEmpty()) {
            throw new ResourceNotFoundException("No countries found in the database.");
        }

        return countryPage.getContent();
    }

    public Country getCountryById(Long id) {
        return countryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Country with ID " + id + " not found."));
    }

    public Country addCountry(Country country) {
        try {
            return countryRepository.save(country);
        } catch (Exception e) {
            throw new DatabaseOperationException("Error adding country: " + e.getMessage());
        }
    }

    public Country updateCountry(Long id, Country updatedCountry) {
        Country existingCountry = countryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Country with ID " + id + " not found."));

        existingCountry.setCca3(updatedCountry.getCca3());
        existingCountry.setCommonName(updatedCountry.getCommonName());
        existingCountry.setOfficialName(updatedCountry.getOfficialName());
        existingCountry.setRegion(updatedCountry.getRegion());
        existingCountry.setSubregion(updatedCountry.getSubregion());
        existingCountry.setPopulation(updatedCountry.getPopulation());
        existingCountry.setArea(updatedCountry.getArea());
        existingCountry.setFlag(updatedCountry.getFlag());
        existingCountry.setFlagPng(updatedCountry.getFlagPng());
        existingCountry.setCapital(updatedCountry.getCapital());

        try {
            return countryRepository.save(existingCountry);
        } catch (Exception e) {
            throw new DatabaseOperationException("Error updating country with ID " + id + ": " + e.getMessage());
        }
    }

    public void deleteCountry(Long id) {
        if (!countryRepository.existsById(id)) {
            throw new ResourceNotFoundException("Country with ID " + id + " not found.");
        }
        try {
            countryRepository.deleteById(id);
        } catch (Exception e) {
            throw new DatabaseOperationException("Error deleting country with ID " + id + ": " + e.getMessage());
        }
    }
}
