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
public class Marker {
    @Id
    @GeneratedValue
    private UUID id;
    private int latitude;
    private int longitude;
    private String text;

    public Marker(int latitude, int longitude, String text) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.text = text;
    }
}
