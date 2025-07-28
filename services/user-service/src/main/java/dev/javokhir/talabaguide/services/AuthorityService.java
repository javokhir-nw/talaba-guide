package dev.javokhir.talabaguide.services;

import dev.javokhir.talabaguide.models.Authority;
import dev.javokhir.talabaguide.repositories.AuthorityRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthorityService {

    private final AuthorityRepository repository;

    public Set<Authority> findAllByIds(List<Long> ids){
        return repository.findAllByIds(ids);
    }
}
