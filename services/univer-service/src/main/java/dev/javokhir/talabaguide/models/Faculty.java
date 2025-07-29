package dev.javokhir.talabaguide.models;

import dev.javokhir.talabaguide.models.University;
import dev.javokhir.talabaguide.models.audit.Auditable;
import dev.javokhir.talabaguide.models.enums.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table(name = "faculties")
public class Faculty extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "university_id", nullable = false)
    private University university;
    private String about;
    private String contactNumber;
    private String email;
    @Enumerated(EnumType.STRING)
    private Status status;
}
