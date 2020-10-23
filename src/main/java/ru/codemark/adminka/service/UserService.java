package ru.codemark.adminka.service;

import ru.codemark.adminka.model.User;
import ru.codemark.adminka.respons.Respons;
import java.util.List;

public interface UserService {
    List<User> findAllUsers();
    User findUserByLogin(String login);
    Respons deleteUser(User user);
    Respons addUser(User user);
    Respons update(User user);
}
