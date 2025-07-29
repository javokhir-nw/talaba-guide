package dev.javokhir.talabaguide.services;

import dev.javokhir.talabaguide.dtos.FacultyProgramRequestDto;
import dev.javokhir.talabaguide.dtos.FacultyProgramResponseDto;
import dev.javokhir.talabaguide.exceptions.MissingIdException;
import dev.javokhir.talabaguide.mappers.FacultyProgramMapper;
import dev.javokhir.talabaguide.models.FacultyProgram;
import dev.javokhir.talabaguide.models.enums.Status;
import dev.javokhir.talabaguide.repositories.FacultyProgramRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class FacultyProgramService {

    private final FacultyProgramRepository facultyProgramRepository;
    private final FacultyProgramMapper mapper;

    public List<FacultyProgramResponseDto> getAllByFacultyId(Long id) {
       return facultyProgramRepository.findAllByFacultyId(id).stream().map(FacultyProgramResponseDto::new).toList();
    }

    public String createOrUpdate(FacultyProgramRequestDto dto) {
        Long facultyId = dto.getFacultyId();
        if (facultyId == null){
            throw new MissingIdException("Faculty id is required field");
        }
        FacultyProgram fp = mapper.toFacultyProgram(dto);
        log.info("FacultyProgram saved by ID:: {}",fp.getId());
        return "Faculty program saved successfully";
    }

    public String deleteById(Long id) {
        FacultyProgram fp = facultyProgramRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Faculty program not found by id = " + id));
        fp.setStatus(Status.ACTIVE);
        facultyProgramRepository.save(fp);
        log.info("Faculty program deleted by ID:: {}",id);
        return "Faculty program deleted successfully";
    }

    public FacultyProgramResponseDto getById(Long id) {
        return new FacultyProgramResponseDto(
                facultyProgramRepository.findById(id).
                        orElseThrow(() -> new EntityNotFoundException("Faculty program not found by id = " + id)
                        )
        );
    }
}
