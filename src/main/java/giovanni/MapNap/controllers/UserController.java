package giovanni.MapNap.controllers;

import giovanni.MapNap.entities.User;
import giovanni.MapNap.exceptions.BadRequestException;
import giovanni.MapNap.payloads.NewUserDTO;
import giovanni.MapNap.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/utenti")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    public Page<User> getAll(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "id") String sortBy){
        return  this.userService.getAll(page, size, sortBy);
    }

    @PutMapping("/utenteId")
    public User patchNameUtente(@PathVariable UUID utenteId, @RequestBody @Validated NewUserDTO body, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            System.out.println(bindingResult.getAllErrors());
            throw new BadRequestException(bindingResult.getAllErrors());
        }
        return userService.patchNameUtente(utenteId, body);
    }

}
