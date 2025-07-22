package dev.javokhir.talabaguide.controllers;

import dev.javokhir.talabaguide.dtos.UserLoginRequestDto;
import dev.javokhir.talabaguide.dtos.UserRegisterRequestDto;
import dev.javokhir.talabaguide.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<String> createAccount(@RequestBody @Valid UserRegisterRequestDto dto){
        return ResponseEntity.ok(userService.createAccount(dto));
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody @Valid UserLoginRequestDto dto){
        return ResponseEntity.ok(userService.login(dto));
    }
}
