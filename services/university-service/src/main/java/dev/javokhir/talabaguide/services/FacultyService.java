package dev.javokhir.talabaguide.services;

import dev.javokhir.talabaguide.dtos.FacultyRequestDto;
import dev.javokhir.talabaguide.dtos.FacultyResponseDto;
import dev.javokhir.talabaguide.models.Faculty;
import dev.javokhir.talabaguide.models.enums.Status;
import dev.javokhir.talabaguide.repositories.FacultyRepository;
import dev.javokhir.talabaguide.repositories.UniversityRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class FacultyService {

    private final FacultyRepository facultyRepository;
    private final UniversityRepository universityRepository;
    private final FacultyProgramService facultyProgramService;

    public List<FacultyResponseDto> getAllByUniversityId(Long universityId) {
        return facultyRepository.findAllByUniversityId(universityId).stream().map(FacultyResponseDto::new).toList();
    }

    @Transactional
    public String createOrUpdate(FacultyRequestDto dto) {
        Long id = dto.getId();
        Faculty faculty;
        if (id != null){
            faculty = facultyRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Faculty not found by id = " + id));
            faculty = dto.toFaculty(faculty);
        }else{
            faculty = dto.toFaculty();
        }
        Long universityId = dto.getUniversityId();
        if (universityId != null){
            faculty.setUniversity(universityRepository.findById(universityId).orElseThrow(() -> new EntityNotFoundException("Faculty not found by id = " + id)));
        }
        faculty = facultyRepository.save(faculty);
        log.info("Faculty saved with ID:: {}",faculty.getId());
        return "Faculty successfully saved";
    }

    public FacultyResponseDto getById(Long id) {
        FacultyResponseDto dto = new FacultyResponseDto(
                facultyRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Faculty not found by id = " + id))
        );
        dto.setFacultyPrograms(facultyProgramService.getAllByFacultyId(id));
        return dto;
    }

    public String deleteById(Long id) {
        Faculty faculty = facultyRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Faculty not found by id = " + id));
        faculty.setStatus(Status.ACTIVE);
        facultyRepository.save(faculty);
        log.info("Faculty deleted by ID:: {}",id);
        return "Faculty deleted by id = " + id;
    }
}
