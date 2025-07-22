package dev.javokhir.talabaguide.repositories;

import dev.javokhir.talabaguide.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    @Query("""
            select count(u) > 0 from User u
            where lower(u.username) = lower(?1) and (?2 is null or u.id != ?2)
            """)
    Boolean checkIsExistUsername(String username, Long userId);

    @Query("""
            select count(u) > 0 from User u
            where lower(u.email) = lower(?1) and (?2 is null or u.id != ?2)
            """)
    Boolean checkIsExistEmail(String email, Long userId);

    @Query("select u from User u where lower(u.username) = lower(?1) and u.status = 'ACTIVE' ")
    Optional<UserDetails> findByUsername(String username);
}
