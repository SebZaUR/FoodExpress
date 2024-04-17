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

    private final UserRepository UserRepository;

    private final  RoleRepository roleRepository;

    public UserServices(proyecto.gestoralmuerzo.repository.UserRepository userRepository, RoleRepository roleRepository) {
        UserRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    public boolean login(String email, String password) throws GestorAlmuerzosAppException {
        if (email.isEmpty()) {
            throw new GestorAlmuerzosAppException(GestorAlmuerzosAppException.empty_email);
        }
        if (password.isEmpty()) {
            throw new GestorAlmuerzosAppException(GestorAlmuerzosAppException.emptyPassword);
        }
        Optional<User> newUser = getUser(email);
        User usuario = newUser.orElseThrow(() -> new GestorAlmuerzosAppException(GestorAlmuerzosAppException.emailnotexist));
        String encryptPassword = usuario.encrypt(password);
        String userPassword = usuario.getPassword();
        return encryptPassword.equals(userPassword);
    }

    public Optional<User> getUser(String email) {
        return UserRepository.findById(email);
    }

    public void addUser(User user,boolean role) {
        UserRepository.save(user);
        if(role){
            user.setRole("client",roleRepository);
            UserRepository.save(user);
        }
    }

    public void updateUser(User user) {
        Optional<User> usuario = UserRepository.findById(user.getEmail());
        User u = usuario.orElseThrow();
        String role = u.getRole();
        user.setRole(role,roleRepository);
        UserRepository.save(user);
    }

    public List<User> getAllUsers() {
        return UserRepository.findAll();
    }

    public void deleteUser(String id) {
        UserRepository.deleteById(id);
    }

}

