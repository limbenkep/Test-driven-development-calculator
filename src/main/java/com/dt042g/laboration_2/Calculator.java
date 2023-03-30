package com.dt042g.laboration_2;

import java.text.DecimalFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

/**
 *  This class is a calculator class whose compute method takes a mathematical expression as a string
 *  parse the expression, compute in order of operator precedence and return the answer as a string.
 *  The class also  has methods that validate the passed expression, remove white spaces,
 *  perform the different operation and format the results. All these are used in the compute
 *  method to compute the expression. Computation is done in order of operator precedence whereby
 *  brackets comes first, exponent second, division and multiplication third, and addition and subtraction comes last
 *  if the expression is not a valid expression, the message with invalid expression is returned.
 */
public class Calculator {
    protected final String additionOrSubtractionRegex = "((-?\\d+(\\.\\d+)?-\\d+(\\.\\d+)?)|(-?\\d+(\\.\\d+)?\\+-?\\d+(\\.\\d+)?))";
    protected final String multiplicationOrDivisionRegex = "((\\d+(\\.\\d+)?\\*-?\\d+(\\.\\d+)?)|(\\d+(\\.\\d+)?/-?\\d+(\\.\\d+)?))";
    protected final String posNrExponentRegex = "\\d+(\\.\\d+)?\\^-?\\d+(\\.\\d+)?";
    protected final String bracketRegex = "\\(-?\\d+(\\.\\d+)?([\\^*/\\-+]-?\\d+(\\.\\d+)?)+\\)";
    protected final String validExpressionRegex = "((-?\\d+(\\.\\d+)?)|(\\(-?\\d+(\\.\\d+)?([\\^*/\\-+]-?\\d+(\\.\\d+)?)+\\)))([\\^*/\\-+]((-?\\d+(\\.\\d+)?)|(\\(-?\\d+(\\.\\d+)?([\\^*/\\-+]-?\\d+(\\.\\d+)?)+\\))))+";
    protected final String undefined = "Undefined";

    /**
     * Checks the expression for first match to a regex pattern and returns the match.
     * @param expression string to be searched
     * @param regex regex string to be matched
     * @return an optional holding the first match if found or null if not found
     */
    protected Optional<String> getFirstRegexMatch(String expression, String regex){
        Matcher matcher = Pattern.compile(regex)
                .matcher(expression);
        if(!matcher.find()){
            return Optional.empty();
        }
        return Optional.ofNullable(matcher.group());
    }
    /**
     * Computes addition in a string expression made of two numbers seperated an addition sign.
     * @param expression string expression made of two numbers seperated by an addition sign
     * @return the result of the addition as a string
     */
    protected String performAdditionBetweenTwoNumbers(String expression){
        return String.valueOf(Arrays.stream(expression.split("\\+"))
                .map(Float::parseFloat)
                .reduce(0f, Float::sum));
    }

    /**
     * Computes subtraction in a string expression made of two numbers seperated a subtraction sign.
     * @param expression string expression made of two numbers seperated by a subtraction sign
     * @return the result of the subtraction as a string
     */
    protected String performSubtractionBetweenTwoNumbers(String expression){
        return Stream.of(expression)
                .map(s->s.split("-"))
                .map(a->new Float[]{Float.parseFloat(a[0]), Float.parseFloat(a[1])})
                .map(a->a[0]- a[1])
                .toList()
                .get(0).toString();
    }

    /**
     * Computes multiplication in a string expression made of two numbers seperated a multiplication sign.
     * @param expression string expression made of two numbers seperated by a multiplication sign
     * @return the result of the multiplication as a string
     */
    protected String performMultiplicationBetweenTwoNumbers(String expression){
        return String.valueOf(Arrays.stream(expression.split("\\*"))
                .map(Float::parseFloat)
                .reduce(1f, (acc, val)->acc*val));
    }

    /**
     * Computes division in a string expression made of two numbers seperated a division sign.
     * @param expression string expression made of two numbers seperated by a division sign
     * @return the result of the division as a string
     */
    protected String performDivisionBetweenTwoNumbers(String expression){
        String[] list = expression.split("/");
        float val1 = Float.parseFloat(list[0]);
        float val2 = Float.parseFloat(list[1]);
        if(val2!=0){
            return Float.toString(val1/val2);
        }
        System.out.println("Division by zero is undefined!");
        return undefined;
    }

    /**
     * Computes power in a string expression made of two numbers seperated a power sign.
     * @param expression string expression made of two numbers seperated by a power sign
     * @return the result of the power as a string
     */
    protected String performPowerOfBetweenTwoNumbers(String expression){
        return Stream.of(expression)
                .map(s -> s.split("\\^"))
                .map(a -> Math.pow(Double.parseDouble(a[0]), Double.parseDouble(a[1])))
                .map(Object::toString)
                .toList()
                .get(0);
    }

