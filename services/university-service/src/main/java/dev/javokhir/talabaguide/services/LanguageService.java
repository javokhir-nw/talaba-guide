package dev.javokhir.talabaguide.services;

import dev.javokhir.talabaguide.dtos.CityDto;
import dev.javokhir.talabaguide.dtos.LanguageDto;
import dev.javokhir.talabaguide.models.Language;
import dev.javokhir.talabaguide.repositories.LanguageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LanguageService {

    private final LanguageRepository languageRepository;

    public String createOrUpdate(LanguageDto dto) {
        Language l = new Language();
        l.setCode(dto.getCode());
        l.setName(dto.getName());
        languageRepository.save(l);
        return "Language saved successfully";
    }

    public List<LanguageDto> getAll() {
        return languageRepository.findAll().stream().map(LanguageDto::new).toList();
    }
}
