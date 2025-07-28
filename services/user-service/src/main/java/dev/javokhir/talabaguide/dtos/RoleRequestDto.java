package dev.javokhir.talabaguide.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;

public record RoleRequestDto(
        Long id,
        @NotBlank
        String name,
        String description,
        @NotBlank
        String code,
        @NotEmpty
        List<Long> authoritiesIds
) {
}
