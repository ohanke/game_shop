package capgemini.gameshop.service;

import capgemini.gameshop.entity.User;
import capgemini.gameshop.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
    public User saveUser(User user){
        return userRepository.save(user);
    }
    public User getUser(Long id){
        return userRepository.findUserById(id).orElse(null);
    }
}
