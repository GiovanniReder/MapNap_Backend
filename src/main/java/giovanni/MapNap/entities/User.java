package giovanni.MapNap.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import giovanni.MapNap.enums.Role;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "users")
@Getter
@Setter
@ToString
@NoArgsConstructor
@JsonIgnoreProperties(value = { "password", "id",  "authorities", "enabled", "accountNonExpired", "credentialsNonExpired", "accountNonLocked"  })
public class User implements UserDetails {
    @Id
    @GeneratedValue
    private UUID id;
    private String userName;
    private String email;
    private String password;
    private String name;
    private String surname;
    private String avatar;
    private Role roles;

    public User(String userName, String email, String password, String name, String surname) {
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.avatar = "https://ui-avatars.com/api/?name=" + this.name + "+" + this.surname;
        this.roles = Role.USER;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getUsername() {
        return "";
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }
}
