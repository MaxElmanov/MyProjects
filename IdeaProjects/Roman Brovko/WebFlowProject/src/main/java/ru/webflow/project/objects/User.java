package ru.webflow.project.objects;

import java.io.Serializable;
import java.util.Objects;

public class User implements Serializable {

    public static final long serialVersionUID = 1L;

    private String name;
    private String password;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public User() {
    }

    @Override
    public int hashCode() {
        return Objects.hash();
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;
        if (obj == null) return false;
        if(!(obj instanceof User)) return false;

        User user = (User) obj;
        if(user.name.equals(this.name) && user.password.equals(this.password)){
            return true;
        }
        else{
            return false;
        }
    }
}
