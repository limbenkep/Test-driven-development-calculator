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
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
/**
 * This class contains unit test for the Calculator class.
 * @Author: Honorine Lima
 * username:holi1900
 * Course: DT042G
 * Date: 2023-03-30
 */
class CalculatorTest {
    /**
     * Test the class design integrity by testing that the Calculator
     * class package name is "Calculator"
     */
    @Test
    void testCalculatorClassPackageName() {
        Calculator calculator = new Calculator();
        assertEquals("com.dt042g.laboration_2", calculator.getClass().getPackageName());
    }
    /**
     * test the class design integrity by testing that the Calculator
     * class simple name is "Calculator"
     */
    @Test
    void testCalculatorClassSimpleName() {
        Calculator calculator = new Calculator();
        assertEquals("Calculator", calculator.getClass().getSimpleName());
    }

    /**
     * test the class design integrity by testing that the Calculator
     * class name is "com.dt042g.laboration_2.Calculator"
     */
    @Test
    void testCalculatorClassName() {
        Calculator calculator = new Calculator();
        assertEquals("com.dt042g.laboration_2.Calculator", calculator.getClass().getName());
    }
    /**
     * test the class design integrity by testing that the Calculator class is public
     */
    @Test
    void testCalculatorClassIsPublic() throws ClassNotFoundException {
        assertTrue(Modifier.isPublic(Class.forName("com.dt042g.laboration_2.Calculator").getModifiers()));
    }
    /**
     * Test the class design integrity by testing that the Calculator class superclass is Object
     */
    @Test
    void testCalculatorClassSuperClassIsObject(){
        Calculator calculator = new Calculator();
        assertEquals("Object", calculator.getClass().getSuperclass().getSimpleName());
    }
    /**
     * Test the class design integrity by testing that the Calculator class does not implement any interfaces
     */
    @Test
    void testCalculatorClassHasNoImplementedInterfaces(){
        Calculator calculator = new Calculator();
        assertEquals(0, calculator.getClass().getInterfaces().length);
    }
    /**
     * Test the class design integrity by testing that the Calculator class has one constructor
     */
    @Test
    void testCalculatorClassOneConstructor(){
        Calculator calculator = new Calculator();
        assertEquals(1, calculator.getClass().getConstructors().length);
    }
    /**
     * Test the class design integrity by testing that the Calculator class constructor takes no parameters
     */
    @Test
    void testCalculatorClassConstructorHasNoParams(){
        Constructor<?>[] constructors = Calculator.class.getConstructors();
        assertEquals(0, constructors[0].getParameterCount());
    }

    /**
     * Test the class design integrity by checking the Calculator class declared methods are all found
     */
    @Test
    public void testCalculatorClassDeclaredMethods() {
        Method[] methods = Calculator.class.getDeclaredMethods();
        List<String> actualMethods = getMethodNames(methods);
        System.out.println(actualMethods);
        assertTrue(actualMethods.containsAll(Arrays.asList(
                "compute", "computeAllMultiplicationAndDivision", "performMultiplicationBetweenTwoNumbers",
                "performSubtractionBetweenTwoNumbers", "getFirstRegexMatch", "resolveBrackets",
                "removeAllSpace", "formatResult", "isValidExpression", "performDivisionBetweenTwoNumbers",
                "computeAllExponents", "performPowerOfBetweenTwoNumbers", "formatSignOperators",
                "computeAllAdditionAndSubtraction", "performAdditionBetweenTwoNumbers")));
    }
    /**
     * Test the class design integrity by checking the Calculator class declared fields are all found
     */
    @Test
    void testCalculatorClassDeclaredFields(){
        Field[] fields = Calculator.class.getDeclaredFields();
        List<String> actualFields = getFieldNames(fields);
        assertEquals(6, actualFields.size());
        assertTrue(actualFields.containsAll(Arrays.asList("additionOrSubtractionRegex",
                "multiplicationOrDivisionRegex", "posNrExponentRegex", "bracketRegex",
                "validExpressionRegex", "undefined")));
    }

    /**
     * Receives an array of Methods and return a list of the names of the Methods
     * @param methods Array of methods
     * @return list of the names of the passed methods
     */
    private static List<String> getMethodNames(Method[] methods) {
        List<String> methodNames = new ArrayList<>();
        for (Method method : methods)
            methodNames.add(method.getName());
        return methodNames;
    }

