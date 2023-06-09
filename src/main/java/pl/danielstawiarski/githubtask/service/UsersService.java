package pl.danielstawiarski.githubtask.service;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import pl.danielstawiarski.githubtask.dto.GithubUserDTO;
import pl.danielstawiarski.githubtask.dto.UserDTO;
import pl.danielstawiarski.githubtask.exception.GithubUserNotFoundException;

@Service
@AllArgsConstructor
public class UsersService {
    private static final String GITHUB_USER_DETAILS_URL = "https://api.github.com/users/%s";
    private final RestTemplate restTemplate;
    private final CalculationService calculationService;
    private final LoginCountService loginCountService;

    public UserDTO getUserDetails(@PathVariable String login) {
        loginCountService.incrementLoginRequestCount(login);
        ResponseEntity<GithubUserDTO> githubUserEntity;
        try {
            githubUserEntity =
                    restTemplate.getForEntity(String.format(GITHUB_USER_DETAILS_URL, login), GithubUserDTO.class);
        } catch (HttpClientErrorException exception) {
            throw new GithubUserNotFoundException(String.format("Cannot find github user with login %s", login));
        }
        return getUserFromGithubUser(githubUserEntity.getBody());
    }

    private UserDTO getUserFromGithubUser(GithubUserDTO githubUser) {
        return UserDTO.builder()
                .id(githubUser.getId())
                .name(githubUser.getName())
                .login(githubUser.getLogin())
                .type(githubUser.getType())
                .avatarUrl(githubUser.getAvatarUrl())
                .createdAt(githubUser.getCreatedAt())
                .calculations(calculationService.calculate(githubUser.getFollowers(), githubUser.getPublicRepos()))
                .build();
    }
}
