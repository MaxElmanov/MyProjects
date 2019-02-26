package ru.springDAO.interfaces;

import ru.springDAO.objects.Author;
import ru.springDAO.objects.MP3;

import java.util.List;
import java.util.Map;

public interface MP3Dao {

    int insertMP3(MP3 mp3);

    int insertAuthor(Author author);

    int insertList(List<MP3> listMP3);

    void deleteMP3(int id);

    void deleteAuthor(int id);

    void deleteByName(String name);

    MP3 getMP3ById(int id);

    List<MP3> getMP3ListByName(String name);

    List<MP3> getMP3ListByAuthor(String author);

    int getCountMP3();

    Map<String, Integer> getStat();

    void updateMP3ByNameSong(String oldName, String newName, String newAuthor);

}