    /**
     * Receives an array of Fields and return a list of the names of the Fields
     * @param fields Array of fields
     * @return list of the names of the passed fields
     */
    private static List<String> getFieldNames(Field[] fields) {
        List<String> fieldNames = new ArrayList<>();
        for (Field field : fields)
            fieldNames.add(field.getName());
        return fieldNames;
    }
    /**
     * test the class design integrity by testing that the method "compute" which is called to perform
     * calculation has a public modifier.
     */
    @Test
    void testComputeMethodModifier() throws  NoSuchMethodException {
        assertTrue(Modifier.isPublic(Calculator.class.getDeclaredMethod("compute", String.class).getModifiers()));
    }

    /**
     * Test performAdditionBetweenTwoNumbers that parse expression and add two numbers
     * Should return the sum of the two numbers in the expression as a string
     */
    @Test
    void testPerformAddition(){
        Calculator calculator = new Calculator();
        assertEquals("6.0", calculator.performAdditionBetweenTwoNumbers("3+3"));
    }
    /**
     * Test performSubtractionBetweenTwoNumbers that parse expression and subtract the second number from the first.
     * Should return the value of the first number minus the second number as a string
     */
    @Test
    void testPerformSubtraction(){
        Calculator calculator = new Calculator();
        assertEquals("0.0", calculator.performSubtractionBetweenTwoNumbers("3-3"));
    }
    /**
     * Test performMultiplicationBetweenTwoNumbers that parse expression and find the product of the two numbers.
     * Should return the product of the two numbers as a string
     */
    @Test
    void testPerformMultiplication(){
        Calculator calculator = new Calculator();
        assertEquals("9.0", calculator.performMultiplicationBetweenTwoNumbers("3*3"));
    }
    /**
     * Test performDivisionBetweenTwoNumbers that parse expression and divide the first number by the second.
     * Should return the value of the first number divided by the second number as a string
     */
    @Test
    void testPerformDivision(){
        Calculator calculator = new Calculator();
        assertEquals("2.5", calculator.performDivisionBetweenTwoNumbers("5/2"));
    }
    /**
     * Test performDivisionBetweenTwoNumbers with division by zero.
     * Should return "Undefined"
     */
    @Test
    void testPerformDivisionWithDivisionByZero(){
        Calculator calculator = new Calculator();
        assertEquals(calculator.undefined, calculator.performDivisionBetweenTwoNumbers("5/0"));
    }

    /**
     * Test that the method performPowerOfBetweenTwoNumbers that parse expression and perform exponent and return correct answer.
     * Should return the value of the first number exponent the second number as a string
     */
    @Test
    void testPerformPowerOf(){
        Calculator calculator = new Calculator();
        assertEquals("27.0", calculator.performPowerOfBetweenTwoNumbers("3^3"));
    }

    /**
     * Test that the method getFirstRegexMatch that received a regex and an expression, finds and returns the
     * first match for the regex pattern
     */
    @Test
    void testGetFirstRegexMatchMatchFound(){
        Calculator calculator = new Calculator();
        assertEquals("3-3", calculator.getFirstRegexMatch("3-3*3", "-?\\d-\\d+").get());
    }
    /**
     * Test that the method getFirstRegexMatch that when the passed expression does not have any match
     * Should return an empty optional which through a NoSuchElementException when the get() method is called on it.
     */
    @Test
    void testGetFirstRegexMatchMatchNotFound(){
        Calculator calculator = new Calculator();
        assertThrows(NoSuchElementException.class,()->calculator.getFirstRegexMatch("3-3*3", "-?\\d/\\d+").get());
    }

