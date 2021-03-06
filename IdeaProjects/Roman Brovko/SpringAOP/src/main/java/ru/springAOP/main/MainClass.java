package ru.springAOP.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.springAOP.objects.FileManager;
import ru.springAOP.objects.FileManager2;

public class MainClass {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:/context.xml");
        FileManager fileManager = (FileManager)context.getBean("fileManager");
        FileManager2 fileManager2 = (FileManager2) context.getBean("fileManager2");

        String folder = "C:\\Windows\\System32";

        fileManager.getExtensionCount(folder);
        fileManager2.getExtentionCount(folder);
    }
}
