package dev.javokhir.talabaguide.services;

import dev.javokhir.talabaguide.config.jwt.JwtUtil;
import dev.javokhir.talabaguide.dtos.UserLoginRequestDto;
import dev.javokhir.talabaguide.dtos.UserRegisterRequestDto;
import dev.javokhir.talabaguide.exceptions.DuplicateResourceException;
import dev.javokhir.talabaguide.mappers.UserMapper;
import dev.javokhir.talabaguide.models.User;
import dev.javokhir.talabaguide.repositories.UserRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;
    private final UserMapper mapper;
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;

    @Transactional
    public String createAccount(@Valid UserRegisterRequestDto dto) {
        String username = dto.username();
        String email = dto.email();

        if (checkIsExistUsername(username,null)){
            log.warn("Try to create exist username:: {}",username);
            throw new DuplicateResourceException("username",username);
        }

        if (email != null && checkIsExistEmail(email,null)){
            log.warn("Try to create exist email:: {}",email);
            throw new DuplicateResourceException("email",email);
        }

        User user = mapper.toUser(dto);
        log.info("User successfully saved by id:: {}",user.getId());
        return jwtUtil.generateToken(user);
    }

    public Boolean checkIsExistUsername(String username,Long userId){
        return repository.checkIsExistUsername(username,userId);
    }

    public Boolean checkIsExistEmail(String email,Long userId){
        return repository.checkIsExistEmail(email,userId);
    }

    public String login(UserLoginRequestDto dto) {
        Authentication authentication =
                authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(dto.username(),dto.password()));


        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        return jwtUtil.generateToken(userDetails);
    }
}
