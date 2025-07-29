package dev.javokhir.talabaguide.services;

import dev.javokhir.talabaguide.dtos.FetchAuthoritiesDto;
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
    private final AuthorityRepository authorityRepository;

    public Set<Authority> findAllByIds(List<Long> ids){
        return repository.findAllByIds(ids);
    }

    public String fetchAuthorities(FetchAuthoritiesDto dto){
        String serviceName = dto.getServiceName();
        Map<String, List<String>> authMap = dto.getAuthorities();
        List<String> allAuthNames = authorityRepository.findAllAuthNames();
        List<Authority> authorities = new ArrayList<>();
        for (Map.Entry<String,List<String>> e :authMap.entrySet()){
            e.getValue().forEach(a -> {
                if (a != null && !allAuthNames.contains(a.toLowerCase())){
                    Authority authority = new Authority();
                    authority.setName(a);
                    authority.setClassName(e.getKey());
                    authority.setServiceName(serviceName);
                    authorities.add(authority);
                }
            });
        }
        authorityRepository.saveAll(authorities);
        return "Authorities successfully saved";
    }
}
