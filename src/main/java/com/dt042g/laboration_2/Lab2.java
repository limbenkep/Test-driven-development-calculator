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

    /**
     *
     * @param expression
     * @return
     */
    public static String performAllAdditionInInput(String expression) {
        if(!expression.contains("+")){
            return expression;
        }
        boolean startWithNeg = expression.charAt(0) == '-';
        if(startWithNeg){
            StringBuilder sb = new StringBuilder(expression);
            expression = sb.deleteCharAt(0).toString();
        }
        List<String>  subExpressions = Arrays.asList(expression.split("-"));
        StringBuilder result1 = new StringBuilder();
        for(int i = 0; i<subExpressions.size(); i++){
            String subExp = subExpressions.get(i);
            if(subExp.contains("+")){
                List<String>  numberStrings = Arrays.asList(subExp.split("\\+"));
                int result;
                if(startWithNeg  && i==0){
                    result = -Integer.parseInt(numberStrings.get(0));
                }else{
                    result =Integer.parseInt(numberStrings.get(0));
                }
                for(int j=1; j<numberStrings.size(); j++){
                    result += Integer.parseInt(numberStrings.get(j));
                }
                result1.append(result);
            } else{
                if(startWithNeg  && i==0){
                    result1.append("-").append(subExpressions.get(i));
                }else{
                    result1.append(subExpressions.get(i));
                }
            }

            if(i<subExpressions.size()-1){
                result1.append("-");
            }

        }
        return result1.toString();
    }

    /**
     * This function resolves all exponents in the input expression
     * and replace the resolved parts with the solution
     * This function is meant to be used for solving equations in
     * order of operator precedence
     * it is assumed that input does not contain brackets but may contain ^,/,*,+,-
     * @param expression mathematical expression to be solved
     * @return a reduces math expression with all exponents computed.
     */
    public static String performAllMultiplicationsInInput(String expression) {
        String regex = "(\\+|-)";
        if(!expression.contains("*")){
            return expression;
        }
        String resultString = expression;
        String[] subExpressions = expression.split(regex);
        for (String subExp : subExpressions) {
            if (subExp.contains("*")) {
                List<String> numberStrings = Arrays.asList(subExp.split("\\*"));
                int result = Integer.parseInt(numberStrings.get(0));
                for (int j = 1; j < numberStrings.size(); j++) {
                    result *= Integer.parseInt(numberStrings.get(j));
                }
                String resultStr = "" + result;
                resultString = resultString.replace(subExp, resultStr);
            }
        }
        return resultString;
    }

    public static String performAllDivisionInInput(String expression) {
        String regex = "[\\+\\*-]";
        if(!expression.contains("/")){
            return expression;
        }
        String resultString = expression;
        String[] subExpressions = expression.split(regex);
        for (String subExp : subExpressions) {
            if (subExp.contains("/")) {
                List<String> numberStrings = Arrays.asList(subExp.split("/"));
                int result = Integer.parseInt(numberStrings.get(0));
                for (int j = 1; j < numberStrings.size(); j++) {
                    double temp = (double)result/Integer.parseInt(numberStrings.get(j));
                    result = (int) Math.round(temp);
                }
                String resultStr = "" + result;
                resultString = resultString.replace(subExp, resultStr);
            }
        }
        return resultString;
    }

    public static String performAllExponentsInInput(String expression) {
        String sign = "^";
        String regex = "[\\+\\*-/]";
        if(!expression.contains(sign)){
            return expression;
        }
        String resultString = expression;
        String[] subExpressions = expression.split(regex);
        for (String subExp : subExpressions) {
            if (subExp.contains(sign)) {
                List<String> numberStrings = Arrays.asList(subExp.split("\\^"));
                int result = (int) Math.pow(Integer.parseInt(numberStrings.get(0)), Integer.parseInt(numberStrings.get(1)));
                String resultStr = "" + result;
                resultString = resultString.replace(subExp, resultStr);
            }
        }
        return resultString;
    }

    public static String resolveBracketsInInput(String expression) {
        //repeat this until all brackets are resolved
        while(expression.contains("(")){
            //Get the indices of the open and close brackets
            int startIndex = expression.indexOf("(") + 1;
            int endIndex = expression.indexOf(")");
            //get a copy of the expression withing the bracket
            String brackeString = expression.substring(startIndex, endIndex);
            // assign the expression in the bracket to input and compute input
            String result = brackeString;
            result = performAllExponentsInInput(result);
            result = performAllDivisionInInput(result);
            result = performAllMultiplicationsInInput(result);
            result = performAllAdditionInInput(result);
            result = "" + performAllSubtractionInInput(result);
            //replace the brackets and it's content in the copy of the original Input
            //with the current value of input which is the answer to the expression in brackets
            //assign the edited original input to input
            expression = expression.replace("(" + brackeString + ")", result);
        }
        return expression;
    }

    /**
     * removes all spaces in a string
     * @return string without spaces
     */
    public static String removeSpacesInString(String string){
        return string.replaceAll("\\s","");
    }

    public static long computeInput(String expression) {
        System.out.println("Expression: " + expression);
        //remove spaces from expression
        expression = removeSpacesInString(expression);
        //compute all expressions in brackets
        expression = resolveBracketsInInput(expression);
        //compute all exponents
        expression = performAllExponentsInInput(expression);
        //compute all divisions
        expression = performAllDivisionInInput(expression);
        //compute all multiplications
        expression = performAllMultiplicationsInInput(expression);
        //compute all additions
        expression = performAllAdditionInInput(expression);
        //compute all subtractions
        return performAllSubtractionInInput(expression);
    }
}
