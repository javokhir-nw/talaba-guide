package dev.javokhir.talabaguide.dtos;

import dev.javokhir.talabaguide.models.Language;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class LanguageDto {
    private Long id;
    @NotBlank(message = "Enter valid name")
    private String name;
    @NotBlank(message = "Enter valid code")
    private String code;

    public LanguageDto(Language language) {
        id = language.getId();
        name = language.getName();
        code = language.getCode();
    }
}
