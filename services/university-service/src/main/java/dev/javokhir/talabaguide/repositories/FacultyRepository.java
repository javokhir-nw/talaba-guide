package dev.javokhir.talabaguide.repositories;

import dev.javokhir.talabaguide.models.Faculty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FacultyRepository extends JpaRepository<Faculty, Long> {

    @Query("select f from Faculty f where f.university.id  = ?1 and f.status = 'ACTIVE'")
    List<Faculty> findAllByUniversityId(Long universityId);
}
