package users;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UserDeveloper {

    public static final Logger logger = LogManager.getLogger(UserDeveloper.class);

    private String name;
    private String email;
    private int age;
    private boolean isDeveloper;

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public int getAge() {
        return age;
    }

    public boolean isDeveloper() {
        return isDeveloper;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setDeveloper(boolean developer) {
        isDeveloper = developer;
    }

    public UserDeveloper(String name, String email, int age, boolean isDeveloper) {
        this.name = name;
        this.email = email;
        this.age = age;
        this.isDeveloper = isDeveloper;
        logger.info("Fields of Class UserDeveloper was created by constructor!");
    }

    public UserDeveloper() {
    }

    @Override
    public String toString() {
        return "UserDeveloper{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", age=" + age +
                ", isDeveloper=" + isDeveloper +
                '}';
    }
}
