package pl.danielstawiarski.githubtask.service;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import pl.danielstawiarski.githubtask.entity.LoginCountEntity;
import pl.danielstawiarski.githubtask.repository.LoginCountRepository;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class LoginCountServiceTest {

    private final LoginCountRepository loginCountRepository = Mockito.mock(LoginCountRepository.class);
    private final LoginCountService loginCountService = new LoginCountService(loginCountRepository);

    @Test
    void shouldIncrementRequestCountIfLoginAlreadyExists() {
        LoginCountEntity loginCountEntity = new LoginCountEntity("dummy", 1);
        when(loginCountRepository.findById("dummy")).thenReturn(Optional.of(loginCountEntity));

        loginCountService.incrementLoginRequestCount("dummy");

        verify(loginCountRepository).save(argThat(
                x -> x.getRequestCount() == 2));
    }

    @Test
    void shouldAddNewEntryForNotExistingLogin() {
        when(loginCountRepository.findById("dummy")).thenReturn(Optional.empty());

        loginCountService.incrementLoginRequestCount("dummy");

        verify(loginCountRepository).save(argThat(
                x -> x.getRequestCount() == 1));
    }
}