    /**
     * Test  the additionOrSubtractionRegex regex pattern which supposed to match addition
     * and subtraction parts of the expression using expression containing addition.
     * The getFirstRegexMatch should identify the first match
     */
    @Test
    void testAdditionAndSubtractionRegexWithAddition(){
        Calculator calculator = new Calculator();
        assertEquals("3+3", calculator.getFirstRegexMatch("3+3*3", calculator.additionOrSubtractionRegex).get());
    }
    /**
     * Test  the additionOrSubtractionRegex regex pattern which supposed to match addition
     * and subtraction parts of the expression using expression containing subtraction.
     * The getFirstRegexMatch should identify the first match
     */
    @Test
    void testAdditionAndSubtractionRegexWithSubtraction(){
        Calculator calculator = new Calculator();
        assertEquals("3-3", calculator.getFirstRegexMatch("3-3*3", calculator.additionOrSubtractionRegex).get());
    }
    /**
     * Test  the additionOrSubtractionRegex regex pattern which supposed to match addition
     * and subtraction parts of the expression using expression containing negative numbers.
     * The getFirstRegexMatch should identify the first match even when match include negative
     */
    @Test
    void testAdditionOrSubtractionRegexWithSubtractionWithNegativeNumber(){
        Calculator calculator = new Calculator();
        assertEquals("-3-3", calculator.getFirstRegexMatch("-3-3*3", calculator.additionOrSubtractionRegex).get());
    }
    /**
     * Test  the additionOrSubtractionRegex regex pattern which supposed to match addition
     * and subtraction parts of the expression using expression containing no match.
     * The getFirstRegexMatch should return an empty optional that throws a NoSuchElementException when get() is called
     */
    @Test
    void testGetFirstRegexMatchForAdditionOrSubtractionRegexWithMatchNotFound(){
        Calculator calculator = new Calculator();
        assertThrows(NoSuchElementException.class,()->calculator.getFirstRegexMatch("3/3*3", calculator.additionOrSubtractionRegex).get());
    }
    /**
     * Test the method computeAllAdditionAndSubtraction that compute all addition
     * and subtraction in the expression in the order they come in from left to right
     */
    @Test
    void testComputeAllAdditionAndSubtraction(){
        Calculator calculator = new Calculator();
        assertEquals("9.0", calculator.computeAllAdditionAndSubtraction("3+3-3+6"));
    }
    /**
     * Test  the multiplicationOrDivisionRegex regex pattern which supposed to match multiplication
     * and division parts of the expression using expression containing multiplication first.
     * The getFirstRegexMatch should identify the first match
     */
    @Test
    void testMultiplicationOrDivisionRegexWithMultiplication(){
        Calculator calculator = new Calculator();
        assertEquals("3*3", calculator.getFirstRegexMatch("3-3*3/2", calculator.multiplicationOrDivisionRegex).get());
    }
    /**
     * Test  the multiplicationOrDivisionRegex regex pattern which supposed to match multiplication
     * and division parts of the expression using expression containing division first.
     * The getFirstRegexMatch should identify the first match
     */
    @Test
    void testMultiplicationOrDivisionRegexWithDivision(){
        Calculator calculator = new Calculator();
        assertEquals("3/3", calculator.getFirstRegexMatch("-3-3/3*2", calculator.multiplicationOrDivisionRegex).get());
    }
    /**
     * Test  the multiplicationOrDivisionRegex regex pattern which supposed to match multiplication
     * and division parts of the expression using expression containing no match.
     * The getFirstRegexMatch should return empty optional
     */
    @Test
    void testGetFirstRegexMatchForMultiplicationOrDivisionWithMatchNotFound(){
        Calculator calculator = new Calculator();
        assertFalse(calculator.getFirstRegexMatch("3-3+3", calculator.multiplicationOrDivisionRegex).isPresent());
    }

    /**
     * Test computeAllMultiplicationAndDivision that performs all multiplication
     * and division with an expression containing division and multiplication.
     * Should return undefined
     */
    @Test
    void testComputeAllMultiplicationAndDivision(){
        Calculator calculator = new Calculator();
        assertEquals("3.0+6", calculator.computeAllMultiplicationAndDivision("3*3/3+6"));
    }

    /**
     * Test computeAllMultiplicationAndDivision that performs all multiplication
     * and division with an expression containing division by zero.
     * Should return undefined
     */
    @Test
    void testComputeAllMultiplicationAndDivisionWithDivisionByZero(){
        Calculator calculator = new Calculator();
        assertEquals(calculator.undefined, calculator.computeAllMultiplicationAndDivision("-3-3/0"));
    }

    /**
     * Test the string used as regex pattern to match exponent parts of the expression
     * Should return the first match
     */
    @Test
    void testExponentRegexFound(){
        Calculator calculator = new Calculator();
        assertEquals("3.0^3.0", calculator.getFirstRegexMatch("3-3.0^3.0", calculator.posNrExponentRegex).get());
    }
    /**
     * Test the string used as regex pattern to match exponent parts of the expression
     * in an expression with no match
     * Should return empty optional
     */
    @Test
    void testExponentRegexNotFound(){
        Calculator calculator = new Calculator();
        assertThrows(NoSuchElementException.class,()->calculator.getFirstRegexMatch("3-3+3", calculator.posNrExponentRegex).get());
    }

