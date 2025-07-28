package dev.javokhir.talabaguide.repositories;

import dev.javokhir.talabaguide.models.DegreeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DegreeTypeRepository extends JpaRepository<DegreeType, Long> {
}
