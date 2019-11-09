package ru.maxelmanov.detagramsocket.objs;

import java.util.List;

public class Analizer
{
    public static int analize(List<String> requestMessages)
    {

        return 0;
    }

    public static void main(String[] args)
    {
        Color.RED.printColor();
        Color.GREEN.printColor();
        Color.BLUE.printColor();
    }

    enum Color
    {
        RED("Красный"), BLUE("Синий"), GREEN("Зелёный");
        private String color;
        public static final String ANSI_RESET = "\u001B[0m";
        public static final String ANSI_RED = "\u001B[31m";
        public static final String ANSI_GREEN = "\u001B[32m";
        public static final String ANSI_BLUE = "\u001B[34m";

        Color(String color)
        {
            this.color = color;
        }

        public void printColor()
        {
            switch (color) {
                case "Красный": {
                    System.out.println(ANSI_RED + color + ANSI_RESET);
                    break;
                }
                case "Синий": {
                    System.out.println(ANSI_BLUE + color + ANSI_RESET);
                    break;
                }
                case "Зелёный": {
                    System.out.println(ANSI_GREEN + color + ANSI_RESET);
                    break;
                }
                default: System.out.println("error");
            }
        }
    }
}
