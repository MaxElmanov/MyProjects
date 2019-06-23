package ru.vebinar.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.vebinar.dao.UserDaoImpl;
import ru.vebinar.entities.User;

import java.util.List;

@Component("service")
public class UserServiceImpl implements UserService {

    @Autowired
    public UserDaoImpl userDao;

    @Override
    public List<User> findAll() {
        return userDao.findAll();
    }
}
