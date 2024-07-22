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
    private int latitude;
    private int longitude;
    private String address;
    private String place;
    private String description;

    public Camp(String description, String place, String address, int longitude, int latitude) {
        this.description = description;
        this.place = place;
        this.address = address;
        this.longitude = longitude;
        this.latitude = latitude;
    }
}
