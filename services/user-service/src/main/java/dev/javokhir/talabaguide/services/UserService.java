package dev.javokhir.talabaguide.services;

import dev.javokhir.talabaguide.config.jwt.JwtUtil;
import dev.javokhir.talabaguide.dtos.UserLoginRequestDto;
import dev.javokhir.talabaguide.dtos.UserRegisterRequestDto;
import dev.javokhir.talabaguide.exceptions.DuplicateResourceException;
import dev.javokhir.talabaguide.mappers.UserMapper.UserMapper;
import dev.javokhir.talabaguide.models.User;
import dev.javokhir.talabaguide.repositories.UserRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Optional;

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
            throw new DuplicateResourceException("username",username);
        }

        if (email != null && checkIsExistEmail(email,null)){
            throw new DuplicateResourceException("email",email);
        }

        User user = repository.save(mapper.toUser(dto));
        return jwtUtil.generateToken(user);
    }

    public Boolean checkIsExistUsername(String username,Long userId){
        return repository.checkIsExistUsername(username,userId);
    }

    public Boolean checkIsExistEmail(String email,Long userId){
        return repository.checkIsExistEmail(email,userId);
    }

    public Optional<UserDetails> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public String login(@Valid UserLoginRequestDto dto) {
        Authentication authentication =
                authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(dto.username(),dto.password()));


        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        return jwtUtil.generateToken(userDetails);
    }
}
