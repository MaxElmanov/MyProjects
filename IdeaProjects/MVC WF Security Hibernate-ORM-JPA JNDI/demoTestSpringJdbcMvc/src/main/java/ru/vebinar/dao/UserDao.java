package ru.vebinar.dao;

import ru.vebinar.entities.User;

import java.util.List;

public interface UserDao {
    List<User> findAll();
}
