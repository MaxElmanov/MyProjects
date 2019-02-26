package mainClass;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import users.UserDeveloper;

import java.io.File;
import java.io.IOException;

public class Run {

    private static final Logger logger = LogManager.getLogger(Run.class);

    public static void main(String[] args) throws IOException {

        UserDeveloper user = new UserDeveloper("Maximmus", "Elmanov_nr@mail.ru", 15, true);

        ObjectMapper mapper = new ObjectMapper();
//
        mapper.writerWithDefaultPrettyPrinter().writeValue(new File("newJson.json"), user);

        UserDeveloper ud = mapper.readValue(new File("newJson.json"), UserDeveloper.class);

        logger.info(ud);

    }

}
