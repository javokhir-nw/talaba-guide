package dev.javokhir.talabaguide.dtos;

import dev.javokhir.talabaguide.models.DegreeType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DegreeTypeDto {
    private Long id;
    private String name;
    private String code;
    private String description;

    public DegreeTypeDto(DegreeType dt) {
        id = dt.getId();
        name = dt.getName();
        code = dt.getCode();
        description = dt.getDescription();
    }
}
