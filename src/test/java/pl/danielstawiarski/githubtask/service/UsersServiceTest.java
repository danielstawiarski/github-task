package pl.danielstawiarski.githubtask.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import pl.danielstawiarski.githubtask.dto.GithubUserDTO;
import pl.danielstawiarski.githubtask.dto.UserDTO;
import pl.danielstawiarski.githubtask.exception.GithubUserNotFoundException;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class UsersServiceTest {

    private static final LocalDateTime TEST_DATE = LocalDateTime.now();
    private static final String TEST_LOGIN = "dummy";
    private static final String TEST_ID = "1";
    private static final String TEST_AVATAR_URL = "avatarUrl";
    private static final String TEST_TYPE = "USER";
    private static final String GITHUB_USER_DETAILS_URL = "https://api.github.com/users/%s";
    private final RestTemplate restTemplate = Mockito.mock(RestTemplate.class);
    private final CalculationService calculationService = new CalculationService();
    private final LoginCountService loginCountService = Mockito.mock(LoginCountService.class);

    private final UsersService usersService = new UsersService(restTemplate, calculationService, loginCountService);

    @Test
    void shouldAddNewEntryForNotExistingLogin() {
        double expectedCalculationsResult = 12.0;
        ResponseEntity<GithubUserDTO> githubUserEntity;
        GithubUserDTO githubUserDTO = getTestUser();
        githubUserEntity = new ResponseEntity<>(githubUserDTO, HttpStatusCode.valueOf(200));
        when(restTemplate.getForEntity(String.format(GITHUB_USER_DETAILS_URL, TEST_LOGIN), GithubUserDTO.class))
                .thenReturn(githubUserEntity);

        UserDTO userDetails = usersService.getUserDetails(TEST_LOGIN);
        verify(loginCountService, times(1)).incrementLoginRequestCount(TEST_LOGIN);


        Assertions.assertEquals(userDetails.getId(), TEST_ID);
        Assertions.assertEquals(userDetails.getLogin(), TEST_LOGIN);
        Assertions.assertNull(userDetails.getName());
        Assertions.assertEquals(userDetails.getAvatarUrl(), TEST_AVATAR_URL);
        Assertions.assertEquals(userDetails.getType(), TEST_TYPE);
        Assertions.assertEquals(userDetails.getCreatedAt(), TEST_DATE);
        Assertions.assertEquals(userDetails.getCalculations(), expectedCalculationsResult);
    }

    @Test
    void shouldThrowGithubNotFoundExceptionWhenUserDoesNotExist() {
        when(restTemplate.getForEntity(String.format(GITHUB_USER_DETAILS_URL, TEST_LOGIN), GithubUserDTO.class))
                .thenThrow(HttpClientErrorException.class);
        assertThrows(GithubUserNotFoundException.class, () -> {
            usersService.getUserDetails(TEST_LOGIN);
        });
    }

    private static GithubUserDTO getTestUser() {
        return GithubUserDTO.builder()
                .id(TEST_ID)
                .login(TEST_LOGIN)
                .name(null)
                .type(TEST_TYPE)
                .avatarUrl(TEST_AVATAR_URL)
                .createdAt(TEST_DATE)
                .followers(2)
                .publicRepos(2)
                .build();
    }
}
