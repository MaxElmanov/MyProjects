package ru.semantic.analysis.logics;

import ru.semantic.analysis.constants.Priority;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PostfixFormExecutor
{
    private StringBuilder expression;
    private StringBuilder postfixForm = new StringBuilder();
    private Deque<Character> stack = new ArrayDeque<>(); //operations
    private Pattern operationPattern = Pattern.compile("[+*/(-]{1}", Pattern.CASE_INSENSITIVE);
    private Pattern letterPattern = Pattern.compile("[a-zA-Z]{1}|[0-9]{1,}", Pattern.CASE_INSENSITIVE);

    public PostfixFormExecutor() { }

    public PostfixFormExecutor(String expression)
    {
        this.expression = new StringBuilder(expression);
    }

    public String getPostfixForm()
    {
        return commonGetPostfixForm(expression);
    }

    public String getPostfixForm(String expression)
    {
        return commonGetPostfixForm(new StringBuilder(expression));
    }

    private void checkExpressionByRules()
    {
        String exp = expression.toString().replace(" ", "");

        for (int i = 0; i < exp.length() - 2; i++) {
            String symbol_1 = String.valueOf(exp.charAt(i));
            String symbol_2 = String.valueOf(exp.charAt(i + 1));
            String symbol_3 = String.valueOf(exp.charAt(i + 2));

            Matcher operationMatcher_1 = operationPattern.matcher(symbol_1);
            Matcher operationMatcher_2 = operationPattern.matcher(symbol_2);
            Matcher operationMatcher_3 = operationPattern.matcher(symbol_3);

            Matcher letterMatcher_1 = letterPattern.matcher(symbol_1);
            Matcher letterMatcher_2 = letterPattern.matcher(symbol_2);
            Matcher letterMatcher_3 = letterPattern.matcher(symbol_3);

            if (operationMatcher_1.matches() && operationMatcher_2.matches() || letterMatcher_1.matches() && letterMatcher_2.matches()) {

                if (!symbol_1.equals("(") && !symbol_1.equals(")") && !symbol_2.equals("(") && !symbol_2.equals(")")) {
                    throwError("Error: Expression consists from double or more letters or numbers. May use only numbers (0-9) and letters (a-z).");
                }
            }

            if (i == 0 && operationMatcher_1.matches() && !symbol_1.equalsIgnoreCase("(")||
                operationMatcher_1.matches() && operationMatcher_2.matches() && operationMatcher_3.matches())
            {
                throwError("Error: Must not use negative a letter or a number. You may forget about operand before sign '-' ");
            }
        }
    }

    private void throwError(String errorTest)
    {
        try {
            throw new Exception(errorTest);
        }
        catch (Exception e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }

    private String commonGetPostfixForm(StringBuilder expression)
    {
        checkExpressionByRules();

        //checking for emptiness "expression"
        if (expression.toString().isEmpty() || expression == null) {
            try {
                throwError("Error: To use function \"getPostfixForm()\" you should fill up the \"expression\" with constructor.");
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }

        for (int i = 0; i < expression.length(); i++) {
            makeAlgorithm(expression.charAt(i));
        }

        pushStackCharsInResult();

        return postfixForm.toString();
    }

    private void pushStackCharsInResult()
    {
        while (!stack.isEmpty()) {
            postfixForm.append(stack.pop());
        }
    }

    private void makeAlgorithm(Character character)
    {
        // ' ' (space) is not valid character for function "makeAlgorithm(Character character)"
        if (character.equals(' ')) {
            return;
        }

        //operations
        Matcher operationMatcher = operationPattern.matcher(character.toString());
        if (operationMatcher.matches()) {
            if (!stack.isEmpty() && !stack.peekFirst().equals('(')) {
                checkOperationPriority(character, stack.peekFirst());
            }
            stack.push(character);
            return;
        }

        //operands
        Matcher letterMatcher = letterPattern.matcher(character.toString());
        if (letterMatcher.matches()) {
            postfixForm.append(character);
            return;
        }

        //open bracket
        if (character.equals(')')) {
            while (!stack.peekFirst().equals('(')) {
                postfixForm.append(stack.pop());
            }
            //remove last element "(" from stack
            stack.pop();
            return;
        }

    }

    private void checkOperationPriority(Character currentChar, Character headChar)
    {
        byte currentCharPriority = setOperationPriority(currentChar);
        byte headstackCharPriority = setOperationPriority(headChar);

        if (currentCharPriority < headstackCharPriority) {
            while (!stack.isEmpty()) {
                if (stack.peekFirst().equals('(')) {
                    break;
                }

                postfixForm.append(stack.pop());
            }
        }
    }

    private byte setOperationPriority(Character character)
    {
        byte priority = 0;

        switch (character) {
            case '+': {
                priority = Priority.sum;
                break;
            }
            case '-': {
                priority = Priority.sub;
                break;
            }
            case '*': {
                priority = Priority.mul;
                break;
            }
            case '/': {
                priority = Priority.div;
                break;
            }
            case '(': {
                priority = Priority.open_bracket;
                break;
            }
            default: {
                try {
                    throwError("Such a operation is not in here.");
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        return priority;
    }
}
