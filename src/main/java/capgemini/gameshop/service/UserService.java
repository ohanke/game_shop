package capgemini.gameshop.service;

import capgemini.gameshop.dto.UserDto;
import capgemini.gameshop.entity.User;
import capgemini.gameshop.exception.EmailExistException;
import capgemini.gameshop.exception.UserNotFoundException;
import capgemini.gameshop.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final ModelMapper mapper;

    /**
     * Method that converts User object to UserDto object using ModelMapper
     *
     * @param user - user object
     * @return - return UserDto object
     */

    private UserDto convertToDTO(User user) {
        return mapper.map(user, UserDto.class);
    }

    /**
     * method that finds all users in user repository and returns userDto
     *
     * @return - returns UserDto object
     */
    public List<UserDto> findAll() {
        return userRepository.findAll().stream().map((this::convertToDTO)).collect((Collectors.toList()));
    }

    /**
     * Metohd that finds User by ID. Returns UserDto object or throws UserNotFoundException (with ID)
     *
     * @param id - Long ID field to find object in user repository
     * @return - return UserDto object
     */
    public UserDto findById(Long id) {
        return userRepository.findById(id).map(this::convertToDTO).orElseThrow(() -> new UserNotFoundException(id));
        //return mapper.map(user, UserDto.class);
    }

    /**
     * Metohd that finds User by user email. Returns UserDto object or throws UserNotFoundException (with email)
     *
     * @param email - string email unique fild in User entity, to find object in user repository
     * @return - return UserDto object
     */
    public UserDto findByEmail(String email) {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new UserNotFoundException(email));
        return mapper.map(user, UserDto.class);
    }

    /**
     * Method that maps UserDto object to User object and saves that in user repository. Method checks if email used to create new user is already used, if TRUE throws EmailExistException
     *
     * @param userDto - UserDto object received from request body at controller.
     * @return - return UserDto object
     */
    public UserDto save(UserDto userDto) {

        if (userRepository.findByEmail(userDto.getEmail()).isPresent()) {
            throw new EmailExistException(userDto.getEmail());
        } else {
            return convertToDTO(userRepository.save(mapper.map(userDto, User.class)));
        }
    }

    /**
     * Method that updates existing user.
     *
     * @param id      - ID Long field of user to be found by user repository
     * @param userDto - userDto object that contains fields that will be updated
     * @return - return UserDto object
     */
    public UserDto update(Long id, UserDto userDto) {
        return userRepository.findById(id)
                .map(user -> updateFields(userDto, user))
                .orElseThrow(() -> new UserNotFoundException(userDto.getId()));
    }

    /**
     * Method that deletes user from database
     *
     * @param id - Long ID field to find user entity that will be deleted
     */
    public void delete(Long id) {
        if (userRepository.findById(id).isEmpty()) {
            throw new UserNotFoundException(id);
        } else userRepository.deleteById(id);
    }

    /**
     * Method that is used to update user in repository. Finds wich fields are changed (need to be change - those NolNULL) and saves it a user repository
     *
     * @param userDto - userDto object that contains fields that need to be change
     * @param user    - user object that will be change
     * @return - return UserDto object
     */
    private UserDto updateFields(UserDto userDto, User user) {
        if (userDto.getEmail() != null) {
            if (userRepository.findByEmail(userDto.getEmail()).isPresent()) {
                throw new EmailExistException(user.getEmail());
            } else {
                user.setEmail(userDto.getEmail());
            }
        }
        if (userDto.getFirstName() != null)
            user.setFirstName(userDto.getFirstName());
        if (userDto.getLastName() != null)
            user.setLastName(userDto.getLastName());
        if (userDto.getPassword() != null)
            user.setPassword(userDto.getPassword());
        return convertToDTO(userRepository.save(user));
    }
}
