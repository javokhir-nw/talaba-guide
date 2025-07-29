package dev.javokhir.talabaguide.services;

import dev.javokhir.talabaguide.dtos.DegreeTypeDto;
import dev.javokhir.talabaguide.models.DegreeType;
import dev.javokhir.talabaguide.repositories.DegreeTypeRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DegreeTypeService {
    private final DegreeTypeRepository degreeTypeRepository;

    public String createOrUpdate(@Valid DegreeTypeDto dto) {
        DegreeType degreeType;
        Long id = dto.getId();
        if(id != null){
            degreeType = degreeTypeRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Degree not found by id = " + id));
        } else{
            degreeType = new DegreeType();
        }
        degreeType.setName(dto.getName());
        degreeType.setCode(dto.getCode());
        degreeType.setDescription(dto.getDescription());
        degreeTypeRepository.save(degreeType);
        return "DegreeType saved successfully";
    }

    public List<DegreeTypeDto> getAll() {
        return degreeTypeRepository.findAll().stream().map(DegreeTypeDto::new).toList();
    }
}
