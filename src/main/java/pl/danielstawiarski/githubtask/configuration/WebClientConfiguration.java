package pl.danielstawiarski.githubtask.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class WebClientConfiguration {

    @Bean
    RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
