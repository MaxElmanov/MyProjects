package ru.maxelmanov.resourcebundle.launch;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.*;

public class Launcher
{
    public static void main(String[] args) throws IOException
    {
        Locale locale = null;
        ResourceBundle rb = null;
        int number;
        ClassLoader loader = new Launcher().getClass().getClassLoader();

        System.out.println("Choose a language you prefer:\n1) Russian\n2) English\n3) French");
        do
        {
            Scanner sc = new Scanner(System.in);
            number = sc.nextInt();
            switch (number)
            {
                case 1:
                    locale = new Locale("RU", "RU");
                    rb = ResourceBundle.getBundle("text", locale);
                    break;
                case 2:
                    locale = new Locale("EN", "US");
                    rb = ResourceBundle.getBundle("text", locale);
                    break;
                default:
                    locale = new Locale("FR", "FR");
                    rb = ResourceBundle.getBundle("text", locale);
            }

            System.out.println("web.submit.button = " + getUtf8Value("web.submit.button", rb));
            System.out.println("web.logo.name = " + getUtf8Value("web.logo.name", rb));

        } while (number != 5);
    }

    private static String getUtf8Value(String key, ResourceBundle rb)
    {
        String result = null;

        try
        {
            result = new String(rb.getString(key).getBytes("CP1251"), "UTF-8");
        }
        catch (UnsupportedEncodingException e)
        {
            e.printStackTrace();
        }

        return result;
    }

    private static String toResourceFileName(Locale locale)
    {
        Formatter formatter = new Formatter();
        formatter.format("text_%s_%S.properties", locale.getLanguage(), locale.getCountry());
        return formatter.toString();
    }
}
