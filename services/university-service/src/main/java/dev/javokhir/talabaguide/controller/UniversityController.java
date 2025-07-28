package dev.javokhir.talabaguide.controller;

import dev.javokhir.talabaguide.dtos.UniversityDto;
import dev.javokhir.talabaguide.services.UniversityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/university")
public class UniversityController {

    private final UniversityService universityService;

    /***
     * Agar id berilsa o'sha id bilan
     * saqlangan universitetni topib update qiladi aksincha create qiladi
     * @param dto
     * @return String
     */
    @PostMapping("/create-or-update")
    @PreAuthorize("hasAuthority('create-university')")
    public ResponseEntity<String> createOrUpdate(@RequestBody UniversityDto dto){
        return ResponseEntity.ok(universityService.createOrUpdate(dto));
    }

    @GetMapping("/get/{id}")
    @PreAuthorize("hasAuthority('get-university-by-id')")
    public ResponseEntity<UniversityDto> getUniversityById(@PathVariable("id") Long id){
        return ResponseEntity.ok(universityService.getById(id));
    }

    @GetMapping("/get-all")
    @PreAuthorize("hasAuthority('get-all-universities')")
    public ResponseEntity<List<UniversityDto>> getAllUniversities(){
        return ResponseEntity.ok(universityService.getAllUniversities());
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('delete-university-by-id')")
    public ResponseEntity<String> deleteById(@PathVariable("id") Long id){
        return ResponseEntity.ok(universityService.deleteById(id));
    }
}
