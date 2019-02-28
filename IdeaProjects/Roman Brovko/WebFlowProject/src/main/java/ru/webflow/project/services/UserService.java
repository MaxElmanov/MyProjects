package ru.webflow.project.services;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.binding.message.DefaultMessageResolver;
import org.springframework.binding.message.MessageBuilder;
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

    public String createUser(User user, RequestContext context) throws Exception {

        if(hasSuchUserYet(user)){
            context.getMessageContext().addMessage(new MessageBuilder().code("user_exist").build());
            throw new Exception("Such user already exists");
        }

        if(areInvalidUserFields(user)){
            context.getMessageContext().addMessage(new MessageBuilder().code("incorrect_fields").build());
            return "invalidFields";
        }

        context.getMessageContext().addMessage(new MessageBuilder().code("enter").build());
        context.getMessageContext().addMessage(new MessageBuilder().code("user_created").build());
        userList.add(user);

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
