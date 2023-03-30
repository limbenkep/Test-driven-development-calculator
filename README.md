# Laboration 2

## Environment & Tools
Window 10 pro, git version 2.33.0.windows.2, Intellij 2022.2.1

## Purpose
The aim of this lab is to use test driven development to write 
a calculator program that receives entries as arguments passed from command line, computes the expression 
according to operator precedence and print the result to console. The operators supported are '+.-.*,/,^,(,)'

## Procedure 
To achieve the goal of this lab, test were written in the Lab2Test file and 
implementation of the calculator program was done in the Lab2. The program was 
designed to support brackets, exponent, division, multiplication, addition and subtraction 
following the operator precedence brackets, exponent, division and multiplication,and, 
addition and subtraction in order of highest to lowest. The calculator was implemented 
starting from the simplest expression, i.e expression with only operators with the lowest 
precedence(addition and subtraction) up to expression including expression with the highest 
precedence. The implementation 
also starts by assuming that the expressions passed were valid expressions and implement validation 
at the end. 

First a test were written for a class data field expression and the field was created to hold the 
expression under computation. A constructor was created which receives the expression as 
parameter and initializes the expression field. The test checks that the expression field exist using 
reflection whereby ``.getClass().getDeclaredField("expression")`` is used on a Lab2 object to get the 
the expression field which throws an exception if the field does not exist and  ``assertDoesNotThrow()`` 
method to assert that no exception is thrown. 

Secondly, a test for addition was written that asserts that an expression with only addition gives 
the correct answer using the ``assertEquals()``method. A test was also written to assert that the
``performAdditionAndSubtraction()`` method exist by introspection using ``.getClass().getMethod("performAdditionAndSubtraction")`` 
method on a Lab2 object which will throw and exception if it does not exist. The ``assertDoesNotThrow()``method is
used to assert that an exception is not thrown. The addition method was implemented to performs all addition 
in the expression. Then a test for just subtraction and another test for addition and subtraction was written 
and the addition method was refactored to perform both addition and subtraction in the order they come in, 
in the expression. The method starts by checking if the expression contains an addition or subtraction operators. If it doesn't 
then it does nothing. If it contains the operators, then the expression is parsed by adding spaces before 
and after all operators except  a negative sign of a negative number. The string is then split at  the spaces 
to a list of strings that are either a number or an operator. The list is then iterated over and when the 
desired operator is found the strings at the indices before and after the operator are parsed to 
long and the computation performed for the two values. The part of the expression which represents the 
computed part of the expression is then replaced by the answer from the computation, 
the iteration is stopped and the process is repeated for the 
updated expression until there are no additions and subtractions in the expression.

Thirdly, tests and implementation for division and multiplication method was implemented following the same logic as mentioned above for addition 
and subtraction, starting with multiplication then division then both.

Next, a test was written for the method to perform exponent and a test for checking that the method to perform exponents
exists. Then the method was implemented by splitting the expression, which checks if the expression has the expont sign,
parse the expression and compute all exponents and update the espression string by replacing the rsolved parts with the answer. 

Next , a test was written for the method to compute the expression within brackets and another test for 
checking that the method exist. The indices of the first open bracket and the first closed baracket was gotten and 
used to get the expression within the bracket. A new Lab2 object is initialized with the sub-expression and computed. 
the bracket expression is then replaced by the answer.

Next, a parameterized test is then written for a method that computes the whole expression in order of operator precedence 
and another test to check that the method exist. The method test receives expressions and answers from another method that 
reads the expression.json file and returns the expressions as a stream of string. 
This method calls all the above mentioned methods in order of precedence and returns the answer as a string.

Validations where then made to ensure that the expression was a valid expression by checking the characters, positions 
of operator and use of bracket. Static methods where used for parts of the validations and all methods started with a 
test and then the implementation. The validation was then added to the method that computes the whole expression such that
the method returns the expression unchanged and print "Invalid expression" if the expression is invalid. 
Finally, the code was refactored and the comments were written for uncommented codes and the report was written.

## Discussion
The purpose of this lab was accomplished and all the expressions from the provide expression.json file yielded the 
correct result. All code that was implemented in the Lab2 class started with a test from the Lab2Test class as stipulated by 
the TDD. The program meets the requirements of the lab but does not work as a proper calculator. For example it does not 
work if the answer for part of the expression was negative. But from the provided examples this was not included in the lab.
I chose to work with the expression as a string because i found it easier to replace parts of the string with their answers.
Overall, I believe the exercise was a good training for TDD and reflection and introspection.

## Version 2
Based on the feedback received from the first submissions,  the implementation for the Calculator has been isolated to a 
separate  class, Calculator, with a separate test class, CalculatorTest. The implementation was also refactored to use 
regular expression in parsing the expression and Stream API. Different regular expression patterns were developed, one  
for addition and subtraction, one for multiplication and division, one for exponent, one for bracket and one for valid 
mathematical expression. These regex were used to validate the passed expression and extracts parts of the expression 
to be evaluated according to operator precedence.
This version also fixes the bug that caused strange results with division by zero. When this happens 'Undefined' 
is returned and a message is also printed that states that 'Division by zero is undefined!'. Float was also used 
instead of integers to allow handling of decimals.
This version also include both behavioral test and integrity test for class design.