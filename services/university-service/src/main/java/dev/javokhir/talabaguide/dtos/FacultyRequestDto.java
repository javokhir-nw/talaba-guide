package dev.javokhir.talabaguide.dtos;

import dev.javokhir.talabaguide.models.Faculty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class FacultyRequestDto {
    private Long id;
    private String name;
    private String about;
    private String contactNumber;
    private String email;
    private Long universityId;

    public Faculty toFaculty(){
        return toFaculty(new Faculty());
    }

    public Faculty toFaculty(Faculty faculty) {
        faculty.setName(name);
        faculty.setAbout(about);
        faculty.setContactNumber(contactNumber);
        faculty.setEmail(email);
        return faculty;
    }
}
