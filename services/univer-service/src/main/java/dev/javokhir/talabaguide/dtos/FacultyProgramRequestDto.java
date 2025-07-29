package dev.javokhir.talabaguide.dtos;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FacultyProgramRequestDto {
    private Long id;
    @NotBlank(message = "Enter valid name")
    private String name;
    private List<Long> degreeTypeIds;
    private Long facultyId;
    private String about;
    @DecimalMin(value = "0.0",message = "Tuition fee from must be higher than 0")
    private Double tuitionFeeFrom;
    @DecimalMin(value = "0.0",message = "Tuition fee to must be higher than 0")
    private Double tuitionFeeTo;
    @Min(value = 0,message = "Duration years must be higher higher than 0")
    private Integer durationYears;
    private Date admissionOpens;
    private Date admissionCloses;
    @NotEmpty(message = "Language is required")
    private List<Long> languageIds;
    private String requirements;
}
