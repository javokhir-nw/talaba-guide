package dev.javokhir.talabaguide.repositories;

import dev.javokhir.talabaguide.models.Authority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface AuthorityRepository extends JpaRepository<Authority, Long> {

    @Query("select a from Authority a where a.id in (?1)")
    Set<Authority> findAllByIds(List<Long> ids);

    @Query("select count(a) > 0 from Authority a where lower(a.name) = lower(?1)")
    boolean existsByName(String authorityName);
}
