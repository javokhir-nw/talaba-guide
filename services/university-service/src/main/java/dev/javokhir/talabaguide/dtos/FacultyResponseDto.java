package dev.javokhir.talabaguide.dtos;


import dev.javokhir.talabaguide.models.Faculty;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FacultyResponseDto {

    private Long id;
    private String name;
    private String about;
    private String contactNumber;
    private String email;
    private List<FacultyProgramResponseDto> facultyPrograms = new ArrayList<>();

    public FacultyResponseDto(Faculty f) {
        id = f.getId();
        name = f.getName();
        about = f.getAbout();
        contactNumber = f.getContactNumber();
        email = f.getEmail();
    }
}
