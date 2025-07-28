package dev.javokhir.talabaguide.dtos;

import lombok.Builder;

@Builder
public record AuthorityResponseDto(
        Long id,
        String name,
        String description
) {
}
