package pl.danielstawiarski.githubtask.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.danielstawiarski.githubtask.entity.LoginCountEntity;

@Repository
public interface LoginCountRepository extends JpaRepository<LoginCountEntity, String> {
}
