package ru.semantic.analysis.logics;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TetradTableExecutor
{
    private StringBuilder table = new StringBuilder();
    private final static String T = "T";
    private static Integer numberT = 1;
    private Deque<String> stack = new ArrayDeque<>();

    public String makeTetradTable(String postfixForm)
    {
        //CAPTION
        createTableHeader(" Входной символ ", " 1 операнд ", " 2 операнд ", " Результат ", " Состояние стека ");

        for (int i = 0; i < postfixForm.length(); i++) {

            Character symbol = postfixForm.charAt(i);

            Matcher matcherOperations = Pattern.compile("[*/+-]{1,1}").matcher(symbol.toString());

            if (matcherOperations.matches()) {

                //clean stack
                String op_2 = stack.pop(); //operand 2
                String op_1 = stack.pop(); //operand 1

                String operation = String.valueOf(symbol);

                createTableRow(operation, op_1, op_2);
            }
            else {
                stack.push(symbol.toString());
                createTableRow(stack.getFirst(), null, null);
            }
        }

        return table.toString();
    }

    private String resetOperands(int operandNumber, String operand, String newNames)
    {
        int T_withNumberLength = newNames.length(); // length == 2 when T1 | length == 3 when T56 (last element in deque)

        String oldValue = operand; //op_1 == op_2. Whatever what value we will take

        // if we have T32 in op_1 and op_2 and T_withNumberLength = 2 => op_1 = T3 AND op_2 = 2
        if (operandNumber == 1) {
            return oldValue.substring(0, T_withNumberLength);
        }
        else if (operandNumber == 2) {
            return oldValue.substring(T_withNumberLength);
        }
        else {
            try {
                throw new Exception("A wrong operand number. Operand number can be 1 or 2 only.");
            }
            catch (Exception e) {
                e.printStackTrace();
            }
            finally {
                return null;
            }
        }
    }

    private String getOperandByNumber(int operandNum, int index, String postfixForm)
    {
        int backSteps = (operandNum == 1)
                        ? 2
                        : (operandNum == 2)
                          ? 1
                          : 0;

        String patternLetters = "(?=[a-zA-Z]{1,1})[^T]";

        String symbol = String.valueOf(postfixForm.charAt(index));

        Matcher matcherLetters = Pattern.compile(patternLetters)
                .matcher(symbol); //if symbol == [a-zA-Z] then return all except T letter
        Pattern patternNumbers = Pattern.compile("[0-9]{1,5}");

        while (index != 0 || backSteps != 0) {
            if (matcherLetters.matches()) {
                backSteps--;
                if (backSteps <= 0) {
                    break;
                }
            }

            if (symbol.equals(TetradTableExecutor.T)) {
                backSteps--;
                if (backSteps <= 0 || index == 0) {
                    StringBuilder strBuilderSymbol = new StringBuilder();

                    for (int i = index; i < postfixForm.length(); i++) {
                        String character = String.valueOf(postfixForm.charAt(i));
                        if (patternNumbers.matcher(character).matches() || character.equals(TetradTableExecutor.T)) {
                            strBuilderSymbol.append(character);
                        }
                        else {
                            break;
                        }
                    }

                    symbol = strBuilderSymbol.toString();
                    break;
                }
            }

            index--;
            symbol = String.valueOf(postfixForm.charAt(index));
            matcherLetters = Pattern.compile(patternLetters).matcher(symbol);
        }

        return symbol;
    }

    private void createTableHeader(String symbol, String op1, String op2, String operationResult, String stack)
    {
        StringBuilder tableRow = new StringBuilder();

        tableRow.append(String.format("|%-15.15s|", symbol));
        tableRow.append(String.format("|%-15.15s|", op1));
        tableRow.append(String.format("|%-15.15s|", op2));
        tableRow.append(String.format("|%-15.15s|", operationResult));
        tableRow.append(String.format("|%-15.15s|", stack));
        tableRow.append("\n");

        tableRow.append("-------------------------------------------------------------------------------------");
        tableRow.append("\n");

        table.append(tableRow);
    }

    private void createTableRow(String symbol, String op1, String op2)
    {
        StringBuilder tableRow = new StringBuilder();
        String newName = null;

        tableRow.append(createPartTableRow(symbol));
        tableRow.append(createPartTableRow(op1));
        tableRow.append(createPartTableRow(op2));

        if (symbol != null && op1 != null && op2 != null) {
            newName = TetradTableExecutor.T + TetradTableExecutor.numberT++;
            stack.push(newName);
        }

        tableRow.append(createPartTableRow(newName));

        Deque<String> tempStack = new ArrayDeque<>();
        StringBuilder tempStackElements = new StringBuilder();
        while (!stack.isEmpty()) {
            tempStack.push(stack.getLast());
            tempStackElements.append(stack.pollLast() + " ");
        }

        tableRow.append(createPartTableRow(tempStackElements.toString()));

        while (!tempStack.isEmpty()) {
            stack.push(tempStack.pollLast());
        }

        tableRow.append("\n");

        table.append(tableRow);
    }

    private String createPartTableRow(String character)
    {
        StringBuilder partTableRow = new StringBuilder();

        if (character != null && !character.equalsIgnoreCase("") && !character.equalsIgnoreCase(" ") && !character.isEmpty()) {
            partTableRow.append(String.format("|%-15.15s|", character));
        }
        else {
            partTableRow.append(String.format("|%-15.15s|", " "));
        }

        return partTableRow.toString();
    }
}
