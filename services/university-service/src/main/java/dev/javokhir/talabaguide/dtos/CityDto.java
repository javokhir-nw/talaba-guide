package dev.javokhir.talabaguide.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record CityDto(
        Long id,
        @NotBlank
        String name,
        @NotBlank
        String code,
        @NotNull
        Long countryId
) {
}
