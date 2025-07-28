package dev.javokhir.talabaguide.dtos;

import dev.javokhir.talabaguide.models.Country;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CountryDto {
    private Long id;
    private String name;
    @NotBlank
    private String code;

    public CountryDto(Country c) {
        id = c.getId();
        name = c.getName();
        code = c.getCode();
    }
}
