package capgemini.gameshop.controller.rest;

import capgemini.gameshop.dto.UserDto;
import capgemini.gameshop.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserRestController {

    private final UserService userService;

    @GetMapping("/users")
    public List<UserDto> getUsers(){
        return userService.getAllUsers();
    }
}
