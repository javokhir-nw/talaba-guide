package dev.javokhir.talabaguide.dtos;

import dev.javokhir.talabaguide.models.City;
import dev.javokhir.talabaguide.models.Country;
import dev.javokhir.talabaguide.models.University;
import dev.javokhir.talabaguide.models.enums.Status;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UniversityDto {
    private Long id;
    @NotBlank
    private String name;
    private Long cityId;
    private String websiteUrl;
    private String email;
    private String contactNumber;
    private Date foundedYear;
    private String foundedBy;
    private Integer rankingGlobal;
    private Integer rankingNational;
    private Double ratingByUsers;
    private String logoUrl;
    private String about;
    private String locationUrl;
    private String facebookUrl;
    private String telegramUrl;
    private String instagramUrl;
    private String youtubeUrl;
    private List<FacultyResponseDto> faculties = new ArrayList<>();

    private String cityName;
    private String countryName;

    public UniversityDto(University u) {
        name = u.getName();
        websiteUrl = u.getWebsiteUrl();
        email = u.getEmail();
        contactNumber = u.getContactNumber();
        foundedYear = u.getFoundedYear();
        foundedBy = u.getFoundedBy();
        rankingGlobal = u.getRankingGlobal();
        rankingNational = u.getRankingNational();
        ratingByUsers = u.getRatingByUsers();
        logoUrl = u.getLogoUrl();
        about = u.getAbout();
        locationUrl = u.getLocationUrl();
        facebookUrl = u.getFacebookUrl();
        telegramUrl = u.getTelegramUrl();
        instagramUrl = u.getInstagramUrl();
        youtubeUrl = u.getYoutubeUrl();

        City city = u.getCity();
        if (city != null) {
            cityName = city.getName();
            Country country = city.getCountry();
            if (country != null){
                countryName = country.getName();
            }
        }
    }

    public University toUniversity() {
        return toUniversity(new University());
    }

    public University toUniversity(University university) {
        university.setWebsiteUrl(websiteUrl);
        university.setEmail(email);
        university.setContactNumber(contactNumber);
        university.setFoundedYear(foundedYear);
        university.setFoundedBy(foundedBy);
        university.setRankingGlobal(rankingGlobal);
        university.setRankingNational(rankingNational);
        university.setLogoUrl(logoUrl);
        university.setAbout(about);
        university.setLocationUrl(locationUrl);
        university.setFacebookUrl(facebookUrl);
        university.setTelegramUrl(telegramUrl);
        university.setInstagramUrl(instagramUrl);
        university.setYoutubeUrl(youtubeUrl);
        university.setStatus(Status.ACTIVE);
        return university;
    }
}
