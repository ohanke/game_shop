package capgemini.gameshop.controller;

import capgemini.gameshop.dto.UserDto;
import capgemini.gameshop.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class UserController {

    private final UserService userService;

    @GetMapping("/users")
    public List<UserDto> getUsers(){
        return userService.getAllUsers();
    }
}
