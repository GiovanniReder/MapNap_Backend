package giovanni.MapNap.payloads;


import jakarta.validation.constraints.*;

public record NewCampDTO(

        @Min(value = -90, message = "Latitude must be between -90 and 90")
        @Max(value = 90, message = "Latitude must be between -90 and 90")
        int latitude,

        @Min(value = -180, message = "Longitude must be between -180 and 180")
        @Max(value = 180, message = "Longitude must be between -180 and 180")
        int longitude,

        @NotEmpty(message = "The address is a mandatory data!")
        @Size( min = 5, max = 255, message = "Address cannot be longer than 255 characters")
        String address,

        @NotEmpty(message = "The place is a mandatory data!")
        @Size(min = 5, max = 100, message = "Place cannot be longer than 100 characters")
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