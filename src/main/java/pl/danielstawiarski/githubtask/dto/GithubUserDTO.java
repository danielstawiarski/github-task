package pl.danielstawiarski.githubtask.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class GithubUserDTO {
    private String id;
    private String login;
    private String name;
    private String type;
    @JsonProperty("avatar_url")
    private String avatarUrl;
    @JsonProperty("created_at")
    private LocalDateTime createdAt;
    @JsonProperty("public_repos")
    private int publicRepos;
    private int followers;
}
