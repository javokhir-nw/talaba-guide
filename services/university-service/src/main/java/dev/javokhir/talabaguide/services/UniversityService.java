package dev.javokhir.talabaguide.services;

import dev.javokhir.talabaguide.dtos.UniversityDto;
import dev.javokhir.talabaguide.models.University;
import dev.javokhir.talabaguide.models.enums.Status;
import dev.javokhir.talabaguide.repositories.CityRepository;
import dev.javokhir.talabaguide.repositories.FacultyRepository;
import dev.javokhir.talabaguide.repositories.UniversityRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class UniversityService {

    private final UniversityRepository universityRepository;
    private final CityRepository cityRepository;
    private final FacultyService facultyService;

    public String createOrUpdate(UniversityDto dto) {
        University university;
        Long universityId = dto.getId();
        if (universityId != null) {
            university = dto.toUniversity(universityRepository.findById(universityId)
                    .orElseThrow(() -> {
                        log.error("University not found by ID:: {}", universityId);
                        return new EntityNotFoundException("University not found by id = " + universityId);
                    }));
        } else {
            university = dto.toUniversity();
        }
        Long cityId = dto.getCityId();
        if (cityId != null){
            university.setCity(cityRepository.findById(cityId).orElseThrow(
                            () -> new EntityNotFoundException("City not found by id = " + cityId)));
        }
       university = universityRepository.save(university);
       log.info("University saved by ID:: {}",university.getId());
       return "University saved successfully";
    }

    public UniversityDto getById(Long id) {
        UniversityDto dto = new UniversityDto(
                universityRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("University not found by id = " + id)
                )
        );
        dto.setFaculties(facultyService.getAllByUniversityId(id));
        return dto;
    }

    public List<UniversityDto> getAllUniversities() {
       return universityRepository.findAllStatusActive().stream().map(UniversityDto::new).toList();
    }

    public String deleteById(Long id) {
        University university = universityRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("University not found by id = " + id));
        university.setStatus(Status.DELETED);
        universityRepository.save(university);
        log.info("University deleted by ID:: {}",id);
        return "University deleted successfully";
    }
}
