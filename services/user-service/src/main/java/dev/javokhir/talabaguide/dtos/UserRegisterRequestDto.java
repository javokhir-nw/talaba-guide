package dev.javokhir.talabaguide.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;

@Builder
public record UserRegisterRequestDto(
        @NotBlank(message = "Firstname is required")
        @Size(min = 1, max = 100, message = "Firstname must be between 1 and 100 characters")
        String firstName,

        @Size(max = 100, message = "Lastname must not exceed 100 characters")
        String lastName,

        @NotBlank(message = "Username is required")
        @Size(min = 3, max = 50, message = "Username must be between 3 and 50 characters")
        String username,

        @NotBlank(message = "Password is required")
        @Size(min = 3, max = 100, message = "Password must be between 6 and 100 characters")
        String password,

        @Email(message = "Please enter a valid email address")
        @Size(max = 254, message = "Email must not exceed 254 characters")
        String email,

        @Size(max = 50, message = "Telegram chat ID must not exceed 50 characters")
        String telegramChatId,

        Long roleId
) {
}
