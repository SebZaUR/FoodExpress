package proyecto.gestoralmuerzo.service;

import proyecto.gestoralmuerzo.repository.RoleRepository;
import proyecto.gestoralmuerzo.repository.UserRepository;
import proyecto.gestoralmuerzo.exceptions.GestorAlmuerzosAppException;
import proyecto.gestoralmuerzo.model.User;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class UserServices {

    private final UserRepository userRepository;

    private final  RoleRepository roleRepository;

    public UserServices(proyecto.gestoralmuerzo.repository.UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    public boolean login(String email, String password) throws GestorAlmuerzosAppException {
        if (email.isEmpty()) {
            throw new GestorAlmuerzosAppException(GestorAlmuerzosAppException.EMPTY_EMAIL);
        }
        if (password.isEmpty()) {
            throw new GestorAlmuerzosAppException(GestorAlmuerzosAppException.EMPTY_PD);
        }
        Optional<User> newUser = getUser(email);
        User usuario = newUser.orElseThrow(() -> new GestorAlmuerzosAppException(GestorAlmuerzosAppException.EMAIL_NOT_EXIST));
        String encryptPassword = usuario.encrypt(password);
        String userPassword = usuario.getPassword();
        return encryptPassword.equals(userPassword);
    }

    public Optional<User> getUser(String email) {
        return userRepository.findById(email);
    }

    public void addUser(User user,boolean role) {
        userRepository.save(user);
        if(role){
            user.setRole("client",roleRepository);
            userRepository.save(user);
        }
    }

    public void updateUser(User user) {
        Optional<User> usuario = userRepository.findById(user.getEmail());
        User u = usuario.orElseThrow();
        String role = u.getRole();
        user.setRole(role,roleRepository);
        userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public void deleteUser(String id) {
        userRepository.deleteById(id);
    }

}

