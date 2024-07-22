package giovanni.MapNap.services;

import giovanni.MapNap.entities.Camp;
import giovanni.MapNap.exceptions.BadRequestException;
import giovanni.MapNap.payloads.NewCampDTO;
import giovanni.MapNap.repositories.CampRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class CampService {
    @Autowired
    public CampRepository campRepository;

    public Camp save(NewCampDTO body){
        if (this.campRepository.findByLatitudeAndLongitude(body.latitude() , body.longitude()).isPresent()){
            throw new BadRequestException("Camp already exist!");
        }
        Camp newCamp= new Camp(body.description(), body.place(), body.address(), body.longitude(), body.latitude());

        return newCamp;
    }
}