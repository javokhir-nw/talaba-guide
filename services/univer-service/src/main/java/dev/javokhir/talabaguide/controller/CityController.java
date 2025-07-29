package dev.javokhir.talabaguide.controller;

import dev.javokhir.talabaguide.dtos.CityDto;
import dev.javokhir.talabaguide.services.CityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/city")
@RequiredArgsConstructor
public class CityController {

    private final CityService cityService;

    @PostMapping("/create")
    @PreAuthorize("hasAuthority('create-or-update-city')")
    public ResponseEntity<String> create(@RequestBody CityDto dto){
        return ResponseEntity.ok(cityService.createOrUpdate(dto));
    }

    @GetMapping("/get-all-by-country-id/{countryId}")
    @PreAuthorize("hasAuthority('get-all-cities-by-country-id')")
    public ResponseEntity<List<CityDto>> getAllByCountryId(@PathVariable Long countryId){
        return ResponseEntity.ok(cityService.getAllByCountryId(countryId));
    }
}
