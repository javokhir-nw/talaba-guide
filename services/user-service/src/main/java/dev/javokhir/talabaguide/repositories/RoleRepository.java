package dev.javokhir.talabaguide.repositories;

import dev.javokhir.talabaguide.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {
}
