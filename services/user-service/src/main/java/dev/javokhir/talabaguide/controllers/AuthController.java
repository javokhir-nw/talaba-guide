package dev.javokhir.talabaguide.controllers;

import dev.javokhir.talabaguide.dtos.FetchAuthoritiesDto;
import dev.javokhir.talabaguide.dtos.UserLoginRequestDto;
import dev.javokhir.talabaguide.dtos.UserRegisterRequestDto;
import dev.javokhir.talabaguide.services.AuthorityService;
import dev.javokhir.talabaguide.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;
    private final AuthorityService authorityService;

    @PostMapping("/register")
    public ResponseEntity<String> createAccount(@RequestBody @Valid UserRegisterRequestDto dto) {
        return ResponseEntity.ok(userService.createAccount(dto));
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody @Valid UserLoginRequestDto dto) {
        return ResponseEntity.ok(userService.login(dto));
    }

    @PostMapping("/fetch-authorities")
    @PreAuthorize("hasRole('ROLE_INTERNAL')")
    public ResponseEntity<String> fetchAuthorities(@RequestBody FetchAuthoritiesDto dto) {
        return ResponseEntity.ok(authorityService.fetchAuthorities(dto));
    }
}
