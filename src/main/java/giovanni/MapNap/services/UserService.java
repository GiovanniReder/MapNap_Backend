package giovanni.MapNap.services;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import giovanni.MapNap.entities.User;
import giovanni.MapNap.exceptions.BadRequestException;
import giovanni.MapNap.exceptions.NotFoundException;
import giovanni.MapNap.payloads.NewUserDTO;
import giovanni.MapNap.repositories.UserRepositories;
import giovanni.MapNap.tools.MailgunSender;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.UUID;
@Service
@Transactional
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
        this.userRepositories.findByEmailOrUserName(body.email(), body.userName()).ifPresent(
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

    public User patchNameUtente(User user, String name){
        user.setName(name);
        return userRepositories.save(user);
    }

    public User patchSurnameUtente(User user, String surname){
        user.setSurname(surname);
        return userRepositories.save(user);
    }

    public User patchEmailUtente(User user, String email){
        user.setEmail(email);
        return userRepositories.save(user);
    }

    public User patchPasswordUtente(User user, String password){
        user.setPassword(password);
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

    public NewUserDTO updateUser(UUID userId, NewUserDTO body) {
        User found = this.getUserById(userId);

        found.setName(body.name());
        found.setSurname(body.surname());
        found.setEmail(body.email());
        found.setPassword(body.password());
        found.setRoles(body.role());
        found.setUserName(body.userName());

        return new NewUserDTO(
                found.getName(),
                found.getSurname(),
                found.getEmail(),
                found.getPassword(),
                found.getUsername(),
                found.getRoles()
        );
    }





}
