package ru.springDAO.objects;

public class MP3 {

    private int id;
    private String name;
    private Author author;

    public MP3() { }

    public MP3(String name, Author author) {
        this.name = name;
        this.author = author;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }
}
