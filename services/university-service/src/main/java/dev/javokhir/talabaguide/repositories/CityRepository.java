package dev.javokhir.talabaguide.repositories;

import dev.javokhir.talabaguide.dtos.CityDto;
import dev.javokhir.talabaguide.models.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {

    @Query("select c from City c where c.country.id = ?1")
    List<City> findAllByCountryId(Long countryId);
}
