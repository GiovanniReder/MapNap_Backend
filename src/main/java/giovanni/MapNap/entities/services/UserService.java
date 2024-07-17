package giovanni.MapNap.entities.services;

import giovanni.MapNap.entities.repositories.UserRepositories;
import org.springframework.beans.factory.annotation.Autowired;

public class UserService {
    @Autowired
    private UserRepositories userRepositories;
}
