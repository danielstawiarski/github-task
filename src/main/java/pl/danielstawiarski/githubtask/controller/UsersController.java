package pl.danielstawiarski.githubtask.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import pl.danielstawiarski.githubtask.dto.UserDTO;

@RestController
@AllArgsConstructor
public class UsersController {
    private final RestTemplate restTemplate;

    @GetMapping("users/{login}")
    public UserDTO getUserDetails(@PathVariable String login) {
        ResponseEntity<UserDTO> forEntity = restTemplate.getForEntity(String.format("https://api.github.com/users/%s", login), UserDTO.class);
        return forEntity.getBody();
    }
}
