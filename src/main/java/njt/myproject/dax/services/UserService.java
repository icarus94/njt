package njt.myproject.dax.services;

import javassist.NotFoundException;
import njt.myproject.dax.dto.form.RegistrationForm;
import njt.myproject.dax.models.User;
import njt.myproject.dax.models.UserHasTodoList;
import njt.myproject.dax.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class UserService {

    private final UserRepository userRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Transactional
    public User createNewUser(RegistrationForm registrationForm) {
        User user = new User();
        user.setEmail(registrationForm.getEmail());
        user.setName(registrationForm.getName());
        user.setSurname(registrationForm.getSurname());
        user.setPassword(bCryptPasswordEncoder.encode(registrationForm.getPassword()));
        user.setActive((byte) 1);
        user.setRole((byte) 0);
        return userRepository.save(user);
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Transactional
    public User createNewUser(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Transactional
    public User editUser(User user) throws NotFoundException {
        User dbUser = userRepository.findById(user.getId()).orElse(null);
        if (dbUser == null)
            throw new NotFoundException("User not found.");
        if (!user.getPassword().trim().isEmpty())
            dbUser.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        dbUser.setEmail(user.getEmail());
        dbUser.setRole(user.getRole());
        dbUser.setName(user.getName());
        dbUser.setSurname(user.getSurname());
        dbUser.setActive(user.getActive());

        return userRepository.save(dbUser);
    }

    public User findById(int user_id) throws NotFoundException {
        User dbUser = userRepository.findById(user_id).orElse(null);
        if (dbUser == null)
            throw new NotFoundException("User not found.");
        return dbUser;
    }

    @Transactional
    public void deleteUserById(int id) throws NotFoundException {
        User dbUser = userRepository.findById(id).orElse(null);
        if (dbUser == null)
            throw new NotFoundException("User not found.");
        userRepository.delete(dbUser);
    }
}
