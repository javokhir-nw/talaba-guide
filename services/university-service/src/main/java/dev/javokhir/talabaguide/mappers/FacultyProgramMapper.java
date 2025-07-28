package dev.javokhir.talabaguide.mappers;

import dev.javokhir.talabaguide.dtos.FacultyProgramRequestDto;
import dev.javokhir.talabaguide.models.FacultyProgram;
import dev.javokhir.talabaguide.models.enums.Status;
import dev.javokhir.talabaguide.repositories.DegreeTypeRepository;
import dev.javokhir.talabaguide.repositories.FacultyProgramRepository;
import dev.javokhir.talabaguide.repositories.FacultyRepository;
import dev.javokhir.talabaguide.repositories.LanguageRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FacultyProgramMapper {
    private final FacultyRepository facultyRepository;
    private final DegreeTypeRepository degreeTypeRepository;
    private final FacultyProgramRepository facultyProgramRepository;
    private final LanguageRepository languageRepository;

    public FacultyProgram toFacultyProgram(FacultyProgramRequestDto dto) {
        Long id = dto.getId();
        FacultyProgram fp;
        if (id != null){
            fp = facultyProgramRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Faculty program not found by id = " + id));
        } else{
            fp = new FacultyProgram();
        }
        fp.setName(dto.getName());
        fp.setDegreeTypes(degreeTypeRepository.findAllById(dto.getDegreeTypeIds()));
        fp.setFaculty(facultyRepository.findById(dto.getFacultyId())
                .orElseThrow(() -> new EntityNotFoundException("Faculty not fount by id = " + id)));
        fp.setAbout(dto.getAbout());
        fp.setTuitionFeeFrom(dto.getTuitionFeeFrom());
        fp.setTuitionFeeTo(dto.getTuitionFeeTo());
        fp.setDurationYears(dto.getDurationYears());
        fp.setAdmissionOpens(dto.getAdmissionOpens());
        fp.setAdmissionCloses(dto.getAdmissionCloses());
        fp.setLanguages(languageRepository.findAllById(dto.getLanguageIds()));
        fp.setRequirements(dto.getRequirements());
        fp.setStatus(Status.ACTIVE);
        return facultyProgramRepository.save(fp);
    }
}
