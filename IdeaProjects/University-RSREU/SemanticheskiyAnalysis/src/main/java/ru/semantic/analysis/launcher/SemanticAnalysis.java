package ru.semantic.analysis.launcher;

import ru.semantic.analysis.logics.PostfixFormExecutor;
import ru.semantic.analysis.logics.TetradTableExecutor;

import java.io.*;
import java.net.URISyntaxException;

public class SemanticAnalysis
{
    public static void main(String[] args) throws URISyntaxException
    {
//        if(args.length <= 0) {
//            try { throw new Exception("Enter result file."); }
//            catch (Exception e) {
//                e.printStackTrace();
//                System.exit(-1);
//            }
//        }

        String expression = "((a+b)*c-d)/e+f*k"; //	a b - c * d e f + / +

        System.out.println("Выражение:" + expression);

        PostfixFormExecutor postfixFormExecutor = new PostfixFormExecutor(expression);
        String postfixForm = postfixFormExecutor.getPostfixForm();
        System.out.println("Постфиксаная форма: " + postfixForm);

        TetradTableExecutor tetradTableExecutor = new TetradTableExecutor();
        String table = tetradTableExecutor.makeTetradTable(postfixForm);
        System.out.println(table);

        //String path = new File(SemanticAnalysis.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath()).getParent();

//        writeIntoFile(path + File.separator + args[0], table);
        writeIntoFile("src\\main\\resources\\result.txt", table);
    }

    private static void writeIntoFile(String path, String data)
    {
        try (FileOutputStream fos = new FileOutputStream(path); OutputStreamWriter osw = new OutputStreamWriter(fos, "UTF-8"); Writer writer = new BufferedWriter(osw)) {
            writer.write(data);
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}

//        String expression = "a + b * c";                   //   a b c * +
//        String expression = "a + b - c * d";               //	a b c d * - +
//        String expression = "a + b * c - d";               //   a b c * + d -
//        String expression = "(a * (b + c) + d) / 2";       //	a b c + * d + 2 /
//        String expression = "(a - b) * c + (d / (e + f))"; //	a b - c * d e f + / +

//        String expression = "99 * a - b"; //	error double number
//        String expression = "9 * a - bb"; //	error double letter
//        String expression = "99 * a - df"; //	error double number and double letter
//        String expression = "-9 * a - d"; //	error double number and double letter
//        String expression = "9 * a - (-d)"; // error double number and double letter