package giovanni.MapNap.payloads;


import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record NewCampDTO(

        @Min(value = -90, message = "Latitude must be between -90 and 90")
        @Max(value = 90, message = "Latitude must be between -90 and 90")
        int latitude,

        @Min(value = -180, message = "Longitude must be between -180 and 180")
        @Max(value = 180, message = "Longitude must be between -180 and 180")
        int longitude,

        @NotBlank(message = "Address cannot be blank")
        @Size(max = 255, message = "Address cannot be longer than 255 characters")
        String address,

        @NotBlank(message = "Place cannot be blank")
        @Size(max = 100, message = "Place cannot be longer than 100 characters")
        String place,

        @Size(max = 500, message = "Description cannot be longer than 500 characters")
        String description

) {
}

/*
*  private int latitude;
    private int longitude;
    public String address;
    public String place;
    private String description;
* */