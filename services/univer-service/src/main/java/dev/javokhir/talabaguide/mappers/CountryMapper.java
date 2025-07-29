package dev.javokhir.talabaguide.mappers;

import dev.javokhir.talabaguide.dtos.CountryResponseDto;
import dev.javokhir.talabaguide.models.Country;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CountryMapper {

    public CountryResponseDto toDto(Country country) {
        CountryResponseDto dto = null;
        if (country != null){
           dto = CountryResponseDto.builder()
                    .id(country.getId())
                    .name(country.getName())
                    .code(country.getCode())
                    .build();
        }
        return dto;
    }
}
