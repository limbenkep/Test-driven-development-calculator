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
    private String expression;// Expression to be solved

    /**
     * Constructor takes the expression to be computed and initialize expression field
     * @param expression expression to be computed as string
     */
    public Lab2(String expression) {
        this.expression = expression;
    }

    /**
     * gets expression
     * @return the value of the expression field
     */
    public String getExpression() {
        return expression;
    }

    /**
     * Computes all addition and subtraction in the order they come in
     * and replace the resolves parts of the expression with their answers
     * @return expression after all additions and subtractions have been computed
     */
    public void performAdditionAndSubtraction() {
        while(expression.contains("+") || expression.contains("-")){
            if(!expression.contains("+") && expression.lastIndexOf("-") == 0){
                break;
            }
            String tempExp = addSpaceAroundOperatorsInInput(expression);
            String[] expComp = tempExp.split("\\s");
            for(int i = 0; i< expComp.length; i++){
                if(Objects.equals(expComp[i], "+")){
                    int ans = Integer.parseInt(expComp[i-1]) + Integer.parseInt(expComp[i+1]);
                    String subExp  =expComp[i-1] + expComp[i] + expComp[i+1];
                    expression = expression.replace(subExp, "" + ans);
                    break;
                } else if (Objects.equals(expComp[i], "-")) {
                    int ans = Integer.parseInt(expComp[i-1]) - Integer.parseInt(expComp[i+1]);
                    String subExp  = expComp[i-1] + expComp[i] + expComp[i+1];
                    expression = expression.replace(subExp, "" + ans);
                    break;
                }
            }
        }
    }

    /**
     * Computes all multiplication and division in the order they come in.
     * and replace the resolves parts of the expression with their answers
     * @return the reduced expression after all multiplication and division have been computed
     */
    public void performMultiplicationAndDivision() {
        while(expression.contains("/") || expression.contains("*")){
            String tempExp = addSpaceAroundOperatorsInInput(expression);
            String[] expComp = tempExp.split("\\s");
            for(int i = 0; i< expComp.length; i++){
                if(Objects.equals(expComp[i], "/")){
                    long ans = Math.round(Double.parseDouble(expComp[i-1]) / Double.parseDouble(expComp[i+1]));
                    String subExp  =expComp[i-1] + expComp[i] + expComp[i+1];
                    expression = expression.replace(subExp, "" + ans);
                    break;
                } else if (Objects.equals(expComp[i], "*")) {
                    long ans = Long.parseLong(expComp[i-1]) * Long.parseLong(expComp[i+1]);
                    String subExp  = expComp[i-1] + expComp[i] + expComp[i+1];
                    expression = expression.replace(subExp, "" + ans);
                    break;
                }
            }
        }
    }

    /**
     * This function resolves all exponents in the input expression
     * and replace the resolved parts with the solution
     * This function is meant to be used for solving equations in
     * order of operator precedence
     * it is assumed that input does not contain brackets but may contain ^,/,*,+,-
     * Replace the resolves parts of the expression with their answers
     * @return the reduced expression with all exponents computed.
     */

    public void performAllExponentsInInput() {
        String sign = "^";
        String regex = "[+*-/]";
        if(expression.contains(sign)){
            String resultString = expression;
            String[] subExpressions = expression.split(regex);
            for (String subExp : subExpressions) {
                if (subExp.contains(sign)) {
                    List<String> numberStrings = Arrays.asList(subExp.split("\\^"));
                    long result = (long) Math.pow(Integer.parseInt(numberStrings.get(0)), Integer.parseInt(numberStrings.get(1)));
                    String resultStr = "" + result;
                    expression = expression.replace(subExp, resultStr);
                }
            }
        }
    }

    /**
     * Computes all expression within brackets and replace the resolves parts of the expression
     * with their answers
     * @return the reduced expression after all brackets have been computed
     */
    public void resolveBracketsInInput() {
        //repeat this until all brackets are resolved
        while(expression.contains("(")){
            //Get the indices of the open and close brackets
            int startIndex = expression.indexOf("(") + 1;
            int endIndex = expression.indexOf(")");
            //get a copy of the expression withing the bracket
            String brackeString = expression.substring(startIndex, endIndex);
            // assign the expression in the bracket to input and compute input
            Lab2 subExp = new Lab2(brackeString);
            subExp.performAllExponentsInInput();
            subExp.performMultiplicationAndDivision();
            subExp.performAdditionAndSubtraction();
            String result = subExp.getExpression();
            //replace the brackets, and it's content in the copy of the original Input
            //with the current value of input which is the answer to the expression in brackets
            //assign the edited original input to input
            expression = expression.replace("(" + brackeString + ")", result);
        }

    }

    /**
     * removes all spaces in a string
     * @return string without spaces
     */
    public static String removeSpacesInString(String string){
        return string.replaceAll("\\s","");
    }

    public String computeInput() {
        System.out.println("Expression: " + expression);
        //remove spaces from expression
        expression = removeSpacesInString(expression);
        if(!validateInput()){
            System.out.println("invalid input");
            return expression;
        }
        //compute all expressions in brackets
        resolveBracketsInInput();
        //compute all exponents
        performAllExponentsInInput();
        //compute all divisions
        performMultiplicationAndDivision();
        //compute all multiplications
        performAdditionAndSubtraction();
        return expression;
    }

    /**
     * checks that input is a valid mathematical expression
     * @return true is valid else false
     */
    public boolean validateInput(){
        return !expression.isEmpty()
                && inputContainsOperator(expression)
                && validateCharactersInInput(expression)
                && validateProperUseOfBrackets(expression)
                && validateOperatorPosition(expression);
    }

    /**
     * adds a space before and after all operators
     * except for the negative sign of a number which
     * can occur att the beginning and end of a
     * @return copy of string with spaces added
     */
    public static String addSpaceAroundOperatorsInInput(String expression){
        StringBuilder result = new StringBuilder();
        //append first char which can be a negative sign for a number
        result.append(expression.charAt(0));
        for(int i=1; i<expression.length(); i++){
            char character = expression.charAt(i);
            //check if it's an operator and is not a sign operator that comes
            //after '(' then add spaces before and after operator
            if(isAnOperator(character) && expression.charAt(i-1) != '('){
                result.append(" ");
                result.append(character);
                result.append(" ");
            }
            //if the char does not come after '(' or is not an operator append to result without space
            if(expression.charAt(i-1) == '(' || !isAnOperator(character)){
                result.append(character);
            }
        }
        return result.toString();
    }

    /**
     * checks in a character is a number by check if the character can be parsed to an int
     * @param s character to be checked
     * @return true if charater is a number else false
     */
    public static boolean isANumber(char s) {
        String character = String.valueOf(s);
        try {
            Integer.parseInt(character);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    /**
     * checks if a char is an operator
     * @param character char to be checked
     * @return true if isi an operator else false
     */
    public static boolean isAnOperator(char character) {

        return character == '*' || character == '/' || character
                == '+' || character == '-' || character == '^';
    }

    /**
     * checks if a character is a bracket
     * @param character to be checked
     * @return true if it is a bracket else false
     */
    public static boolean isABracket(char character){
        return character == '(' || character == ')';
    }
    /**
     * checks if expression contains only that is only numbers, operators and brackets
     * @return true if all characters are valid else false
     */
    public static boolean validateCharactersInInput(String input){
        for(int i= 0; i<input.length(); i++){
            char character = input.charAt(i);
            if(!isANumber(character) && !isAnOperator(character) && !isABracket(character)){
                return false;
            }
        }
        return true;
    }

    /**
     * Checks if a string contains an operator
     * Operators are +,*,/
     * @param input the input string
     * @return index of the operator or -1
     */
    public static boolean inputContainsOperator(String input) {
        return input.contains("+")
                || input.contains("-")
                || input.contains("/")
                || input.contains("*")
                || input.contains("^");
    }

    public static boolean inputContainsBrackets(String input) {
        return input.contains("(") || input.contains(")");
    }

    public static boolean validateProperUseOfBrackets(String input) {
        int inputLength = input.length();
        List<Integer>indicesOfOpenBrackets = new ArrayList<>();
        List<Integer>indicesOfCloseBrackets = new ArrayList<>();
        for(int i=0; i<inputLength; i++){
            if(input.charAt(i) == '('){indicesOfOpenBrackets.add(i);}
            if(input.charAt(i) == ')'){indicesOfCloseBrackets.add(i);}
        }

        //check if unequal number of open and close brackets are used
        if(indicesOfOpenBrackets.size()!=indicesOfCloseBrackets.size()){
            return false;
        }
        //Check if close brackets come before tne open bracket.
        for(int i=0; i<indicesOfOpenBrackets.size(); i++){
            if(indicesOfCloseBrackets.get(i)<indicesOfOpenBrackets.get(i)){
                return false;
            }
        }

        //Check if string include empty brackets or contains a close,
        //then open bracket without an operator in between.
        if(input.contains("()") ||input.contains(")(")){return false;}

        for(int i=0; i<inputLength-1; i++){
            char character = input.charAt(i);
            char nextChar = input.charAt(i+1);
            //check that if a number is directly followed by an open bracket
            //without an operator inbetween
            if(isANumber(character) && nextChar=='('){return false;}

            //Check if an open bracket is followed by an operator without a number in between
            if(character=='(' && isAnOperator(nextChar)){
                if(nextChar!='-'){return false;}
            }
            //check if number is followed by open bracket without an operator in between
            if(isANumber(character) && nextChar == '('){return false;}
            //check if a number follows the closed bracket without any operator in between
            if(character==')' && isANumber(nextChar)){return false;}
            //check if number is followed by open bracket without an operator in between
            if(isAnOperator(character) && nextChar == ')'){return false;}
        }
        return true;
    }

    /**
     * checks for correct use of operators. The operator should not occur
     * at the begining of an expression unless it is a '-' sign for negative numbers
     * an operator should not be directly followed by another operator
     * an operator should not be used at the end of the expression
     * @return true if operators are used correctly else false
     */
    public static boolean validateOperatorPosition(String input) {
        int inputLength = input.length();
        char firstChar = input.charAt(0);

        //If the first character is an operator other that '-' which
        //denotes negative numbers, return false
        if(isAnOperator(firstChar) && firstChar!= '-'){return false;}

        //If the last character is an operator, it is not a valid
        //operator position. return false
        if(isAnOperator(input.charAt(inputLength-1))){return false;}

        //check if there are two consecutive opreators in the string and return false.
        for(int i=0; i<inputLength-1; i++){
            char character = input.charAt(i);
            char nextChar = input.charAt(i+1);
            if(isAnOperator(character) && isAnOperator(nextChar)){
                return false;
            }
        }
        return true;
    }



    static public void main(String... args) {
        if(args.length>0){
            String expression = args[0];
            Lab2 lab2 = new Lab2(expression);
            String result = lab2.computeInput();
            System.out.println(expression + " = " + result);
        }else {
            System.out.println("No expression was provided");
        }

    }
}
