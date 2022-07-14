package capgemini.gameshop.controller;

import capgemini.gameshop.users.dto.UserDto;
import capgemini.gameshop.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserRestController {

    private final UserService userService;
    private final CircuitBreakerFactory factory;


    /**
     * Returns all users from repository
     *
     * @return - list of user
     */
    @GetMapping
    public List<UserDto> getUsers() {
        return factory.create("userService").run(userService::findAll);
    }

    /**
     * Return the user with specific ID
     *
     * @param id - the ID of user to retrieve
     * @return - the user with specific ID, or errors status NOT_FOUND
     */
    @GetMapping(value = "/id/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public UserDto getUserById(@PathVariable Long id) {
        return factory.create("userService").run(() -> userService.findById(id));
    }

    /**
     * Return the user with specific Email
     *
     * @param email - email of the user to retrieve
     * @return - the user with specific email, or errors status NOT_FOUND
     */
    @GetMapping(value = "/email/{email}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public UserDto getUserByMail(@PathVariable String email) {
        return factory.create("userService").run(() -> userService.findByEmail(email));
    }

    /**
     * Create new User
     *
     * @param userDto - the user to create
     * @return - the created product, or errors status FORBIDDEN on existing userDto eamil value in database
     */
    @PostMapping
    public UserDto create(@Valid @RequestBody UserDto userDto) {
        return factory.create("userService").run(() -> userService.create(userDto));
    }

    /**
     * Updates the fields in specific user with specific ID
     *
     * @param id      - the ID of user to update
     * @param userDto - user field values to update
     * @return - the updated product, or errors status NOT_FOUND
     */
    @PutMapping("/{id}")
    public UserDto update(@PathVariable Long id, @Valid @RequestBody UserDto userDto) {
        return factory.create("userService").run(() -> userService.update(id, userDto));
    }

    /**
     * Deleting user with specific ID
     *
     * @param id - the ID of user to delete
     *           Throws errors status NOT_FOUND if user not found in database
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        userService.delete(id);
    }
}

