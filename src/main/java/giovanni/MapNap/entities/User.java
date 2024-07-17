package giovanni.MapNap.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import giovanni.MapNap.entities.enums.Role;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Entity
@Table(name = "users")
@Getter
@Setter
@ToString
@NoArgsConstructor
@JsonIgnoreProperties(value = { "password", "id", "username", "authorities", "enabled", "accountNonExpired", "credentialsNonExpired", "accountNonLocked"  })
public class User  {
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

    public User(String userName, String email, String password, String name, String surname, String avatar, Role roles) {
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.avatar = avatar;
        this.roles = Role.USER;
    }
}
