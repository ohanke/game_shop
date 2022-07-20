package capgemini.gameshop.service;

import capgemini.gameshop.users.dto.UserDto;
import capgemini.gameshop.users.event.IntegrationEvent;
import capgemini.gameshop.users.event.UserDeletedEvent;
import capgemini.gameshop.users.event.UserRegisteredEvent;
import capgemini.gameshop.exception.EmailExistException;
import capgemini.gameshop.exception.UserNotFoundException;
import capgemini.gameshop.model.User;
import capgemini.gameshop.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
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

    private final KafkaTemplate<Long, IntegrationEvent> kafkaTemplate;

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
    public UserDto create(UserDto userDto) {

        if (userRepository.findByEmail(userDto.getEmail()).isPresent()) {
            throw new EmailExistException(userDto.getEmail());
        } else {
            User user = mapper.map(userDto, User.class);
            user.setCreatedAt(LocalDateTime.now());
            UserDto savedUser = convertToDTO(userRepository.save(user));
            kafkaTemplate.send("users-create", savedUser.getId(),
                    new UserRegisteredEvent(savedUser.getId()));
            return savedUser;
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
        User existingUser = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
        if (!userDto.getEmail().equals(existingUser.getEmail())) {
            if (userRepository.findByEmail(userDto.getEmail()).isPresent()) {
                throw new EmailExistException(userDto.getEmail());
            } else {
                existingUser.setEmail(userDto.getEmail());
            }
        }
        existingUser.setFirstName(userDto.getFirstName());
        existingUser.setLastName(userDto.getLastName());
        existingUser.setPassword(userDto.getPassword());
        existingUser.setLastModifiedAt(LocalDateTime.now());
        return convertToDTO(userRepository.save(existingUser));
    }

    /**
     * Method that makes user inactive (change active flag to FALSE) so other microservices can still find it but wil know it's "deleted"
     *
     * @param id - Long ID field to find user entity that will be set active as FALSE
     */
    public void delete(Long id) {
        if (userRepository.findById(id).isEmpty()) {
            throw new UserNotFoundException(id);
        } else {
            User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
            user.setActive(false);
            user.setLastModifiedAt(LocalDateTime.now());
            userRepository.save(user);
            kafkaTemplate.send("users-delete", user.getId(),
                    new UserDeletedEvent(user.getId()));
        }
    }
}