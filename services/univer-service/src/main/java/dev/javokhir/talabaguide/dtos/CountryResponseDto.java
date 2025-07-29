package dev.javokhir.talabaguide.dtos;

import lombok.Builder;

@Builder
public record CountryResponseDto(
        Long id,
        String name,
        String code
) {
}