    /**
     *Computes all addition and subtraction in a passed string expression and returns the updated expression
     * @param expression valid a mathematical expression as string
     * @return reduced passed expression were all addition and subtraction are computed and replaced by the answer
     */
    protected String computeAllAdditionAndSubtraction(String expression){
        while(Pattern.compile(additionOrSubtractionRegex)
                .matcher(expression).find()){
            Optional<String> match = getFirstRegexMatch(expression, additionOrSubtractionRegex);
            if(match.isPresent() && match.get().contains("+")){
                expression = expression.replaceFirst(additionOrSubtractionRegex,
                        performAdditionBetweenTwoNumbers(match.get()));
            } else if (match.isPresent() && match.get().contains("-")){
                expression = expression.replaceFirst(additionOrSubtractionRegex,
                        performSubtractionBetweenTwoNumbers(match.get()));
            }
            expression = formatSignOperators(expression);
        }
        return expression;
    }

    /**
     *Computes all multiplication and division in a passed string expression and returns the updated expression
     * @param expression valid a mathematical expression as string
     * @return reduced passed expression were all multiplication and division are computed and replaced by the answer
     */
    protected String computeAllMultiplicationAndDivision(String expression){
        while(Pattern.compile(multiplicationOrDivisionRegex)
                .matcher(expression).find()){
            Optional<String> match = getFirstRegexMatch(expression, multiplicationOrDivisionRegex);
            if(match.isPresent() && match.get().contains("/")){
                String partialResult = performDivisionBetweenTwoNumbers(match.get());
                if(Objects.equals(partialResult, undefined)){
                    expression = undefined;
                }else {
                    expression = expression.replaceFirst(multiplicationOrDivisionRegex,
                            partialResult);
                }

            }else if(match.isPresent() && match.get().contains("*")){
                expression = expression.replaceFirst(multiplicationOrDivisionRegex,
                        performMultiplicationBetweenTwoNumbers(match.get()));
            }
            expression = formatSignOperators(expression);
        }
        return expression;
    }

    /**
     *Computes all exponent in a passed string expression and returns the updated expression
     * @param expression valid a mathematical expression as string
     * @return reduced passed expression were all exponent are computed and replaced by the answer
     */
    protected String computeAllExponents(String expression){
        while(Pattern.compile(posNrExponentRegex)
                .matcher(expression).find()){
            expression = expression.replaceFirst(posNrExponentRegex,
                    performPowerOfBetweenTwoNumbers(getFirstRegexMatch(expression, posNrExponentRegex).get()));
            expression = formatSignOperators(expression);
        }
        return expression;
    }

    /**
     * Computes all expressions enclosed in brackets in a passed string expression and returns the updated expression
     * @param expression valid a mathematical expression as string
     * @return reduced passed expression were all brackets are computed and replaced by the answer
     */
    protected String resolveBrackets(String expression){
        while(Pattern.compile(bracketRegex)
                .matcher(expression).find()){
            expression = expression.replaceFirst(bracketRegex,
                    compute(getFirstRegexMatch(expression, bracketRegex).get().replaceAll("(\\(|\\))", "")));
            expression = formatSignOperators(expression);
        }
        return expression;
    }

    /**
     * Removes all white spaces
     * @param expression string to be formatted
     * @return  formatted string containing no white spaces
     */
    protected String removeAllSpace(String expression){
        return expression.replace(" ", "");
    }

    /**
     * Checks is the passed expression is a valid mathematical expression by comparing it to the
     * validExpressionRegex regex pattern
     * @param expression math expression to be validated
     * @return true is expression is valid else false
     */
    protected boolean isValidExpression(String expression){
        return Pattern.compile(validExpressionRegex)
                .matcher(removeAllSpace(expression)).matches();
    }

    /**
     * Called after to any part of the expression has been computed to
     * take care to computation that result in negative numbers.
     * Replaces '--' with '+' and '+-' with '-' if present in the expression
     * @param expression math expression
     * @return formatted expression
     */
    protected String formatSignOperators(String expression){
        expression = expression.replaceAll("\\+-", "-");
        expression = expression.replaceAll("--", "+");
        return expression;
    }

    /**
     * removes trailing zeros from results if present
     * @param s result to be formatted
     * @return formatted result
     */
    protected  String formatResult(String s){
        if(Objects.equals(s, undefined)){
            return s;
        }
        float val  = Float.parseFloat(s);
        DecimalFormat format = new DecimalFormat("0.#");
        return format.format(val);

    }

    /**
     * Computes expressions a passed string expression and returns the answer
     * @param expression valid a mathematical expression as string
     * @return reduced passed expression were all exponent are computed and replaced by the answer
     */
    public String compute(String expression){
        if(!isValidExpression(expression)){
            return "Invalid expression";
        }
        return Stream.of(expression)
                .map(this::removeAllSpace)
                .map(this::resolveBrackets)
                .map(this::computeAllExponents)
                .map(this::computeAllMultiplicationAndDivision)
                .map(this::computeAllAdditionAndSubtraction)
                .map(this::formatResult)
                .map(StringBuilder::new)
                .reduce(new StringBuilder(), StringBuilder::append)
                .toString();
    }
}
