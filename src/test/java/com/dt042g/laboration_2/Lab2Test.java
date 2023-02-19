package com.dt042g.laboration_2;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Set;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
/**
 * @Author: Honorine Lima
 * username:holi1900
 * Course: DT042G
 * version: 1
 *
 *
 * This class is a calculator class that takes a mathematical expression in the from of a string,
 * checks if it is a valid mathematical expression and
 * if it is valid, it computes the expression according to order of operator precedence
 *  that is  brackets, exponent, division, multiplacation, addition, subtraction and returns the answer
 * if the expression is not a valid expression, it returns the expression without white spaces
 *
 */
public class Lab2Test {
    /**
     *test the class design integrity by testing that the field "expression" exist
     */
    @Test
    void testExpressionFieldFound(){
        var expression1 = new Lab2("14-11");
        assertDoesNotThrow(()->expression1.getClass().getDeclaredField("expression"));
    }

    /**
     *test the class design integrity by testing that the method "performAdditionAndSubtraction()" exist
     */
    @Test
    void testPerformAdditionAndSubtractionMethodFound(){
        var expression1 = new Lab2("14-11");
        assertDoesNotThrow(()->expression1.getClass().getMethod("performAdditionAndSubtraction"));
    }
    /**
     * test function performAdditionAndSubtraction with only subtraction in the expression.
     * This should compute all subtractions in the input expression.
     */
    @Test
    void testPerformAdditionAndSubtractionWithOnlySubtractionInInput(){
        var expression1 = new Lab2("14-11");
        assertEquals("3", expression1.performAdditionAndSubtraction());
        var expression2 = new Lab2("-3-5-4");
        assertEquals("-12", expression2.performAdditionAndSubtraction());
    }

    /**
     * test function performAdditionAndSubtraction with only addition in the expression.
     * This should compute all additions in the input expression.
     */
    @Test
    void testPerformAdditionAndSubtractionWithOnlyAdditionInInput(){
        var expression1 = new Lab2("5+8");
        assertEquals("13", expression1.performAdditionAndSubtraction());
        var expression2 = new Lab2("-3+4+5+1");
        assertEquals("7", expression2.performAdditionAndSubtraction());
    }

    /**
     * test function performAdditionAndSubtraction with addition and subtraction the expression.
     * This should compute all additions in the input expression.
     */
    @Test
    void testPerformAdditionAndSubtractionInInput(){
        var expression1 = new Lab2("3+3-4");
        assertEquals("2", expression1.performAdditionAndSubtraction());
        var expression2 = new Lab2("-3-4+5+1");
        assertEquals("-1", expression2.performAdditionAndSubtraction());
    }



    /**
     * Test addSpaceAroundOperatorsInInput method which adds a space before and after all operators
     * except for the negative sign of a number which can occur att the beginning and end of a
     */
    @Test
    public void testAddSpaceAroundOperatorsInInput(){
        assertEquals("3 + 4", Lab2.addSpaceAroundOperatorsInInput("3+4"));
        //Do not add space around '-' operator that represents negative numbers that can occur
        //at the beginning of the expression or after an open bracket
        assertEquals("-3 + 4 * (-4 + 2)", Lab2.addSpaceAroundOperatorsInInput("-3+4*(-4+2)"));

    }

    /**
     *test the class design integrity by testing that the method "performMultiplicationAndDivision" exist
     */
    @Test
    void testPerformMultiplicationAndDivisionMethodFound(){
        var expression1 = new Lab2("14*11");
        assertDoesNotThrow(()->expression1.getClass().getMethod("performMultiplicationAndDivision"));
    }

    /**
     * test function performMultiplicationAndDivision with only multiplication in the expression.
     * This should compute all multiplication in the input expression
     */
    @Test
    public void testPerformMultiplicationAndDivisionWithOnlyMultiplication(){
        var expression1 = new Lab2("4*5");
        assertEquals("20", expression1.performMultiplicationAndDivision());
        var expression2 = new Lab2("-5*5");
        assertEquals("-25", expression2.performMultiplicationAndDivision());
        var expression3 = new Lab2("-5*5+4*5");
        assertEquals("-25+20", expression3.performMultiplicationAndDivision());

    }

