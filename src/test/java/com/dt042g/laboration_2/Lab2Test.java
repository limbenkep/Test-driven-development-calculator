package com.dt042g.laboration_2;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
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
}
