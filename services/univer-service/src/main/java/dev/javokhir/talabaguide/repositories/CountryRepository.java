package dev.javokhir.talabaguide.repositories;

import dev.javokhir.talabaguide.models.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryRepository extends JpaRepository<Country, Long> {
}