    /**
     * test function performMultiplicationAndDivision with only division in the expression.
     * This should compute all division in the input expression
     */
    @Test
    public void testPerformMultiplicationAndDivisionWithOnlyDivision(){
        var expression1 = new Lab2("-4/4");
        assertEquals("-1", expression1.performMultiplicationAndDivision());
        var expression2 = new Lab2("4/5-2/1");
        assertEquals("1-2", expression2.performMultiplicationAndDivision());
    }

    /**
     * test function performMultiplicationAndDivision with division first in the expression.
     * This should compute in the order the operator comes in the input expression
     */
    @Test
    public void testPerformMultiplicationAndDivisionWithDivisionFirst(){
        var expression1 = new Lab2("-4/4*2");
        assertEquals("-2", expression1.performMultiplicationAndDivision());
    }

    /**
     * test function performMultiplicationAndDivision with multiplication firstin the expression.
     * This should compute in the order the operator comes in the input expression
     */
    @Test
    public void testPerformMultiplicationAndDivisionWithMultiplicationFirst(){
        var expression1 = new Lab2("-4/4");
        assertEquals("-1", expression1.performMultiplicationAndDivision());
        var expression2 = new Lab2("4/5-2/1");
        assertEquals("1-2", expression2.performMultiplicationAndDivision());
    }

    /**
     *test the class design integrity by testing that the method "performAllExponentsInInput" exist.
     */
    @Test
    void testPerformAllExponentsInInputMethodFound(){
        var expression1 = new Lab2("14*11");
        assertDoesNotThrow(()->expression1.getClass().getMethod("performAllExponentsInInput"));
    }

    /**
     * test function performAllExponentsInInput that performs all exponents in the input expression
     */
    @Test
    public void testPerformAllExponentsInInput(){
        var expression1 = new Lab2("-4+4^2");
        assertEquals("-4+16", expression1.performAllExponentsInInput());
        var expression2 = new Lab2("5^3-2^4");
        assertEquals("125-16", expression2.performAllExponentsInInput());
    }

    /**
     *test the class design integrity by testing that the method "resolveBracketsInInput" exist.
     */
    @Test
    void testPerformAllResolveBracketsInInputMethodFound(){
        var expression1 = new Lab2("14*11");
        assertDoesNotThrow(()->expression1.getClass().getMethod("resolveBracketsInInput"));
    }

    /**
     * test function resolveBracketsInInput that computes expressions inside brackets in the input expression
     */
    @Test
    public void testResolveBracketsInInput(){
        var expression1 = new Lab2("3-5+4-1");
        assertEquals("3-5+4-1", expression1.resolveBracketsInInput());
        var expression2 = new Lab2("3-(5-4)-1");
        assertEquals("3-1-1", expression2.resolveBracketsInInput());

    }

    /**
     *test the class design integrity by testing that the method "ComputeInput" exist.
     */
    @Test
    void testComputeInputMethodFound(){
        var expression1 = new Lab2("14*11");
        assertDoesNotThrow(()->expression1.getClass().getMethod("computeInput"));
    }

    /**
     * test function performAdditionAndSubtraction within computeInput  where addition comes first in the expression.
     * This should compute all subtractions and additions in the order they appear in the input expression.
     */
    @Test
    public void testPerformAdditionAndSubtractionWithAdditionFirst(){
        var expression1 = new Lab2("8+7*4-3");
        assertEquals("33", expression1.computeInput());
        var expression2 = new Lab2("4+3*8-7");
        assertEquals("21", expression2.computeInput());
    }

    /**
     * test function performAdditionAndSubtraction within computeInput where addition comes first in the expression.
     * This should compute all subtractions and additions in the order they appear in the input expression.
     */
    @Test
    public void testPerformAdditionAndSubtractionWithSubtractionFirst(){
        var expression2 = new Lab2("8-7*4+3");
        assertEquals("-17", expression2.computeInput());
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
        var exp = new Lab2(expression[0]);
        assertEquals(expression[1], exp.computeInput());
    }

