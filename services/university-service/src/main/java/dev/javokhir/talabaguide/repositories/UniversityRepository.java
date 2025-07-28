package dev.javokhir.talabaguide.repositories;

import dev.javokhir.talabaguide.models.University;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UniversityRepository extends JpaRepository<University, Long> {

    @Query("select u from University u where u.status = 'ACTIVE'")
    List<University> findAllStatusActive();
}
