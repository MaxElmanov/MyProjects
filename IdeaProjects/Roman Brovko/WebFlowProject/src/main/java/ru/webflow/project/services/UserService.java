package ru.webflow.project.services;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.webflow.execution.RequestContext;
import ru.webflow.project.objects.User;

import java.util.ArrayList;
import java.util.List;

@Component(value = "userService")
public class UserService {

    public static final Logger log = LogManager.getLogger(UserService.class);

    private List<User> userList = new ArrayList<>();

    public UserService() {
        userList.add(new User("user", "123"));
    }

    public boolean checkUser(User user){
        for (User existingUser : userList){
            if(existingUser.equals(user)){
                return true;
            }
        }

        return false;
    }

    public String createUser(User user, RequestContext context){

        log.info("suchUserExistsYet");
        if(hasSuchUserYet(user)){
            return "suchUserExistsYet";
        }

        log.info("invalidFields");
        if(areInvalidUserFields(user)){
            return "invalidFields";
        }

        userList.add(user);

        log.info("user [name= "+user.getName() + " - password= " + user.getPassword() + " was created");
        System.out.println(context.getFlowScope().asMap());

        return "validFields";
    }

    private boolean areInvalidUserFields(User user) {
        if(user.getName().isEmpty() || user.getPassword().isEmpty()) {
            return true;
        }

        return false;
    }

    private boolean hasSuchUserYet(User user) {
       for (User u : userList){
           if(u.equals(user)) {
               return true;
           }
       }

       return false;
    }
}
