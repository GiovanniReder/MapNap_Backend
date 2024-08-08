package giovanni.MapNap.controllers;

import giovanni.MapNap.entities.User;
import giovanni.MapNap.exceptions.BadRequestException;
import giovanni.MapNap.exceptions.NotFoundException;
import giovanni.MapNap.payloads.NewUserDTO;
import giovanni.MapNap.payloads.UpdateAvatarDTO;
import giovanni.MapNap.payloads.UpdateAvatarResponseDTO;
import giovanni.MapNap.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/utenti")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private PasswordEncoder bcrypt;

    @GetMapping
    public Page<User> getAll(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "id") String sortBy){
        return  this.userService.getAll(page, size, sortBy);
    }

    @PutMapping("/{utenteId}/profile")
    public ResponseEntity<User> patchUser(@PathVariable UUID utenteId,
                                          @RequestBody @Validated NewUserDTO body,
                                          BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            System.out.println(bindingResult.getAllErrors());
            throw new BadRequestException(bindingResult.getAllErrors());
        }

        User updatedUser = userService.patchUser(
                utenteId,
                body.name(),
                body.surname(),
                body.email(),
                bcrypt.encode(body.password())


        );

        return ResponseEntity.ok(updatedUser);
    }


    @PutMapping("/{userId}/avatar")
    public ResponseEntity<UpdateAvatarResponseDTO> updateAvatar(@PathVariable UUID userId, @RequestBody UpdateAvatarDTO request) {
        User user = userService.findById(userId);
        if (user == null) {
            throw new NotFoundException("User with id " + userId + " not found!");
        }


        User updatedUser = userService.patchAvatarUtente(user, request.url());
        String updatedAvatarUrl = request.url();

        return ResponseEntity.ok(new UpdateAvatarResponseDTO(updatedAvatarUrl));
    }



}

