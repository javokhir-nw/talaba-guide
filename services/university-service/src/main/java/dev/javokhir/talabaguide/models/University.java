package dev.javokhir.talabaguide.models;

import dev.javokhir.talabaguide.models.audit.Auditable;
import dev.javokhir.talabaguide.models.enums.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table(name = "faculties")
public class University extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @JoinColumn(name = "city_id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private City city;

    private String websiteUrl;
    private String email;
    private String contactNumber;
    private Date foundedYear;
    private String foundedBy;
    private Integer rankingGlobal;
    private Integer rankingNational;
    private Double ratingByUsers = 0.0; //future
    private String logoUrl;
    private String about;
    private String locationUrl;
    private String facebookUrl;
    private String telegramUrl;
    private String instagramUrl;
    private String youtubeUrl;
    @Enumerated(EnumType.STRING)
    private Status status;
}
