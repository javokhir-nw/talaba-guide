package dev.javokhir.talabaguide.config.audit;

import dev.javokhir.talabaguide.models.Authority;
import dev.javokhir.talabaguide.repositories.AuthorityRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
@RequiredArgsConstructor
@Slf4j
public class AuthorityScanService {

    private final ApplicationContext applicationContext;
    private final AuthorityRepository repository;

    @PostConstruct
    public void scanAndSaveAuthorities() {

        log.info("Authority save process begin at {}",new Date());

        Map<String, Object> beans = applicationContext.getBeansWithAnnotation(RestController.class);

        for (Object bean : beans.values()) {
            Class<?> aClass = bean.getClass();
            Method[] methods = aClass.getMethods();

            for (Method method : methods) {
                PreAuthorize preAuth = AnnotationUtils.findAnnotation(method, PreAuthorize.class);
                if (preAuth != null) {
                    List<String> extractedAuthorities = extractAuthorities(preAuth.value());
                    for (String authorityName : extractedAuthorities) {
                        if (!repository.existsByName(authorityName)){
                            Authority authority = new Authority();
                            authority.setName(authorityName);
                            authority.setClassName(ClassUtils.getUserClass(aClass).getSimpleName());
                            repository.save(authority);
                        }
                    }
                }
            }
        }
    }

    private List<String> extractAuthorities(String value) {
        List<String> result = new ArrayList<>();
        Pattern pattern = Pattern.compile("hasAuthority\\('(.+?)'\\)");
        Matcher matcher = pattern.matcher(value);
        while (matcher.find()) {
            result.add(matcher.group(1));
        }
        return result;
    }
}
