package pl.danielstawiarski.githubtask.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.danielstawiarski.githubtask.entity.LoginCountEntity;
import pl.danielstawiarski.githubtask.repository.LoginCountRepository;

import java.util.Optional;

@Service
@AllArgsConstructor
public class LoginCountService {
    private final LoginCountRepository loginCountRepository;

    @Transactional
    public void incrementLoginRequestCount(String login) {
        Optional<LoginCountEntity> optionalCountEntity = loginCountRepository.findById(login);
        if (optionalCountEntity.isPresent()) {
            LoginCountEntity countEntity = optionalCountEntity.get();
            countEntity.setRequestCount(countEntity.getRequestCount() + 1);
            loginCountRepository.save(countEntity);
            return;
        }
        loginCountRepository.save(new LoginCountEntity(login, 1));
    }
}
