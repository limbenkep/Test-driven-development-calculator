package com.dt042g.laboration_2;

import java.util.Arrays;
import java.util.List;

public class Lab2 {
    static public void main(String... args) {
        System.out.println("Laboration 2");
    }

    /**
     * Receives expression as a string which is expected to contain only subtraction
     * Since subtraction is the last sign to be computed.
     * splits expression at '-' sign and
     * Performs all subtraction in the expression and returns the answer as an integer.
     * @param expression Mathematical expression to be computed
     * @return result of the comutation as an int
     */
    public static int performAllSubtractionInInput(String expression) {
        if(!expression.contains("-")){
            return Integer.parseInt(expression);
        }
        boolean startWithNeg= expression.charAt(0) == '-';
        List<String> list = Arrays.asList(expression.split("-"));

        int result;
        if(startWithNeg){
            result = -Integer.parseInt(list.get(1));
        }else{
            result =Integer.parseInt(list.get(0)) - Integer.parseInt(list.get(1));
        }
        if(list.size()>2){
            for(int i=2; i<list.size(); i++){
                result -= Integer.parseInt(list.get(i));
            }
        }
        return result;
    }
}
