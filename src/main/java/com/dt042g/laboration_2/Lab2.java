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
                System.out.println("array after split at +:" + numberStrings);
                int result;
                if(startWithNeg  && i==0){
                    result = -Integer.parseInt(numberStrings.get(0));
                }else{
                    result =Integer.parseInt(numberStrings.get(0));
                }
                for(int j=1; j<numberStrings.size(); j++){
                    result += Integer.parseInt(numberStrings.get(j));
                }
                System.out.println("result: " + result);
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
     *
     * @param expression
     * @return
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
}
