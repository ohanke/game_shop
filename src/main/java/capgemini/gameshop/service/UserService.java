package capgemini.gameshop.service;

import capgemini.gameshop.dto.UserDto;
import capgemini.gameshop.entity.User;
import capgemini.gameshop.exception.UserNotFoundException;
import capgemini.gameshop.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 *  Service for UserRepository.
 *
 *  Calls UserRepository to get required Entities of User.
 *  Calls the UserToUserDtoMapper to map the Entities into DTO type.
 *  Returns DTO's of  User
 */
@Service
@AllArgsConstructor
public class UserService {

    private ModelMapper mapper;

    private UserRepository userRepository;

    public UserDto convertToUserDTO (User user) {
    UserDto userDto = new UserDto();
    userDto = mapper.map(user, UserDto.class);
    return userDto;
    }

    public List<UserDto> findAll() {
        return userRepository.findAll().stream().map(this::convertToUserDTO).collect((Collectors.toList()));
    }
    public UserDto save(User user){
        return mapper.map(userRepository.save(user), UserDto.class);
    }
    public UserDto FindById(Long id){
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));

        return mapper.map(user, UserDto.class);
    }
}
