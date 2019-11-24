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
    private Pattern operationPattern = Pattern.compile("[+*/-]{1}", Pattern.CASE_INSENSITIVE);
    private Pattern letterPattern = Pattern.compile("[a-zA-Z]{1}|[0-9]{1,}", Pattern.CASE_INSENSITIVE);

    public PostfixFormExecutor()
    {
    }

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

        for (int i = 0; i < exp.length() - 1; i++) {
            String symbol_1 = String.valueOf(exp.charAt(i));
            String symbol_2 = String.valueOf(exp.charAt(i + 1));

            Matcher OperationMatcher_1 = operationPattern.matcher(symbol_1);
            Matcher OperationMatcher_2 = operationPattern.matcher(symbol_2);

            Matcher LetterMatcher_1 = letterPattern.matcher(symbol_1);
            Matcher LetterMatcher_2 = letterPattern.matcher(symbol_2);

            // 99 + dd  ||  9 +- d
            if (OperationMatcher_1.matches() && OperationMatcher_2.matches() || LetterMatcher_1.matches() && LetterMatcher_2.matches()) {
                throwError("Error: Expression consists from double or more letters or numbers. May use only numbers (0-9) and letters (a-z).");
            }

            // -9 + c
            if (i == 0 && OperationMatcher_1.matches() && LetterMatcher_2.matches()) {
                throwError("Error: Must not use unary operations.");
            }

            // (-b)
            if (symbol_1.equalsIgnoreCase("(") && OperationMatcher_2.matches()) {
                throwError("Error: Must not use unary operations.");
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
        //checking for emptiness "expression"
        if (expression.toString().isEmpty() || expression == null) {
            throwError("Error: To use function \"getPostfixForm()\" you should fill up the \"expression\" with constructor.");
        }

        checkExpressionByRules();

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

        //open bracket
        if (character.equals('(')) {
            stack.push(character);
            return;
        }

        //operations
        Matcher operationMatcher = operationPattern.matcher(character.toString());
        if (operationMatcher.matches()) {
            if (!stack.isEmpty()) {
                checkOperationPriority(character, stack.peekFirst());
            }
            else {
                stack.push(character);
            }
            return;
        }

        //operands
        Matcher letterMatcher = letterPattern.matcher(character.toString());
        if (letterMatcher.matches()) {
            postfixForm.append(character);
            return;
        }

        //close bracket
        if (character.equals(')')) {
            while (!stack.peekFirst().equals('(')) {
                postfixForm.append(stack.pop());
            }
            //remove last element "(" from stack
            stack.pop();
            return;
        }

        // if program went to this place. It means that none of condition above haven't worked. It's wrong, cause' current symbol is not supported.
        throwError("current symbol is not supported");
    }

    private void checkOperationPriority(Character currentChar, Character headChar)
    {
        byte currentCharPriority = setOperationPriority(currentChar);
        byte headStackCharPriority = setOperationPriority(headChar);

        if (currentCharPriority >= headStackCharPriority) {
            stack.push(currentChar);
        }
        else {
            postfixForm.append(stack.pop());
            checkOperationPriority(currentChar, stack.peekFirst());
        }
    }

    private byte setOperationPriority(Character character)
    {
        if (character == null) {
            return Priority.default_priority;
        }

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
