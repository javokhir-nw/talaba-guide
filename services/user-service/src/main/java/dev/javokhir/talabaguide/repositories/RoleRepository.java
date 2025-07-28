package dev.javokhir.talabaguide.repositories;

import dev.javokhir.talabaguide.models.Role;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {


    @Query("select count(r) > 0 from Role r where lower(r.code) = lower(?1)")
    boolean checkIfExistsByCode(String code);

    @Query("select count(r) > 0 from Role r where r.id != ?1 and lower(r.code) = lower(?2)")
    boolean checkIfExistsByCodeInUpdate(Long id, String code);

    @Query("select r from Role r where r.status = 'ACTIVE'")
    List<Role> findAllStatusActive();

    @Query("select r from Role r where lower(r.code) = lower(?1)")
    Optional<Role> findByCode(String roleAdmin);
}
