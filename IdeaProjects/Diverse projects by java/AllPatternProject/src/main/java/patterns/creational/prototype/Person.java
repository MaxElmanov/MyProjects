package patterns.creational.prototype;

import java.util.List;

public class Person implements Copyable, Cloneable{

    private int id;
    private String name;
    private char gender;
    private List<Integer> carsNumbers;

    public Person(int id, String name, char gender, List<Integer> carsNumbers) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.carsNumbers = carsNumbers;
    }

    @Override
    public Object copy() {
        return new Person(this.id, this.name, this.gender, this.carsNumbers);
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        Person person = null;

        try
        {
            person = (Person) super.clone();
        }
        catch (CloneNotSupportedException e) { e.printStackTrace(); }

        return person;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", gender=" + gender +
                ", cars Numbers=" + carsNumbers +
                '}';
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

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    public List<Integer> getCarsNumbers() {
        return carsNumbers;
    }

    public void setCarsNumbers(List<Integer> carsNumbers) {
        this.carsNumbers = carsNumbers;
    }
}
