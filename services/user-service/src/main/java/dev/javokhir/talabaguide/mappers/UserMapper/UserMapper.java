package dev.javokhir.talabaguide.mappers.UserMapper;

import dev.javokhir.talabaguide.dtos.UserRegisterRequestDto;
import dev.javokhir.talabaguide.enums.Status;
import dev.javokhir.talabaguide.models.User;
import dev.javokhir.talabaguide.repositories.RoleRepository;
import dev.javokhir.talabaguide.repositories.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserMapper {

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public User toUser(UserRegisterRequestDto dto){
        User user = new User();
        user.setUsername(dto.username());
        user.setEmail(dto.email());
        user.setFirstName(dto.firstName());
        user.setLastName(dto.lastName());
        user.setPassword(passwordEncoder.encode(dto.password())); // todo hash password
        user.setStatus(Status.ACTIVE);
        user.setRole(
                roleRepository
                        .findById(dto.roleId())
                        .orElseThrow(()
                                -> new EntityNotFoundException("Role is not exist by id:: " + dto.roleId())));
        user.setTelegramChatId(dto.telegramChatId());
        return userRepository.save(user);
    }
}
