package dev.javokhir.talabaguide.controller;

import dev.javokhir.talabaguide.dtos.DegreeTypeDto;
import dev.javokhir.talabaguide.services.DegreeTypeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/city")
@RequiredArgsConstructor
public class DegreeTypeController {

    private final DegreeTypeService degreeTypeService;

    @PostMapping("/create")
    @PreAuthorize("hasAuthority('create-or-update-degree-type')")
    public ResponseEntity<String> create(@RequestBody @Valid DegreeTypeDto dto) {
        return ResponseEntity.ok(degreeTypeService.createOrUpdate(dto));
    }

    @GetMapping("/get-all")
    @PreAuthorize("hasAuthority('get-all-degree-type')")
    public ResponseEntity<List<DegreeTypeDto>> getAll() {
        return ResponseEntity.ok(degreeTypeService.getAll());
    }
}
