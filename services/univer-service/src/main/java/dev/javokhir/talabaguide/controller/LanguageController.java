package dev.javokhir.talabaguide.controller;

import dev.javokhir.talabaguide.dtos.LanguageDto;
import dev.javokhir.talabaguide.services.LanguageService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/city")
@RequiredArgsConstructor
public class LanguageController {

    private final LanguageService languageService;

    @PostMapping("/create")
    @PreAuthorize("hasAuthority('create-or-update-language')")
    public ResponseEntity<String> create(@RequestBody @Valid LanguageDto dto) {
        return ResponseEntity.ok(languageService.createOrUpdate(dto));
    }

    @GetMapping("/get-all")
    @PreAuthorize("hasAuthority('get-all-languages')")
    public ResponseEntity<List<LanguageDto>> getAll() {
        return ResponseEntity.ok(languageService.getAll());
    }
}
