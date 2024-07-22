package giovanni.MapNap.controllers;

import giovanni.MapNap.exceptions.BadRequestException;
import giovanni.MapNap.payloads.NewCampDTO;
import giovanni.MapNap.payloads.NewCampResponseDTO;
import giovanni.MapNap.payloads.NewUserDTO;
import giovanni.MapNap.payloads.NewUserResponseDTO;
import giovanni.MapNap.services.CampService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/camp")
public class CampController {
    @Autowired
    private CampService campService;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public NewCampResponseDTO campResponseDTO (@RequestBody @Validated NewCampDTO body, BindingResult validationResult){
        if (validationResult.hasErrors()) {
            System.out.println(validationResult.getAllErrors());
            throw new BadRequestException(validationResult.getAllErrors());
        }
        return new NewCampResponseDTO(this.campService.save(body).getId());

    }
}
