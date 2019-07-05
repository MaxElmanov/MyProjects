package main;

import logics.Ini4jExecutor;

import java.io.IOException;

public class Main
{
    public static void main(String[] args) throws IOException
    {
        //Getting
        Ini4jExecutor jExecutor = new Ini4jExecutor("properties.ini");
        int property = (int) jExecutor.getProperty("one", "age", int.class);
        String property2 = (String) jExecutor.getProperty("two", "secondName", String.class);
        System.out.println(property);
        System.out.println(property2);

        //Setting up
        jExecutor.putProperty("three", "name", "John");
        jExecutor.putProperty("three", "secondName", "White");
        jExecutor.putProperty("three", "age", "34");
        jExecutor.putProperty("three", "nationality", "English");
    }
}
