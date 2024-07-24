package giovanni.MapNap.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import java.util.UUID;


@Entity
@Table(name = "Campeggi")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Camp {
    @Id
    @GeneratedValue
    private UUID id;
    private String name;
    private float latitude;
    private float longitude;
    private String address;
    private String place;
    private String description;
    private String image;

    public Camp(String name, float latitude, float longitude, String address, String place, String description, String image) {
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.address = address;
        this.place = place;
        this.description = description;
        this.image = image;
    }
}
