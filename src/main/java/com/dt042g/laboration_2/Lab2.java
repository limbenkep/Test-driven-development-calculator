package com.dt042g.laboration_2;

/**
 * This program is a calculator that compute mathematical expression passed as a string and print the result.
 * The class is a utility class that acts as the main point for execution.
 * The expression is passed as by commandline, parser and computed and the result is printed to console.
 * The computation is done by the Calculator class.
 * @author: Honorine Lima
 * username:holi1900
 * Course: DT042G
 * Date: 2023-02-19
 */
public class Lab2 {
    /**
     * Receive argument array and check if array contains an expression. If yes, the calculator
     * method compute() is called to compute the expression and the result is printed to console.
     * If the argument array is empty, a message is printed to notify user that no expression was passed.
     * @param args passed expression
     */
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
