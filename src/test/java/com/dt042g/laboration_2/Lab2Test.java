package com.dt042g.laboration_2;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
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
    private JSONObject jsonObject;
    /**
     * test function performAdditionAndSubtraction with only subtraction in the expression.
     * This should compute all subtractions in the input expression.
     */
    @Test
    public void testPerformAllSubtractionInInput(){
        assertEquals(1, Lab2.performAdditionAndSubtraction("5-4"));
        assertEquals(-12, Lab2.performAdditionAndSubtraction("-3-5-4"));
    }

    /**
     * test function performAdditionAndSubtraction with only addition in the expression.
     * This should compute all additions in the input expression.
     */
    @Test
    public void testPerformAllAdditionInInput(){
        assertEquals("6", Lab2.performAdditionAndSubtraction("3+3"));
        assertEquals("7", Lab2.performAdditionAndSubtraction("-3+4+5+1"));
    }



    @Test
    public void testPerformMultiplicationAndDivision(){
        assertEquals("8-12", Lab2.performMultiplicationAndDivision("8-9*4/3"));
        assertEquals("16+3", Lab2.performMultiplicationAndDivision("8/2*4+3"));
    }
    /**
     * Test addSpaceAroundOperatorsInInput method which adds a space before and after all operators
     * except for the negative sign of a number which can occur att the beginning and end of a
     */
    @Test
    public void testaddSpaceAroundOperatorsInInput(){
        assertEquals("3 + 4", Lab2.addSpaceAroundOperatorsInInput("3+4"));
        //Do not add space around '-' operator that represents negative numbers that can occur
        //at the begining of the expression or after an open bracket
        assertEquals("-3 + 4 * (-4 + 2)", Lab2.addSpaceAroundOperatorsInInput("-3+4*(-4+2)"));

    }

    /**
     * test function performMultiplicationAndDivision with only multiplication in the expression.
     * This should compute all multiplication in the input expression
     */
    @Test
    public void testPerformMultiplicationAndDivisionWithOnlyMultiplication(){
        assertEquals("20", Lab2.performMultiplicationAndDivision("4*5"));
        assertEquals("-25", Lab2.performMultiplicationAndDivision("-5*5"));
        assertEquals("-25+20", Lab2.performMultiplicationAndDivision("-5*5+4*5"));

    }

    /**
     * test function performMultiplicationAndDivision with only division in the expression.
     * This should compute all division in the input expression
     */
    @Test
    public void testPerformMultiplicationAndDivisionWithOnlyDivision(){
        assertEquals("-1", Lab2.performMultiplicationAndDivision("-4/4"));
        assertEquals("1-2", Lab2.performMultiplicationAndDivision("4/5-2/1"));
    }

    /**
     * test function performMultiplicationAndDivision with division first in the expression.
     * This should compute in the order the operator comes in the input expression
     */
    @Test
    public void testPerformMultiplicationAndDivisionWithDivisionFirst(){
        assertEquals("-2", Lab2.performMultiplicationAndDivision("-4/4*2"));
    }

    /**
     * test function performMultiplicationAndDivision with multiplication firstin the expression.
     * This should compute in the order the operator comes in the input expression
     */
    @Test
    public void testPerformMultiplicationAndDivisionWithMultiplicationFirst(){
        assertEquals("-1", Lab2.performMultiplicationAndDivision("-4/4"));
        assertEquals("1-2", Lab2.performMultiplicationAndDivision("4/5-2/1"));
    }



    /**
     * test function performAllExponentsInInput that performs all exponents in the input expression
     */
    @Test
    public void testPerformAllExponentsInInput(){
        assertEquals("-4+16", Lab2.performAllExponentsInInput("-4+4^2"));
        assertEquals("125-16", Lab2.performAllExponentsInInput("5^3-2^4"));
    }

    /**
     * test function resolveBracketsInInput that computes expressions inside brackets in the input expression
     */
    @Test
    public void testResolveBracketsInInput(){
        assertEquals("3-5+4-1", Lab2.resolveBracketsInInput("3-5+4-1"));
        assertEquals("3-1-1", Lab2.resolveBracketsInInput("3-(5-4)-1"));

    }
    /**
     * test function performAdditionAndSubtraction within computeInput  where addition comes first in the expression.
     * This should compute all subtractions and additions in the order they appear in the input expression.
     */
    @Test
    public void testPerformAdditionAndSubtractionWithAdditionFirst(){
        assertEquals("33", Lab2.computeInput("8+7*4-3"));
        assertEquals("21", Lab2.computeInput("4+3*8-7"));
    }

    /**
     * test function performAdditionAndSubtraction within computeInput where addition comes first in the expression.
     * This should compute all subtractions and additions in the order they appear in the input expression.
     */
    @Test
    public void testPerformAdditionAndSubtractionWithSubtractionFirst(){
        assertEquals("-17", Lab2.computeInput("8-7*4+3"));
    }


    /**
     * test function computeInput that computes the input expression and returns the result
     * or returns the expression if it is not a valid mathematical expression
     */
    @Test
    public void testComputeInput(){
        readTestValidationExpressions();
        Set<String> keys = jsonObject.keySet();
        ArrayList<String> expressions = new ArrayList<>(keys);
        for(String expression:expressions){
            assertEquals("" + jsonObject.get(expression), Lab2.computeInput(expression));
        }
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
     * Test test checks if the character in a char is a number
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
     * Test if a character is an operator
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
        assertFalse(Lab2.validateInput("10^3"));
    }

    /**
     * Test validate ProperUseOfBracket method which checks if the input
     * for correct use of brackets.
     * number of open and close brackets should be equal
     * open bracket should come before closed brackets
     * open brackets should not be follwed by an operator
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
     * Test that the function validateInput which checks if input is a valid
     * mathemathical expression, returns true if the expression is valid and false
     * if invalid
     */
    @Test
    public void testValidateInput(){
        assertTrue(Lab2.validateInput("14-11"));
        assertTrue(Lab2.validateInput("10^3"));
    }


    private void readTestValidationExpressions(){
        String fileDir = Path.of(".", "lab2_expressions").toString();
        String filename= "expressions.json";
        JSONParser parser = new JSONParser();
        try (Reader reader = new FileReader(Paths.get(fileDir, filename).toString())) {
            jsonObject = (JSONObject) parser.parse(reader);
            Set<String> keys = jsonObject.keySet();
            ArrayList<String> expressions = new ArrayList<>(keys);
            System.out.println("Expression: " + expressions);
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }
}
