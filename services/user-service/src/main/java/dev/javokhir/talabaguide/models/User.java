package dev.javokhir.talabaguide.models;


import dev.javokhir.talabaguide.enums.Status;
import dev.javokhir.talabaguide.models.audit.Auditable;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "users")
public class User extends Auditable<User> implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    @Column(nullable = false, unique = true)
    private String username;
    @Column(nullable = false)
    private String password;
    @Column(unique = true)
    private String email;
    private String telegramChatId;
    @ManyToOne
    @JoinColumn(name = "role_id", nullable = false)
    private Role role;
    @Enumerated(EnumType.STRING)
    private Status status;

    private Boolean isNonExpired = true;
    private Boolean isNonLocked = true;
    private Boolean isCredentialsNonExpired = true;
    private Boolean isEnabled = false;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return role.getAuthorities().stream().map(a -> new SimpleGrantedAuthority(a.getName())).toList();
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.isNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.isNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.isCredentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return this.isEnabled;
    }
}
