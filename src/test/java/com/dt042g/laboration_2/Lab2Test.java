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
     * test function performAllSubtractionInInput that computes all subtractions in the input expression
     */
    @Test
    public void testPerformAllSubtractionInInput(){
        assertEquals(1, Lab2.performAllSubtractionInInput("5-4"));
        assertEquals(-12, Lab2.performAllSubtractionInInput("-3-5-4"));
    }

    /**
     * test function performAllAdditionInInput that performs all addition in the input expression
     */
    @Test
    public void testPerformAllAdditionInInput(){
        assertEquals("6", Lab2.performAllAdditionInInput("3+3"));
        assertEquals("1-6", Lab2.performAllAdditionInInput("-3+4-5+1"));
    }

    /**
     * test function performAllMultiplicationsInInput that performs all multiplication in the input expression
     */
    @Test
    public void testPerformAllMultiplicationsInInput(){
        assertEquals("-4+20", Lab2.performAllMultiplicationsInInput("-4+4*5"));
        assertEquals("20-1", Lab2.performAllMultiplicationsInInput("4*5-1"));
        assertEquals("-25", Lab2.performAllMultiplicationsInInput("-5*5"));
    }

    /**
     * test function performAllDivisionInInput that performs all division in the input expression
     */
    @Test
    public void testPerformAllDivisionInInput(){
        assertEquals("-1-5", Lab2.performAllDivisionInInput("-4/4-5"));
        assertEquals("1-1", Lab2.performAllDivisionInInput("4/5-1"));
    }

    /**
     * test function performAllMultiplicationsInInput that performs all multiplication in the input expression
     */
    @Test
    public void testPerformAllExponentsInInput(){
        assertEquals("-4+16", Lab2.performAllExponentsInInput("-4+4^2"));
        assertEquals("125-16", Lab2.performAllExponentsInInput("5^3-2^4"));
    }

    /**
     * test function performAllSubtractionInInput that performs all subtraction in the input expression
     */
    @Test
    public void testResolveBracketsInInput(){
        assertEquals("3-5+4-1", Lab2.resolveBracketsInInput("3-5+4-1"));
        assertEquals("3-1-1", Lab2.resolveBracketsInInput("3-(5-4)-1"));

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
            assertEquals(jsonObject.get(expression), Lab2.computeInput(expression));
        }
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
