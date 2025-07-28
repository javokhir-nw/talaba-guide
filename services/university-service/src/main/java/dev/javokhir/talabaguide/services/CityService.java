package dev.javokhir.talabaguide.services;

import dev.javokhir.talabaguide.dtos.CityDto;
import dev.javokhir.talabaguide.models.City;
import dev.javokhir.talabaguide.repositories.CityRepository;
import dev.javokhir.talabaguide.repositories.CountryRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CityService {
    private final CountryRepository countryRepository;
    private final CityRepository cityRepository;

    public String createOrUpdate(CityDto dto) {
        City city;
        Long id = dto.id();
        if (id != null){
            city = cityRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("City not found by id = " + id));
        } else{
           city = new City();
        }
        city.setCode(dto.code());
        city.setCountry(countryRepository.findById(dto.countryId())
                .orElseThrow(() -> new EntityNotFoundException("Country not found by id = " + dto.countryId())));
        city.setName(dto.name());
        cityRepository.save(city);
        return "City saved successfully";
    }

    public List<CityDto> getAllByCountryId(Long countryId) {
        return cityRepository.findAllByCountryId(countryId)
                .stream().map(c -> new CityDto(
                        c.getId(),
                        c.getName(),
                        c.getCode(),
                        countryId)).toList();
    }
}
