package dev.javokhir.talabaguide.controller;

import dev.javokhir.talabaguide.dtos.CountryDto;
import dev.javokhir.talabaguide.services.CountryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/country")
@RequiredArgsConstructor
public class CountryController {

    private final CountryService countryService;

    @PostMapping("/create-or-update")
    @PreAuthorize("hasAuthority('create-or-update-country')")
    public ResponseEntity<String> createOrUpdateCountry(@RequestBody CountryDto dto){
        return ResponseEntity.ok(countryService.createOrUpdate(dto));
    }

    @GetMapping("/get-all")
    @PreAuthorize("hasAuthority('get-all-countries')")
    public ResponseEntity<List<CountryDto>> getAllCountries(){
        return ResponseEntity.ok(countryService.getAll());
    }
}
