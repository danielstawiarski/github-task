package pl.danielstawiarski.githubtask.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import pl.danielstawiarski.githubtask.dto.UserDTO;
import pl.danielstawiarski.githubtask.service.UsersService;

@RestController
@AllArgsConstructor
public class UsersController {
    private final UsersService usersService;

    @GetMapping("users/{login}")
    public UserDTO getUserDetails(@PathVariable String login) {
        return usersService.getUserDetails(login);
    }
}
