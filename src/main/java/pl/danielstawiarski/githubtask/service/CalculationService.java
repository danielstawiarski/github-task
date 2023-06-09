package pl.danielstawiarski.githubtask.service;

import org.springframework.stereotype.Service;

@Service
public class CalculationService {

    //TODO There is no requirement regarding 0 followers case.
    public double calculate(int followers, int publicRepos) {
        return (followers == 0 ? 0 : (double) 6 / followers) * publicRepos * 2;
    }
}
