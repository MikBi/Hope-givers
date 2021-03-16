package pl.coderslab.charity.services;

import org.springframework.stereotype.Service;
import pl.coderslab.charity.entities.User;
import pl.coderslab.charity.repositories.UserRepository;

import java.util.List;

@Service
public class UserService implements SimpleServices<User> {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public User add(User user) {
        return userRepository.save(user);
    }

    @Override
    public void delete(int id) {
        userRepository.deleteById(id);
    }

    @Override
    public void update(User user) {
        userRepository.save(user);
    }

    @Override
    public List<User> all() {
        return userRepository.findAll();
    }

    @Override
    public User single(int id) {
        return userRepository.findTopById(id);
    }

    public User email(String email) {
        return userRepository.findByEmail(email);
    }
}
