package dev.javokhir.talabaguide.repositories;

import dev.javokhir.talabaguide.models.FacultyProgram;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FacultyProgramRepository extends JpaRepository<FacultyProgram, Long> {

    @Query("select fp from FacultyProgram fp where fp.faculty.id = ?1 and fp.status = 'ACTIVE'")
    List<FacultyProgram> findAllByFacultyId(Long id);
}
