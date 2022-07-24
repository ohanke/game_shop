package capgemini.gameshop.controller;

import capgemini.gameshop.service.UserService;
import capgemini.gameshop.users.dto.UserDto;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.micrometer.core.annotation.Timed;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserRestController {

    private final String CIRCUIT_SERVICE = "userService";
    private final UserService userService;
    private final CircuitBreakerFactory factory;


    /**
     * Returns all users from repository
     *
     * @return - list of user
     */
    @GetMapping
    @Timed("users.all")
    public List<UserDto> getUsers() {
        return factory.create(CIRCUIT_SERVICE).run(userService::findAll);
    }



    /**
     * Return the user with specific ID
     *
     * @param id - the ID of user to retrieve
     * @return - the user with specific ID, or errors status NOT_FOUND
     */
    @GetMapping(value = "/id/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public UserDto getUserById(@PathVariable Long id) {
        return factory.create(CIRCUIT_SERVICE).run(() -> userService.findById(id));
    }

    /**
     * Return the user with specific Email
     *
     * @param email - email of the user to retrieve
     * @return - the user with specific email, or errors status NOT_FOUND
     */
    @GetMapping(value = "/email/{email}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public UserDto getUserByMail(@PathVariable String email) {
        return factory.create(CIRCUIT_SERVICE).run(() -> userService.findByEmail(email));
    }

    /**
     * Create new User
     *
     * @param userDto - the user to create
     * @return - the created product, or errors status FORBIDDEN on existing userDto eamil value in database
     */
    @PostMapping
    public UserDto create(@Valid @RequestBody UserDto userDto) {
        return factory.create(CIRCUIT_SERVICE).run(() -> userService.create(userDto));
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
        return factory.create(CIRCUIT_SERVICE).run(() -> userService.update(id, userDto));
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

    @GetMapping("token")
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String authorizationHeader = request.getHeader(AUTHORIZATION);
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            try {
                String refresh_token = authorizationHeader.substring("Bearer ".length());
                Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
                JWTVerifier verifier = JWT.require(algorithm).build();
                DecodedJWT decodedJWT = verifier.verify(refresh_token);
                String username = decodedJWT.getSubject();
                UserDto user = userService.findByUsername(username);

                String access_token = JWT.create()
                        .withSubject(user.getUsername())
                        .withExpiresAt(new Date(System.currentTimeMillis() + 5 * 60 * 1000))
                        .withIssuer(request.getRequestURL().toString())
                        .withClaim("role", user.getRole())
                        .sign(algorithm);

                Map<String, String> tokens = new HashMap<>();
                tokens.put("access_token", access_token);
                tokens.put("refresh_token", refresh_token);
                response.setContentType(APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(), tokens);

            } catch (Exception exception) {
                response.setHeader("error", exception.getMessage());
                response.setStatus(FORBIDDEN.value());
                Map<String, String> error = new HashMap<>();
                error.put("error_message", exception.getMessage());
                response.setContentType(APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(), error);
            }
        } else {
            throw new RuntimeException("Refresh token is missing");
        }
    }
}

