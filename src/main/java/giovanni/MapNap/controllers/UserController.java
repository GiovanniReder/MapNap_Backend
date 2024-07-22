package giovanni.MapNap.controllers;

import giovanni.MapNap.entities.User;
import giovanni.MapNap.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/utenti")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    public Page<User> getAll(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "id") String sortBy){
        return  this.userService.getAll(page, size, sortBy);
    }

}
