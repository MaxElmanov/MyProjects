package patterns.prototype;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;


public class main {

    public static final Logger log = LogManager.getLogger(main.class);

    public static void main(String[] args) throws CloneNotSupportedException {

        Person person = new Person(1, "Max", 'm', Arrays.asList(123,456,789));

        Person person2 = (Person) person.copy();

        log.info(person);
        log.info(person2);

        person.setName("Marina");
        person.setGender('ж');

        person2.setId(2);
        person2.setCarsNumbers(Arrays.asList(321,654,987));
        person2.setName("Nick");

        log.info(person);
        log.info(person2);

    }

}
