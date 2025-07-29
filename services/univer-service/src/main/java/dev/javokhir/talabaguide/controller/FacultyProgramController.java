package dev.javokhir.talabaguide.controller;

import dev.javokhir.talabaguide.dtos.FacultyProgramRequestDto;
import dev.javokhir.talabaguide.dtos.FacultyProgramResponseDto;
import dev.javokhir.talabaguide.services.FacultyProgramService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/faculty-program-controller")
@RequiredArgsConstructor
public class FacultyProgramController {

    private FacultyProgramService facultyProgramService;

    @PostMapping("/create-or-update")
    @PreAuthorize("hasAuthority('create-or-update-faculty-program')")
    public ResponseEntity<String> createOrUpdate(@RequestBody FacultyProgramRequestDto dto){
        return ResponseEntity.ok(facultyProgramService.createOrUpdate(dto));
    }

    @GetMapping("/get/{id}")
    @PreAuthorize("hasAuthority('get-faculty-program-by-id')")
    public ResponseEntity<FacultyProgramResponseDto> getById(@PathVariable Long id){
        return ResponseEntity.ok(facultyProgramService.getById(id));
    }

    @GetMapping("/get-all-by-faculty-id/{faculty-id}")
    @PreAuthorize("hasAuthority('get-all-faculty-programs-by-faculty-id')")
    public ResponseEntity<List<FacultyProgramResponseDto>> getAllById(@PathVariable("faculty-id") Long facultyId){
        return ResponseEntity.ok(facultyProgramService.getAllByFacultyId(facultyId));
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('delete-faculty-by-id')")
    public ResponseEntity<String> deleteById(@PathVariable("id") Long id){
        return ResponseEntity.ok(facultyProgramService.deleteById(id));
    }
}
