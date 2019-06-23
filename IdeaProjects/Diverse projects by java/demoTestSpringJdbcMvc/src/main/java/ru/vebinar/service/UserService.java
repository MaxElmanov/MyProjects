package ru.vebinar.service;

import ru.vebinar.entities.User;

import java.util.List;

public interface UserService {
    List<User> findAll();
}
