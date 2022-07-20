package capgemini.gameshop.users.clients;

import capgemini.gameshop.users.dto.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@FeignClient(name = "users", contextId ="users", path = "/api/users")
public interface UserClient {

    @GetMapping
    List<UserDto> getUsers();

    @GetMapping(value = "/id/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    UserDto getUserById(@PathVariable Long id);

    @GetMapping(value = "/email/{email}", produces = {MediaType.APPLICATION_JSON_VALUE})
    UserDto getUserByMail(@PathVariable String email);

    @PostMapping
    UserDto create(@Valid @RequestBody UserDto userDto);

    @PutMapping("/{id}")
    UserDto update(@PathVariable Long id, @Valid @RequestBody UserDto userDto);

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void delete(@PathVariable Long id);
}
