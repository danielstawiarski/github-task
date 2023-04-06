package pl.danielstawiarski.githubtask.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "login_counts")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoginCountEntity {
    @Id
    @Column(name = "LOGIN")
    private String login;
    @Column(name = "REQUEST_COUNT")
    private int requestCount;
}
