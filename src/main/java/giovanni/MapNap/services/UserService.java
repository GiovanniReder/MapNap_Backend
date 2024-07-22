package giovanni.MapNap.services;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import giovanni.MapNap.entities.User;
import giovanni.MapNap.exceptions.BadRequestException;
import giovanni.MapNap.exceptions.NotFoundException;
import giovanni.MapNap.payloads.NewUserDTO;
import giovanni.MapNap.repositories.UserRepositories;
import giovanni.MapNap.tools.MailgunSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.UUID;

public class UserService {
    @Autowired
    private UserRepositories userRepositories;
    @Autowired
    private PasswordEncoder bcrypt;
    @Autowired
    private Cloudinary cloudinary;
    @Autowired
    private MailgunSender mailgunSender;



    public User save(NewUserDTO body) {
        this.userRepositories.findByEmailorUserName(body.email(), body.userName()).ifPresent(
                user -> {
                    throw new BadRequestException("Email or username already taken");
                });
        User newUser = new User(body.userName(), body.email(), bcrypt.encode(body.password()), body.name(), body.surname() );

        userRepositories.save(newUser);
        mailgunSender.sendRegistrationEmail(newUser);
        return newUser;
    }

    public User findById(UUID userId){
        return userRepositories.findById(userId).orElseThrow(() -> new NotFoundException(userId));
    }

    public User findByEmail(String email){
        return userRepositories.findByEmail(email).orElseThrow(() -> new NotFoundException( "User with email: " + email + " not found!"));
    }

    public String uploadAvatar(MultipartFile file) throws IOException {
        return (String) this.cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap()).get("url");
    }

    public User patchAvatarUtente(User user, String url) {
        user.setAvatar(url);
        return userRepositories.save(user);
    }

    public User getUserById(UUID id) {
        return userRepositories.findById(id).orElseThrow(() -> new NotFoundException(id));
    }

    public Page<User> getAll(int pageNumber, int pageSize, String sortBy){
        if(pageSize > 50) pageSize = 50;
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(sortBy));
        return this.userRepositories.findAll(pageable);
    }

}