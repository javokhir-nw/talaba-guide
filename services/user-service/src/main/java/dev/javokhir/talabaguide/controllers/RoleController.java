package dev.javokhir.talabaguide.controllers;


import dev.javokhir.talabaguide.dtos.RoleRequestDto;
import dev.javokhir.talabaguide.dtos.RoleResponseDto;
import dev.javokhir.talabaguide.services.RoleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/role")
@RequiredArgsConstructor
public class RoleController {

    private final RoleService roleService;

    @PostMapping("/create")
    @PreAuthorize("hasAuthority('create-role')")
    public ResponseEntity<String> create(@RequestBody @Valid RoleRequestDto dto){
        return ResponseEntity.ok(roleService.create(dto));
    }

    @PostMapping("/update")
    @PreAuthorize("hasAuthority('update-role')")
    public ResponseEntity<String> update(@RequestBody @Valid RoleRequestDto dto){
        return ResponseEntity.ok(roleService.update(dto));
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('delete-role')")
    public ResponseEntity<String> delete(@PathVariable("id") Long id){
        return ResponseEntity.ok(roleService.deleteById(id));
    }

    @GetMapping("/get/{id}")
    @PreAuthorize("hasAuthority('get-role-by-id')")
    public ResponseEntity<RoleResponseDto> getById(@PathVariable("id") Long id){
        return ResponseEntity.ok(roleService.getById(id));
    }

    @GetMapping("/get-all")
    @PreAuthorize("hasAuthority('get-all-roles')")
    public ResponseEntity<List<RoleResponseDto>> getAll(){
        return ResponseEntity.ok(roleService.getAll());
    }
}
