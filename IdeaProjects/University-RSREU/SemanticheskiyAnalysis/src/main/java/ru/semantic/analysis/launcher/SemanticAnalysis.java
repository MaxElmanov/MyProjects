package ru.semantic.analysis.launcher;

import ru.semantic.analysis.logics.PostfixFormExecutor;
import ru.semantic.analysis.logics.TetradTableExecutor;

public class SemanticAnalysis
{
    public static void main(String[] args)
    {
//        String expression = "a + b * c";                   //   a b c * +
//        String expression = "a + b - c * d";               //	a b c d * - +
        String expression = "a + b * c - d";               //   a b c * + d -
//        String expression = "(a * (b + c) + d) / 2";       //	a b c + * d + 2 /
//        String expression = "(a - b) * c + (d / (e + f))"; //	a b - c * d e f + / +

        PostfixFormExecutor postfixFormExecutor = new PostfixFormExecutor(expression);

        String postfixForm = postfixFormExecutor.getPostfixForm();

        System.out.println("Постфиксаная форма: " + postfixForm);

        TetradTableExecutor tetradTableExecutor = new TetradTableExecutor();

        String table = tetradTableExecutor.makeTetradTable(postfixForm);

        System.out.println(table);

    }
}