    /**
     *test the class design integrity by testing that the method "validateInput" exist.
     */
    @Test
    void testValidateInputMethodFound(){
        var expression1 = new Lab2("14*11");
        assertDoesNotThrow(()->expression1.getClass().getMethod("validateInput"));
    }

    /**
     * Test that the function validateInput which checks if input is a valid
     * mathematical expression and returns true if the expression is valid and false
     * if invalid
     */
    @Test

    public void testValidateInput(){
        var expression1 = new Lab2("14-11");
        assertTrue(expression1.validateInput());
        var expression2 = new Lab2("10^3");
        assertTrue(expression2.validateInput());
    }

    /**
     * Tests removeWhiteSpacesInString method which if the white spaces are removed from a string
     */
    @Test
    @DisplayName("should remove all white spaces from string")
    public void testRemoveSpacesInString(){
        assertEquals("2+3+4", Lab2.removeSpacesInString("2 +  3 + 4"), "should removes whitespaces");
    }

    /**
     * Test isANumber which checks if the character in a char is a number
     */
    @Test
    @DisplayName("Test if character is a number")
    public void testIsANumber()
    {
        //should return true if the passed char is a number
        assertTrue(Lab2.isANumber('3'));
        //should return false if the passed char is a letter
        assertFalse(Lab2.isANumber('b'));
        //should return false if the passed char is an operator
        assertFalse(Lab2.isANumber('+'));
        //should return false if the passed char is a bracket
        assertFalse(Lab2.isANumber(')'));
    }

    /**
     * Test isAnOperator which checks if a character is an operator
     */
    @Test
    @DisplayName("Test is character is an operator")
    public void testIsAnOperator()
    {
        //should assert false if the passed char is not one of
        //the operators; '+', '-', '*', '/', '^'
        assertFalse(Lab2.isAnOperator('3'));
        assertFalse(Lab2.isAnOperator(')'));
        assertFalse(Lab2.isAnOperator('('));
        assertFalse(Lab2.isAnOperator('b'));

        //should return true if the passed char is one of
        //the operators; '+', '-', '*', '/', '^'
        assertTrue(Lab2.isAnOperator('+'));
        assertTrue(Lab2.isAnOperator('-'));
        assertTrue(Lab2.isAnOperator('^'));
        assertTrue(Lab2.isAnOperator('/'));
        assertTrue(Lab2.isAnOperator('*'));

    }

    /**
     * Test if a character is a bracket
     */
    @Test
    @DisplayName("Test if character is an operator")
    public void testIsABracket()
    {
        //should assert false if the passed char is not a bracket
        assertFalse(Lab2.isABracket('3'));
        assertFalse(Lab2.isABracket('+'));
        assertFalse(Lab2.isABracket('b'));
        //should assert true if the passed char is a bracket
        assertTrue(Lab2.isABracket('('));
        assertTrue(Lab2.isABracket(')'));
    }

    /**
     * Tests if the input expression intered in calculator contains
     * valid characters, that is only numbers, operators and brackets
     *
     */
    @Test
    @DisplayName("Test if input contain oly valid characters")
    public void testValidateCharactersInInput(){
        //should assert false if string contains any characters other than numbers
        //operators and brackets
        assertFalse(Lab2.validateCharactersInInput("b+e+2+4"));
        assertFalse(Lab2.validateCharactersInInput("2+4!"));
        //should assert true if string contains only numbers or operators or brackets
        assertTrue(Lab2.validateCharactersInInput("2+4"));
        assertTrue(Lab2.validateCharactersInInput("2+4(3*8)"));
        assertTrue(Lab2.validateCharactersInInput("14-11"));
    }

