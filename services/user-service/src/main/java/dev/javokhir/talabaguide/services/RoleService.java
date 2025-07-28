package dev.javokhir.talabaguide.services;


import dev.javokhir.talabaguide.dtos.AuthorityResponseDto;
import dev.javokhir.talabaguide.dtos.RoleRequestDto;
import dev.javokhir.talabaguide.dtos.RoleResponseDto;
import dev.javokhir.talabaguide.enums.Status;
import dev.javokhir.talabaguide.exceptions.DuplicateResourceException;
import dev.javokhir.talabaguide.exceptions.MissingIdException;
import dev.javokhir.talabaguide.mappers.RoleMapper;
import dev.javokhir.talabaguide.models.Role;
import dev.javokhir.talabaguide.repositories.RoleRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class RoleService {

    private final RoleMapper roleMapper;
    private final RoleRepository repository;
    private final RoleRepository roleRepository;

    public String create(@Valid RoleRequestDto dto) {

        if (repository.checkIfExistsByCode(dto.code())) {
            throw new DuplicateResourceException("role", dto.code());
        }

        Role role = roleMapper.toRole(dto);

        log.info("Role saved successfully ROLE:: {}", role);

        return "Role saved successfully!";
    }

    public String update(RoleRequestDto dto) {
        return null;
    }

    public String deleteById(Long id) {

        Role role = roleRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Role not found by id = " + id));

        role.setStatus(Status.DELETED);
        role = roleRepository.save(role);

        log.info("Role deleted successfully ROLE:: {}", role);

        return "Role deleted successfully!";
    }

    public RoleResponseDto getById(Long id) {
        Role role = roleRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Role not found by id = " + id));

        return roleMapper.toDto(role);
    }

    public List<RoleResponseDto> getAll() {
       return roleRepository.findAllStatusActive().stream().map(
                roleMapper::toDto
        ).toList();
    }
}
