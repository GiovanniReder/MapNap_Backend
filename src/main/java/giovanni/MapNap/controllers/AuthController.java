package giovanni.MapNap.controllers;

import giovanni.MapNap.exceptions.BadRequestException;
import giovanni.MapNap.payloads.NewUserDTO;
import giovanni.MapNap.payloads.NewUserResponseDTO;
import giovanni.MapNap.payloads.UserLoginDTO;
import giovanni.MapNap.payloads.UserLoginResponseDTO;
import giovanni.MapNap.services.AuthService;
import giovanni.MapNap.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private UserService userService;
    @Autowired
    private AuthService authService;


    // http://localhost:3001/auth/register

    // POST
    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public NewUserResponseDTO userResponseDTO (@RequestBody @Validated NewUserDTO body, BindingResult validationResult){
        if (validationResult.hasErrors()) {
            System.out.println(validationResult.getAllErrors());
            throw new BadRequestException(validationResult.getAllErrors());
        }
        return new NewUserResponseDTO(this.userService.save(body).getId());
    }

    //LOGIN  http://localhost:3001/auth/login
    @PostMapping("/login")
    public UserLoginResponseDTO login(@RequestBody @Validated UserLoginDTO body, BindingResult validationResult){
        if (validationResult.hasErrors()) {
            System.out.println(validationResult.getAllErrors());
            throw new BadRequestException(validationResult.getAllErrors());
        }
        return new UserLoginResponseDTO(this.authService.authenticateUserAndGenerateToken(body));
    }
}
