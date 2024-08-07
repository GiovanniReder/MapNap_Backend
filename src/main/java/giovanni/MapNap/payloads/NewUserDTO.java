package giovanni.MapNap.payloads;

import giovanni.MapNap.enums.Role;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record NewUserDTO(
        @NotEmpty(message = "The username is a mandatory data!")
        @Size(min = 3, max = 25, message = "The username must be between 3 and 25 characters!")
        String username,

        @NotEmpty(message = "The email is mandatory data!")
        @Pattern(regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$", message = "The email is not valid")
        String email,

        @NotEmpty(message = "The password is mandatory data!")
        @Size(min = 3, max = 25, message = "The password must be between 3 and 25 characters!")
        String password,

        @NotEmpty(message = "The name is mandatory data!")
        @Size(min = 3, max = 25, message = "The name must be between 3 and 25 characters!")
        String name,

        @NotEmpty(message = "The surname is mandatory data!")
        @Size(min = 3, max = 25, message = "The surname must be between 3 and 25 characters!")
        String surname,
        Role role
) {
}
/*
*   private String userName;
    private String email;
    private String password;
    private String name;
    private String surname;
    private String avatar;
    private Role roles;
* */