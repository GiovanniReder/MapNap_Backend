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
    public String address;
    public String place;
    private String description;

    public Camp(int latitude, int longitude, String description) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.description = description;
    }
}
