package com.dt042g.laboration_2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * @author: Honorine Lima
 * username:holi1900
 * Course: DT042G
 * Date: 2023-02-19
 * This class is a calculator class that takes a mathematical expression as a string
 * passed as an argument in command line, parse the expression, compute in order of
 * operator precedence and print the answer on the console.
 * If the expression is a valid mathematical expression the expression computed according
 * to order of operator precedence whereby brackets comes first, exponent second, division
 * and multiplication third, and addition and subtraction comes last
 * if the expression is not a valid expression, it returns the expression without white spaces
 */
public class Lab2 {
    static public void main(String... args) {
        if(args.length>0){
            String expression = args[0];
            Calculator calculator = new Calculator();
            String result = calculator.compute(expression);
            System.out.println(expression + " = " + result);
        }else {
            System.out.println("No expression was provided");
        }

    }
}
