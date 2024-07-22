package giovanni.MapNap.repositories;

import giovanni.MapNap.entities.Camp;
import giovanni.MapNap.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CampRepository extends JpaRepository<Camp, UUID> {
    Optional<Camp> findByPlace(String place);
    Optional<Camp> findByLatitudeAndLongitude(Integer latitude, Integer longitude);

}
