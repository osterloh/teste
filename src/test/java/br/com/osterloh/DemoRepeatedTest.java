package br.com.osterloh;

import br.com.osterloh.math.SimpleMath;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DemoRepeatedTest {

    SimpleMath math;

    @BeforeEach
    void beforeEachMethod(){
        math = new SimpleMath();
    }

    @RepeatedTest(value = 3, name = "{displayName}. Repetition {currentRepetition} of {totalRepetitions}!")
    @DisplayName("Teste Division by Zero")
    void testDivision_When_FirstNumberDivisionByZero_ShouldThrowAritmeticException(RepetitionInfo repetitionInfo, TestInfo testInfo){

        System.out.println("Repetition Nº: " + repetitionInfo.getCurrentRepetition() + " of " + repetitionInfo.getTotalRepetitions());
        System.out.println("Running " + testInfo.getTestMethod().get().getName());
        //given
        double firstNumber = 6D;
        double secondNumber = 0D;

        String expectdMessage = "Impossível divisão por zero";

        //when & then
        ArithmeticException actual = assertThrows(ArithmeticException.class, () -> {
            //when & then
            math.division(firstNumber, secondNumber);
        }, () -> "Division by zero should throw an ArithmeticException");

        assertEquals(expectdMessage, actual.getMessage(), () -> "Unexpectded exception message");
    }
}
