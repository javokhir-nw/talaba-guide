package dev.javokhir.talabaguide.dtos;


import dev.javokhir.talabaguide.models.DegreeType;
import dev.javokhir.talabaguide.models.FacultyProgram;
import dev.javokhir.talabaguide.models.Language;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class FacultyProgramResponseDto {
    private Long id;
    private List<String> degreeTypes;
    private String about;
    private Double tuitionFeeFrom;
    private Double tuitionFeeTo;
    private Integer durationYears;
    private Date admissionOpens;
    private Date admissionCloses;
    private List<String> languages;
    private String requirements;

    public FacultyProgramResponseDto(FacultyProgram fp) {
        id = fp.getId();
        List<DegreeType> degreeTypes = fp.getDegreeTypes();
        if (degreeTypes != null && !degreeTypes.isEmpty()){
            this.degreeTypes = degreeTypes.stream().map(DegreeType::getName).toList();
        }
        about = fp.getAbout();
        tuitionFeeFrom = fp.getTuitionFeeFrom();
        tuitionFeeTo = fp.getTuitionFeeTo();
        durationYears = fp.getDurationYears();
        admissionOpens = fp.getAdmissionOpens();
        admissionCloses = fp.getAdmissionCloses();
        List<Language> languages = fp.getLanguages();
        if (languages != null && !languages.isEmpty()){
            this.languages = languages.stream().map(Language::getName).toList();
        }
        requirements = fp.getRequirements();
    }
}
