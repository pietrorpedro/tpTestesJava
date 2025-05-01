import org.example.ScientificCalculator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ScientificCalculatorTest {

    private ScientificCalculator calculator;

    @BeforeEach
    void setUp() {
        calculator = new ScientificCalculator();
    }

    // testes de operações básicas

    @Test
    public void shouldReturnCorrectSumWhenAddingTwoNumbers() {
        double result = calculator.add(1.0, 2.0);

        assertEquals(3.0, result, 0);
    }

    @Test
    public void shouldReturnCorrectDifferenceWhenSubtractingTwoNumbers() {
        // setup cria a instância e prepara os dados
        double n1 = 5.0;
        double n2 = 4.0;

        // execution executa o método que sera testado
        double result = calculator.subtract(n1, n2);

        // assertion verifica o resultado se é o esperado
        assertEquals(1.0, result, 0);

        // teardown não é aplicavel para esse tipo de teste pois não há recursos externos

    }

    @Test
    public void shouldReturnCorrectSquareRootForPositiveNumber() {
        double n1 = 4.0;

        double result = calculator.squareRoot(n1);

        assertEquals(2.0, result, 0);
    }

    @Test
    public void shouldThrowExceptionWhenCalculatingSquareRootOfNegativeNumber() {
        assertThrows(IllegalArgumentException.class, () -> {
           calculator.squareRoot(-1.0);
        });
    }

    @Test
    public void shouldThrowExceptionWhenDividingByZero() {
        double n1 = 0.0;
        double n2 = 0.0;

        assertThrows(IllegalArgumentException.class, () -> {
            calculator.divide(n1, n2);
        });
    }

    // testes funções trignométricas

    @Test
    public void shouldReturnCorrectSineForGivenAngle() {
        double n = 30.0;

        double result = calculator.sin(n);

        System.out.println(result);

        assertEquals(0.5, result, 0.0001);
    }

    @Test
    public void shouldReturnCorrectCosineForGivenAngle() {
        double angle = 60.0;

        double result = calculator.cos(angle);

        assertEquals(0.5, result, 0.0001);
    }

    // testes de funções logarítmicas

    @Test
    public void shouldReturnCorrectLogarithmForPositiveNumber() {
        double n = Math.E;

        double result = calculator.log(n);

        assertEquals(1.0, result, 0);
    }

    @Test
    public void shouldThrowExceptionWhenCalculatingLogForNonPositiveNumber() {
        assertThrows(IllegalArgumentException.class, () -> {
            calculator.log(0.0);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            calculator.log(-1.0);
        });
    }
}
