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

    private String commonGetPostfixForm(StringBuilder expression)
    {
        //checking for emptiness "expression"
        if (expression.toString().isEmpty() || expression == null) {
            try {
                throw new Exception("Error: To use function \"getPostfixForm()\" you should fill up the \"expression\" with constructor.");
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
        Matcher matcher = Pattern.compile("[+*/(-]{1}", Pattern.CASE_INSENSITIVE).matcher(character.toString());
        if (matcher.matches()) {
            if (!stack.isEmpty() && !stack.peekFirst().equals('(')) {
                checkOperationPriority(character, stack.peekFirst());
            }
            stack.push(character);
            return;
        }

        //operands
        matcher = Pattern.compile("[a-zA-Z]{1}|[0-9]{1,}", Pattern.CASE_INSENSITIVE).matcher(character.toString());
        if (matcher.matches()) {
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
                if(stack.peekFirst().equals('(')) {
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
                    throw new Exception("Such a operation is not in here.");
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        return priority;
    }
}
