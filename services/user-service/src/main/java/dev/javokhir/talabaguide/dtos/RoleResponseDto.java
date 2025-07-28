package dev.javokhir.talabaguide.dtos;

import lombok.Builder;

import java.util.List;

@Builder
public record RoleResponseDto(
        Long id,
        String name,
        String description,
        String code,
        List<AuthorityResponseDto> authorityResponseDtoList
) {
}