    /**
     * Test the method computeAllExponents that computes all exponents parts of the expression
     * and replace the computed parts with their answers
     */
    @Test
    void testComputeAllPowerOf(){
        Calculator calculator = new Calculator();
        assertEquals("27.0/9.0", calculator.computeAllExponents("3^3/3^2"));
    }

    /**
     * Test that the bracketRegex string can match parts of expression surrounded by brackets.
     * Should return the first match
     */
    @Test
    void testBracketRegex(){
        Calculator calculator = new Calculator();
        assertEquals("(3^3/3^2)", calculator.getFirstRegexMatch("(3^3/3^2)+(-2+4*3)", calculator.bracketRegex).get());
    }

    /**
     * Test removing bracket signs from matches returned by getFirstRegexMatch using bracketRegex
     * should return the expression within the brackets without the brackets
     */
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
        assertEquals("3+10", calculator.resolveBrackets("(3^3/3^2)+(-2+4*3)"));
    }
    /**
     * Test the compute method for a valid expression.
     * Method should compute the expression and return the correct answer
     */
    @Test
    void testComputeExpression(){
        Calculator calculator = new Calculator();
        assertEquals("13", calculator.compute("3^3/3^2-2+4*3"));
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
     * Test the method isValidExpression with an invalid expression containing letter.
     * The method checks if the passed expression is a valid mathematical expression.
     * Should return False.
     */
    @Test
    void testValidateExpressionWithExpressionContainingLetters(){
        Calculator calculator = new Calculator();
        assertFalse(calculator.isValidExpression("3^3/c^2-2+4*3"));
    }

    /**
     * Test the method isValidExpression with an invalid expression containing no operators.
     * The method checks if the passed expression is a valid mathematical expression.
     * Should return False.
     */
    @Test
    void testValidateExpressionWithExpressionContainingNoOperators(){
        Calculator calculator = new Calculator();
        assertFalse(calculator.isValidExpression("33.004"));
    }

    /**
     * Test the method isValidExpression with an invalid expression containing nested brackets.
     * The method checks if the passed expression is a valid mathematical expression.
     * Should return False.
     */
    @Test
    void testValidateExpressionWithExpressionContainingNestedBrackets(){
        Calculator calculator = new Calculator();
        assertFalse(calculator.isValidExpression("(3^(3/3^2)-2+4*3)"));
    }

    /**
     * Test the method isValidExpression with an invalid expression containing unclosed.
     * The method checks if the passed expression is a valid mathematical expression.
     * Should return False.
     */
    @Test
    void testValidateExpressionWithExpressionMissingClosingBrackets(){
        Calculator calculator = new Calculator();
        assertFalse(calculator.isValidExpression("(3^3/2^2-2+4*3"));
    }
    /**
     * Test the method isValidExpression with an invalid expression containing unclosed.
     * The method checks if the passed expression is a valid mathematical expression.
     * Should return False.
     */
    @Test
    void testValidateExpressionWithExpressionMissingOpeningBrackets(){
        Calculator calculator = new Calculator();
        assertFalse(calculator.isValidExpression("3^3/2^2-2+4)*3"));
    }
    /**
     * Test the method isValidExpression with an invalid expression containing no operators.
     * The method checks if the passed expression is a valid mathematical expression.
     * Should return False.
     */
    @Test
    void testValidateExpressionWithMissingOperatorBeforeOpeningBracket(){
        Calculator calculator = new Calculator();
        assertFalse(calculator.isValidExpression("3(2+4)*3"));
    }
    /**
     * Test the method isValidExpression with an invalid expression containing no operators.
     * The method checks if the passed expression is a valid mathematical expression.
     * Should return False.
     */
    @Test
    void testValidateExpressionWithMissingOperatorAfterClosingBracket(){
        Calculator calculator = new Calculator();
        assertFalse(calculator.isValidExpression("3*(2+4)3"));
    }
    /**
     * Test the method isValidExpression with an invalid expression containing improper use of operators
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