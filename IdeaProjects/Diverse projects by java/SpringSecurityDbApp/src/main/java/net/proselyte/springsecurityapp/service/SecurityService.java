package net.proselyte.springsecurityapp.service;

/**
 * Service for security
 *
 * @author Nikita Elmanov
 * @version 1.0
 */

public interface SecurityService {

    String findLoggedInUsername();

    void autoLogin(String name, String password);

}
