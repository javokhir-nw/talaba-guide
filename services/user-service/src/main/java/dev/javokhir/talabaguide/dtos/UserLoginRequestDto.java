package dev.javokhir.talabaguide.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserLoginRequestDto(
        @NotBlank(message = "Enter a valid username") // ensures it's not null or only whitespace
        @Size(min = 3, max = 50, message = "Username must be between 3 and 50 characters")
        String username,
        @NotBlank(message = "Enter a valid password")
        @Size(min = 3, max = 100, message = "Password must be between 3 and 100 characters")
        String password
) {
}
