package giovanni.MapNap.services;

import giovanni.MapNap.entities.Camp;
import giovanni.MapNap.exceptions.BadRequestException;
import giovanni.MapNap.exceptions.NotFoundException;
import giovanni.MapNap.payloads.NewCampDTO;
import giovanni.MapNap.repositories.CampRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class CampService {
    @Autowired
    public CampRepository campRepository;

    public Camp save(NewCampDTO body){
        if (this.campRepository.findByLatitudeAndLongitude(body.latitude() , body.longitude()).isPresent()){
            throw new BadRequestException("Camp already exist!");
        }
        Camp newCamp= new Camp(body.name(), body.latitude(), body.longitude(), body.address(), body.place(), body.description(), body.image());

        return this.campRepository.save(newCamp);
    }

    public List<Camp> getAll(String sortBy) {
        return campRepository.findAll(Sort.by(sortBy));
    }

    public Camp findById(UUID campId){
        return campRepository.findById(campId).orElseThrow(() -> new NotFoundException(campId));
    }
    public List<Camp> findByPlace(String searchedPlace){
        return campRepository.findByPlace(searchedPlace);
    }
}
