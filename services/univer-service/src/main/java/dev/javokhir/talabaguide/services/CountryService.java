package dev.javokhir.talabaguide.services;

import dev.javokhir.talabaguide.dtos.CountryDto;
import dev.javokhir.talabaguide.models.Country;
import dev.javokhir.talabaguide.repositories.CountryRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CountryService {

    private final CountryRepository  countryRepository;

    public String createOrUpdate(CountryDto dto) {
        Country country;
        Long id = dto.getId();
        if(id != null){
           country = countryRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Country not found by id = " + id));
        } else{
            country = new Country();
        }
        country.setName(dto.getName());
        country.setCode(dto.getCode());
        countryRepository.save(country);
        return "Country saved successfully";
    }

    public List<CountryDto> getAll() {
      return  countryRepository.findAll().stream().map(CountryDto::new).toList();
    }
}
