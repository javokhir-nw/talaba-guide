package dev.javokhir.talabaguide.models;

import dev.javokhir.talabaguide.models.audit.Auditable;
import dev.javokhir.talabaguide.models.enums.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table(name = "faculties_programs")
public class FacultyProgram extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            joinColumns = @JoinColumn(name = "faculty_program_id"),
            inverseJoinColumns = @JoinColumn(name = "degree_type_id"),
            name = "faculty_programs_degrees"
    )
    private List<DegreeType> degreeTypes;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "faculty_id")
    private Faculty faculty;
    private String about;
    private Double tuitionFeeFrom;
    private Double tuitionFeeTo;
    private Integer durationYears;
    private Date admissionOpens;
    private Date admissionCloses;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            joinColumns = @JoinColumn(name = "faculty_program_id"),
            inverseJoinColumns = @JoinColumn(name = "language_id"),
            name = "faculty_programs_languages"
    )
    private List<Language> languages;
    private String requirements;
    @Enumerated(EnumType.STRING)
    private Status status = Status.ACTIVE;
}
