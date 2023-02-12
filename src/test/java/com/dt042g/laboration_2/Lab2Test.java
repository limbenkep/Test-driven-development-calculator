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
}
