package pl.danielstawiarski.githubtask.service;

import org.springframework.stereotype.Service;

@Service
public class CalculationService {

    //TODO There is no requirement regarding 0 followers case.
    public int calculate(int followers, int publicRepos) {
        return followers == 0 ? 0 : 6 / followers * publicRepos * 2;
    }
}
