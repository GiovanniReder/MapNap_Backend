package giovanni.MapNap.controllers;

import giovanni.MapNap.entities.Camp;
import giovanni.MapNap.exceptions.BadRequestException;
import giovanni.MapNap.payloads.NewCampDTO;
import giovanni.MapNap.payloads.NewCampResponseDTO;
import giovanni.MapNap.payloads.NewUserDTO;
import giovanni.MapNap.payloads.NewUserResponseDTO;
import giovanni.MapNap.services.CampService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

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

    @GetMapping
    public Page<Camp> getAll(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "id") String sortBy){
        return  this.campService.getAll(page, size, sortBy);
    }

    @GetMapping("/{campId}")
    public Optional<Camp>  getCampById(@PathVariable UUID campId) {
        return Optional.ofNullable(campService.findById(campId));
    }
    @GetMapping("/location/{place}")
    public Optional<List<Camp>>  getCampsByPlace(@PathVariable String place) {
        return Optional.ofNullable(campService.findByPlace(place));
    }
}
