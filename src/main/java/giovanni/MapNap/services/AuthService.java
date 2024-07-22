package giovanni.MapNap.services;

import giovanni.MapNap.entities.User;
import giovanni.MapNap.exceptions.UnauthorizedException;
import giovanni.MapNap.payloads.UserLoginDTO;
import giovanni.MapNap.security.JWTTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    private UserService userService;
    @Autowired
    private PasswordEncoder bcrypt;
    @Autowired
    private JWTTools jwtTools;

    public String authenticateUserAndGenerateToken(UserLoginDTO payload){
        User found = userService.findByEmail(payload.email());
        if (bcrypt.matches(payload.password(), found.getPassword())){
            return jwtTools.createToken(found);
        } else {
            throw new UnauthorizedException("Wrong Credentials!");
        }
    }
}
