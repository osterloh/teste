package br.com.osterloh;

import br.com.osterloh.math.SimpleMath;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class SimpleMathTestS4 {

    SimpleMath math;

    @BeforeEach
    void beforeEachMethod(){
        math = new SimpleMath();
    }

    @DisplayName("Test 6 / 2 = 3")
    @ParameterizedTest
    @MethodSource("testDivisionInputParameters")
    void testDivision(double firstNumber, double secondNumber, double expected){
        Double actual = math.division(firstNumber, secondNumber);

        assertEquals(expected, actual, 2D, () -> "The testDivision() did not produce expected result!");
    }

    public static Stream<Arguments> testDivisionInputParameters(){
        return Stream.of(
                Arguments.of(6.2D, 2D, 3.1D),
                Arguments.of(71D, 14D, 5.7D),
                Arguments.of(18.3D, 3.1D, 5.90D)
        );
    }

    @DisplayName("Test 6 / 2 = 3")
    @ParameterizedTest
    @MethodSource()
    void testDivision2(double firstNumber, double secondNumber, double expected){
        Double actual = math.division(firstNumber, secondNumber);

        assertEquals(expected, actual, 2D, () -> "The testDivision() did not produce expected result!");
    }

    public static Stream<Arguments> testDivision2(){
        return Stream.of(
                Arguments.of(6.2D, 2D, 3.1D),
                Arguments.of(71D, 14D, 5.7D),
                Arguments.of(18.3D, 3.1D, 5.90D)
        );
    }

    @DisplayName("Test 6 / 2 = 3")
    @ParameterizedTest
    @CsvSource({
            "6.2, 2, 3.1",
            "71, 14, 5.07",
            "18.3, 3.1, 5.90"
    })
    void testDivision3(double firstNumber, double secondNumber, double expected){
        Double actual = math.division(firstNumber, secondNumber);

        assertEquals(expected, actual, 2D, () -> "The testDivision() did not produce expected result!");
    }

//    @DisplayName("Test 6 / 2 = 3")
//    @ParameterizedTest
//    @CsvSource({
//            "Pelé, Football",
//            "Sena, F1",
//            "Keith Moon, ''"
//    })
//    void testDivision4(double firstNumber, double secondNumber, double expected){
//        Double actual = math.division(firstNumber, secondNumber);
//
//        assertEquals(expected, actual, 2D, () -> "The testDivision() did not produce expected result!");
//    }

    @DisplayName("Test double division [firstNumber, secondNumber, expected]")
    @ParameterizedTest
    @CsvFileSource(resources = "/testeDivision.csv")
    void testDivision5(double firstNumber, double secondNumber, double expected){
        Double actual = math.division(firstNumber, secondNumber);

        assertEquals(expected, actual, 2D, () -> "The testDivision() did not produce expected result!");
    }

    @ParameterizedTest
    @ValueSource(strings = {"Pelé", "Sena", "Keith Moon"})
    void testValueSource(String firstName) {
        System.out.println(firstName);
        assertNotNull(firstName);
    }
}
