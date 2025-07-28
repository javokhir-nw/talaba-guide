package dev.javokhir.talabaguide.mappers;


import dev.javokhir.talabaguide.dtos.AuthorityResponseDto;
import dev.javokhir.talabaguide.dtos.RoleRequestDto;
import dev.javokhir.talabaguide.dtos.RoleResponseDto;
import dev.javokhir.talabaguide.enums.Status;
import dev.javokhir.talabaguide.models.Authority;
import dev.javokhir.talabaguide.models.Role;
import dev.javokhir.talabaguide.repositories.RoleRepository;
import dev.javokhir.talabaguide.services.AuthorityService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class RoleMapper {

    private final RoleRepository roleRepository;
    private final AuthorityService authorityService;

    @Transactional
    public Role toRole(RoleRequestDto dto) {
        Role role;
        if (dto.id() != null) {
            role = roleRepository.findById(dto.id())
                    .orElseThrow(() -> new EntityNotFoundException("Role not found by id = " + dto.id()));
        } else {
            role = new Role();
        }

        role.setName(dto.name());
        role.setCode(dto.code());
        role.setDescription(dto.description());
        role.setStatus(Status.ACTIVE);

        Set<Authority> authorities = authorityService.findAllByIds(dto.authoritiesIds());
        role.setAuthorities(authorities);

        return roleRepository.save(role);
    }

    public RoleResponseDto toDto(Role role) {
        List<AuthorityResponseDto> list = role.getAuthorities().stream().map(
                a -> new AuthorityResponseDto(
                        a.getId(),
                        a.getName(),
                        a.getDescription()
                )
        ).toList();

        return new RoleResponseDto(
                role.getId(),
                role.getName(),
                role.getDescription(),
                role.getCode(),
                list
        );
    }
}