    /**
     * Test InputContainsOperator method that checks is a char is an operator
     */
    @Test
    @DisplayName("Input should have an operator")
    public void testInputContainsOperator(){
        assertFalse(Lab2.inputContainsOperator("12345678"));
        assertTrue(Lab2.inputContainsOperator("15+4"));
        assertTrue(Lab2.inputContainsOperator("14-11"));
        assertTrue(Lab2.inputContainsOperator("10^3"));
    }

    /**
     * test  method inputContainsBrackets which checks if input have a bracket
     */
    @Test @DisplayName("Test if Input have brackest")
    public void testInputContainsBrackets(){
        //assert true if input contains brackets
        assertTrue(Lab2.inputContainsBrackets("(12)"));
        assertTrue(Lab2.inputContainsBrackets("(45564"));
        assertTrue(Lab2.inputContainsBrackets("45564)"));
        assertTrue(Lab2.inputContainsBrackets("4(5564)"));
        assertFalse(Lab2.inputContainsBrackets("45564"));

    }

    /**
     * Test validate ProperUseOfBracket method which checks if the input
     * for correct use of brackets.
     * number of open and close brackets should be equal
     * open bracket should come before closed brackets
     * open brackets should not be followed by an operator
     * unless it is a negative sign
     * close bracket should be followed by an operator or nothing
     *
     */
    @Test
    public void testValidateProperUseOfBrackets(){
        //should assert false if the number of open brackets is not equals to
        //number close brackets in the expression
        assertFalse(Lab2.validateProperUseOfBrackets("(2+3*5"));
        assertFalse(Lab2.validateProperUseOfBrackets("2+3)*5"));


        //should assert false if the number of close bracket comes before
        //open bracket in the expression
        assertFalse(Lab2.validateProperUseOfBrackets(")2+(3*5"));
        //should assert false expression contains empty brackets
        assertFalse(Lab2.validateProperUseOfBrackets("()+2+3*5"));
        //should assert false if a number follows closed brackets directly
        //without being seperated by an operator
        assertFalse(Lab2.validateProperUseOfBrackets("(2+3)5"));
        //should assert false if an open bracket follows closed brackets directly
        //without being seperated by an operator
        assertFalse(Lab2.validateProperUseOfBrackets("(2+3)(3*5)"));
        //should assert false if an operator other than '-' follows an open brackets directly
        assertFalse(Lab2.validateProperUseOfBrackets("2+(+3*5)"));

        assertTrue(Lab2.validateProperUseOfBrackets("2+3*5"));
        assertTrue(Lab2.validateProperUseOfBrackets("(2+3)*5"));
        assertTrue(Lab2.validateProperUseOfBrackets("(2+3*5)"));
        assertTrue(Lab2.validateProperUseOfBrackets("(-2+3*5)"));
        assertTrue(Lab2.validateProperUseOfBrackets("2+(3*5)"));
        assertTrue(Lab2.validateProperUseOfBrackets("10^3"));
    }

    /**
     * Test validateOperatorPosition method which checks proper use of operator
     */
    @Test
    public void testValidateOperatorPosition(){
        //test check expression where first character is an operator but not '-'
        assertFalse(Lab2.validateOperatorPosition("+2+3*5"));
        //test check expression where last character is not an operator
        assertFalse(Lab2.validateOperatorPosition("2+3*5-"));
        //test check expression containing two consecutive operators
        assertFalse(Lab2.validateOperatorPosition("2+-3*5"));
        //test check expression where first character is an operator but not '-'
        assertFalse(Lab2.validateOperatorPosition("+2+3*5"));
        //test check expression where last character is not an operator
        assertFalse(Lab2.validateOperatorPosition("2+3*5-"));
        //test check expression containing two consecutive operators
        assertFalse(Lab2.validateOperatorPosition("2+-3*5"));

        //test check expression where first character is not an operator
        assertTrue(Lab2.validateOperatorPosition("2+3*5"));
        //test check expression where first character is '-'
        assertTrue(Lab2.validateOperatorPosition("-2+3*5"));
        assertTrue(Lab2.validateOperatorPosition("10^3"));
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
}
