package ru.springDAO.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.springDAO.impls.SQLiteDAO;
import ru.springDAO.objects.Author;
import ru.springDAO.objects.MP3;

import java.util.ArrayList;
import java.util.List;

public class MainClass {
    public static void main(String[] args) {

        Author author = new Author("Billy Talent");

        MP3 mp3_1 = new MP3();
        mp3_1.setName("Horses & Chariots");
        mp3_1.setAuthor(author);

        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:/context.xml");
        SQLiteDAO sqLiteDAO = (SQLiteDAO) context.getBean("sqliteDAO_1");

        sqLiteDAO.insertMP3(mp3_1);

//        sqLiteDAO.deleteAuthor(13);
    }
}
