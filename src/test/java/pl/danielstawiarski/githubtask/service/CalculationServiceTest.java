package pl.danielstawiarski.githubtask.service;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CalculationServiceTest {
    private final CalculationService calculationService = new CalculationService();

    @Test
    void shouldCalculateForSmallNumbers() {
        Assertions.assertEquals(2.0, calculationService.calculate(6, 1),
                0);
    }

    @Test
    void shouldReturnZeroWhenUserDoesNotHaveFollowers() {
        Assertions.assertEquals(0, calculationService.calculate(0, 100),
                0);
    }

    @Test
    void shouldCalculateForResultWithManyNumbersAfterComma() {
        Assertions.assertEquals(0.45569620253164556, calculationService.calculate(79, 3),
                0);
    }
}
