package dev.javokhir.talabaguide.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class FetchAuthoritiesDto {
    @NotBlank
    private String serviceName;
    @NotEmpty
    private Map<String, List<String>> authorities;
}
