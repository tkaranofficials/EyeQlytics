package com.example.EyeQlytics.EyeQlytics.controller;

import com.example.EyeQlytics.EyeQlytics.dto.ResponseWrapper;
import com.example.EyeQlytics.EyeQlytics.entity.Country;
import com.example.EyeQlytics.EyeQlytics.service.CountryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/countries")
public class CountryController {
    private final CountryService countryService;

    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @GetMapping
    public ResponseEntity<ResponseWrapper<List<Country>>> getAllCountries(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        List<Country> countries = countryService.getAllCountries(page, size);
        return ResponseEntity.ok(new ResponseWrapper<>(true, HttpStatus.OK, "Countries retrieved successfully", countries));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseWrapper<Country>> getCountryById(@PathVariable Long id) {
        Country country = countryService.getCountryById(id);
        return ResponseEntity.ok(new ResponseWrapper<>(true, HttpStatus.OK, "Country retrieved successfully", country));
    }

    @PostMapping
    public ResponseEntity<ResponseWrapper<Country>> addCountry(@RequestBody Country country) {
        Country savedCountry = countryService.addCountry(country);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponseWrapper<>(true, HttpStatus.CREATED, "Country added successfully", savedCountry));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseWrapper<Country>> updateCountry(@PathVariable Long id, @RequestBody Country country) {
        Country updatedCountry = countryService.updateCountry(id, country);
        return ResponseEntity.ok(new ResponseWrapper<>(true, HttpStatus.OK, "Country updated successfully", updatedCountry));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseWrapper<String>> deleteCountry(@PathVariable Long id) {
        countryService.deleteCountry(id);
        return ResponseEntity.ok(new ResponseWrapper<>(true, HttpStatus.OK, "Country with ID " + id + " deleted successfully", null));
    }

    @PostMapping("/fetch")
    public ResponseEntity<ResponseWrapper<List<Country>>> fetchAndSaveCountries() {
        List<Country> savedCountries = countryService.fetchAndSaveCountries();
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponseWrapper<>(true, HttpStatus.CREATED, "Countries fetched and saved successfully", savedCountries));
    }
}
