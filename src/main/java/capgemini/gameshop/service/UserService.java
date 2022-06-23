package capgemini.gameshop.service;

import capgemini.gameshop.dto.UserDto;
import capgemini.gameshop.entity.User;
import capgemini.gameshop.exception.UserNotFoundException;
import capgemini.gameshop.repository.UserRepository;
import com.github.dozermapper.core.DozerBeanMapper;
import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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
    private final Mapper mapper = DozerBeanMapperBuilder.buildDefault();

    public List<UserDto> getAllUsers() {
        return userRepository.findAll().stream().map(user -> mapper.map(user, UserDto.class)).collect((Collectors.toList()));
    }
    public UserDto saveUser(User user){
        return mapper.map(userRepository.save(user), UserDto.class);
    }
    public UserDto getUserById(Long id){
        User user = userRepository.findUserById(id).orElseThrow(() -> new UserNotFoundException(id));

        return mapper.map(user, UserDto.class);
    }
}
