package capgemini.gameshop.service;

import capgemini.gameshop.dto.UserDto;
import capgemini.gameshop.entity.User;
import capgemini.gameshop.mapper.UserToUserDtoMapper;
import capgemini.gameshop.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *  Service for UserRepository.
 *
 *  Calls UserRepository to get required Entities of User.
 *  Calls the UserToUserDtoMapper to map the Entities into DTO type.
 *  Returns DTO's of User
 */
@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public List<UserDto> getAllUsers() {
        return UserToUserDtoMapper.map(userRepository.findAll());
    }
    //TODO change return type to UserDto
    public User saveUser(User user){
        return userRepository.save(user);
    }
    public UserDto getUserById(Long id){
        return UserToUserDtoMapper.map(userRepository.findUserById(id).orElse(null));
    }
}
