package com.dt042g.laboration_2;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {
    @Test
    void testPerformAddition(){
        Calculator calculator = new Calculator();
        assertEquals("6.0", calculator.performAdditionBetweenTwoNumbers("3+3"));
    }
    @Test
    void testPerformSubtraction(){
        Calculator calculator = new Calculator();
        assertEquals("0.0", calculator.performSubtractionBetweenTwoNumbers("3-3"));
    }
    @Test
    void testPerformMultiplication(){
        Calculator calculator = new Calculator();
        assertEquals("9.0", calculator.performMultiplicationBetweenTwoNumbers("3*3"));
    }
    @Test
    void testPerformDivision(){
        Calculator calculator = new Calculator();
        assertEquals("2.5", calculator.performDivisionBetweenTwoNumbers("5/2"));
    }
    @Test
    void testPerformDivisionWithDivisionByZero(){
        Calculator calculator = new Calculator();
        assertEquals(calculator.undefined, calculator.performDivisionBetweenTwoNumbers("5/0"));
    }

    @Test
    void testPerformPowerOf(){
        Calculator calculator = new Calculator();
        assertEquals("27.0", calculator.performPowerOfBetweenTwoNumbers("3^3"));
    }
    @Test
    void testGetFirstRegexMatchMatchFound(){
        Calculator calculator = new Calculator();
        assertEquals("3-3", calculator.getFirstRegexMatch("3-3*3", "-?\\d-\\d+").get());
    }
    @Test
    void testGetFirstRegexMatchMatchNotFound(){
        Calculator calculator = new Calculator();
        assertThrows(NoSuchElementException.class,()->calculator.getFirstRegexMatch("3-3*3", "-?\\d/\\d+").get());
    }

    @Test
    void testAdditionAndSubtractionRegexWithAddition(){
        Calculator calculator = new Calculator();
        assertEquals("3+3", calculator.getFirstRegexMatch("3+3*3", calculator.additionOrSubtractionRegex).get());
    }

    @Test
    void testAdditionAndSubtractionRegexWithSubtraction(){
        Calculator calculator = new Calculator();
        assertEquals("3-3", calculator.getFirstRegexMatch("3-3*3", calculator.additionOrSubtractionRegex).get());
    }

    @Test
    void testAdditionOrSubtractionRegexWithSubtractionWithNegativeNumber(){
        Calculator calculator = new Calculator();
        assertEquals("-3-3", calculator.getFirstRegexMatch("-3-3*3", "((-?\\d+-\\d+)|(-?\\d+\\+-?\\d+))").get());
    }

    @Test
    void testGetFirstRegexMatchForAdditionOrSubtractionRegexWithMatchNotFound(){
        Calculator calculator = new Calculator();
        assertThrows(NoSuchElementException.class,()->calculator.getFirstRegexMatch("3/3*3", calculator.additionOrSubtractionRegex).get());
    }
    @Test
    void testComputeAllAdditionAndSubtraction(){
        Calculator calculator = new Calculator();
        assertEquals("9.0", calculator.computeAllAdditionAndSubtraction("3+3-3+6"));
    }
    @Test
    void testMultiplicationOrDivisionRegexWithMultiplication(){
        Calculator calculator = new Calculator();
        assertEquals("3*3", calculator.getFirstRegexMatch("3-3*3", calculator.multiplicationOrDivisionRegex).get());
    }

    @Test
    void testMultiplicationOrDivisionRegexWithDivision(){
        Calculator calculator = new Calculator();
        assertEquals("3/3", calculator.getFirstRegexMatch("-3-3/3", calculator.multiplicationOrDivisionRegex).get());
    }
    @Test
    void testGetFirstRegexMatchForMultiplicationOrDivisionWithMatchNotFound(){
        Calculator calculator = new Calculator();
        assertFalse(calculator.getFirstRegexMatch("3-3+3", calculator.multiplicationOrDivisionRegex).isPresent());
    }

    @Test
    void testComputeAllMultiplicationAndDivisionWithDivisionByZero(){
        Calculator calculator = new Calculator();
        assertEquals(calculator.undefined, calculator.computeAllMultiplicationAndDivision("-3-3/0"));
    }
    @Test
    void testComputeAllMultiplicationAndDivision(){
        Calculator calculator = new Calculator();
        assertEquals("3.0+6", calculator.computeAllMultiplicationAndDivision("3*3/3+6"));
    }

    @Test
    void testExponentRegexFound(){
        Calculator calculator = new Calculator();
        assertEquals("3.0^3.0", calculator.getFirstRegexMatch("3-3.0^3.0", calculator.posNrExponentRegex).get());
    }
    @Test
    void testExponentRegexNotFound(){
        Calculator calculator = new Calculator();
        assertThrows(NoSuchElementException.class,()->calculator.getFirstRegexMatch("3-3+3", calculator.posNrExponentRegex).get());
    }

    @Test
    void testComputeAllPowerOf(){
        Calculator calculator = new Calculator();
        assertEquals("27.0/9.0", calculator.computeAllExponents("3^3/3^2"));
    }

    @Test
    void testBracketRegex(){
        Calculator calculator = new Calculator();
        assertEquals("(3^3/3^2)", calculator.getFirstRegexMatch("(3^3/3^2)+(-2+4*3)", calculator.bracketRegex).get());
    }
    @Test
    void testGetFirstBracketRegexMatchAndRemoveBracketSigns(){
        Calculator calculator = new Calculator();
        assertEquals("3^3/3^2", calculator.getFirstRegexMatch("(3^3/3^2)+(-2+4*3)", calculator.bracketRegex).get().replaceAll("(\\(|\\))", ""));
    }
    /**
     * Test the resolveBracket method for an expression that contain brackets.
     * Method should compute the expressions within brackets and replace the bracket expressions with their results.
     * The resulting expression should not contain any brackets.
     */
    @Test
    void testResolveBrackets(){
        Calculator calculator = new Calculator();
        assertEquals("3.0+10.0", calculator.resolveBrackets("(3^3/3^2)+(-2+4*3)"));
    }
    /**
     * Test the compute method for a valid expression.
     * Method should compute the expression and return the correct answer
     */
    @Test
    void testComputeExpression(){
        Calculator calculator = new Calculator();
        assertEquals("13.0", calculator.compute("3^3/3^2-2+4*3"));
    }

    /**
     * Test the compute method for an expression that include division by zero.
     * Method should return undefined
     */
    @Test
    void testComputeExpressionWithDivisionByZero(){
        Calculator calculator = new Calculator();
        assertEquals(calculator.undefined, calculator.compute("3^3/0-2+4*3"));
    }

    /**
     * Test the that method removeAllSpace removes all spaces in the received string and return string with no spaces
     */
    @Test
    void testRemoveAllSpace(){
        Calculator calculator = new Calculator();
        assertEquals("3^3/0-2+4*3", calculator.removeAllSpace("3^ 3/ 0-2+ 4 *3"));
    }

    /**
     * Test the method isValidExpression with a valid expression.
     * The method checks if the passed expression is a valid mathematical expression.
     * Should return true.
     */
    @Test
    void testIsValidateExpressionWithValidExpression(){
        Calculator calculator = new Calculator();
        assertTrue(calculator.isValidExpression("3^3/3^2-2+4*3"));
    }
    /**
     * Test the method isValidExpression with a valid expression.
     * The method checks if the passed expression is a valid mathematical expression.
     * Should return true.
     */
    @Test
    void testIsValidateExpressionWithValidExpressionContainingNegativeNumbers(){
        Calculator calculator = new Calculator();
        assertTrue(calculator.isValidExpression("3^-3/3^2-2+4*-3"));
    }

    /**
     * Test the method isValidExpression with a invalid expression containing letter.
     * The method checks if the passed expression is a valid mathematical expression.
     * Should return False.
     */
    @Test
    void testValidateExpressionWithExpressionContainingLetters(){
        Calculator calculator = new Calculator();
        assertFalse(calculator.isValidExpression("3^3/c^2-2+4*3"));
    }

    /**
     * Test the method isValidExpression with a invalid expression containing no operators.
     * The method checks if the passed expression is a valid mathematical expression.
     * Should return False.
     */
    @Test
    void testValidateExpressionWithExpressionContainingNoOperators(){
        Calculator calculator = new Calculator();
        assertFalse(calculator.isValidExpression("33.004"));
    }

    /**
     * Test the method isValidExpression with a invalid expression containing nested brackets.
     * The method checks if the passed expression is a valid mathematical expression.
     * Should return False.
     */
    @Test
    void testValidateExpressionWithExpressionContainingNestedBrackets(){
        Calculator calculator = new Calculator();
        assertFalse(calculator.isValidExpression("(3^(3/3^2)-2+4*3)"));
    }

    /**
     * Test the method isValidExpression with a invalid expression containing unclosed.
     * The method checks if the passed expression is a valid mathematical expression.
     * Should return False.
     */
    @Test
    void testValidateExpressionWithExpressionMissingClosingBrackets(){
        Calculator calculator = new Calculator();
        assertFalse(calculator.isValidExpression("(3^3/2^2-2+4*3"));
    }
    /**
     * Test the method isValidExpression with a invalid expression containing unclosed.
     * The method checks if the passed expression is a valid mathematical expression.
     * Should return False.
     */
    @Test
    void testValidateExpressionWithExpressionMissingOpeningBrackets(){
        Calculator calculator = new Calculator();
        assertFalse(calculator.isValidExpression("3^3/2^2-2+4)*3"));
    }
    /**
     * Test the method isValidExpression with a invalid expression containing no operators.
     * The method checks if the passed expression is a valid mathematical expression.
     * Should return False.
     */
    @Test
    void testValidateExpressionWithMissingOperatorBeforeOpeningBracket(){
        Calculator calculator = new Calculator();
        assertFalse(calculator.isValidExpression("3(2+4)*3"));
    }
    /**
     * Test the method isValidExpression with a invalid expression containing no operators.
     * The method checks if the passed expression is a valid mathematical expression.
     * Should return False.
     */
    @Test
    void testValidateExpressionWithMissingOperatorAfterClosingBracket(){
        Calculator calculator = new Calculator();
        assertFalse(calculator.isValidExpression("3*(2+4)3"));
    }
    /**
     * Test the method isValidExpression with a invalid expression containing improper use of operators
     * The method checks if the passed expression is a valid mathematical expression.
     * Should return False.
     */
    @Test
    void testValidateExpressionWithImproperUseOfOperators(){
        Calculator calculator = new Calculator();
        assertFalse(calculator.isValidExpression("2-*3"));
    }
    /**
     * Test the method formatResult which remove trialing zeros from final result
     * Should remove all trailing zeros.
     */
    @Test
    void testFormatResult(){
        Calculator calculator = new Calculator();
        assertEquals("3",calculator.formatResult("3.0"));
    }

    /**
     * Test the method formatSignOperators which replaces '--' with '+' and '+-' with '-'
     * Should replace '--' with '+'.
     */
    @Test
    void testFormatSignOperatorsWithDoubleMinus(){
        Calculator calculator = new Calculator();
        assertEquals("3+4",calculator.formatSignOperators("3--4"));
    }
    /**
     * Test the method formatSignOperators which replaces '--' with '+' and '+-' with '-'
     * Should replace '+-' with '-'.
     */
    @Test
    void testFormatSignOperatorsWithPlusMinus(){
        Calculator calculator = new Calculator();
        assertEquals("3-4", calculator.formatSignOperators("3+-4"));
    }

    /**
     * Reads expressions and answers from file expressions.json and
     * returns the expressions and answers as a stream of strings.
     * The expression and answer is seperated by an equals to sign
     * @return a stream of strings
     */
    private static Stream<String> readTestValidationExpressions(){
        String fileDir = Path.of(".", "lab2_expressions").toString();
        String filename= "expressions.json";
        JSONParser parser = new JSONParser();
        try (Reader reader = new FileReader(Paths.get(fileDir, filename).toString())) {
            JSONObject jsonObject = (JSONObject) parser.parse(reader);
            Set<String> keys = jsonObject.keySet();
            ArrayList<String> expressions = new ArrayList<>();

            for(String key:keys){
                expressions.add( key + "=" + jsonObject.get(key));

            }
            return expressions.stream();
        } catch (IOException | ParseException e) {
            e.printStackTrace();
            return Stream.empty();
        }
    }

    /**
     * test function computeInput, which computes the input expression and returns the result
     * or returns the expression if it is not a valid mathematical expression.
     * Runs test for each of the expressions in provided expression.json file.
     * The expressions are passed using the @MethodSource
     */
    @ParameterizedTest
    @MethodSource("readTestValidationExpressions")
    public void testComputeInput(String s){
        String[] expression = s.split("=");
        var exp = new Calculator();
        assertEquals(expression[1], exp.compute(expression[0]));
    }
}