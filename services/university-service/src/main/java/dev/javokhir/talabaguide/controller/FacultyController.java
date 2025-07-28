package dev.javokhir.talabaguide.controller;

import dev.javokhir.talabaguide.dtos.FacultyRequestDto;
import dev.javokhir.talabaguide.dtos.FacultyResponseDto;
import dev.javokhir.talabaguide.dtos.UniversityDto;
import dev.javokhir.talabaguide.services.FacultyService;
import dev.javokhir.talabaguide.services.UniversityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/faculty")
public class FacultyController {

    private final FacultyService facultyService;

    /***
     * Agar id berilsa o'sha id bilan
     * saqlangan fakultetni topib update qiladi aksincha create qiladi
     * @param dto
     * @return String
     */
    @PostMapping("/create-or-update")
    @PreAuthorize("hasAuthority('create-or-update-faculty')")
    public ResponseEntity<String> createOrUpdate(@RequestBody FacultyRequestDto dto){
        return ResponseEntity.ok(facultyService.createOrUpdate(dto));
    }

    @GetMapping("/get/{id}")
    @PreAuthorize("hasAuthority('get-faculty-by-id')")
    public ResponseEntity<FacultyResponseDto> getById(@PathVariable("id") Long id){
        return ResponseEntity.ok(facultyService.getById(id));
    }

    @GetMapping("/get-all-by-university-id/{universityId}")
    @PreAuthorize("hasAuthority('get-all-faculties-by-university-id')")
    public ResponseEntity<List<FacultyResponseDto>> getAllUniversities(@PathVariable Long universityId){
        return ResponseEntity.ok(facultyService.getAllByUniversityId(universityId));
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('delete-faculy-by-id')")
    public ResponseEntity<String> deleteById(@PathVariable("id") Long id){
        return ResponseEntity.ok(facultyService.deleteById(id));
    }
}
