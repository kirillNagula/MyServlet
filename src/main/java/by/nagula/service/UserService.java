package by.nagula.service;

import by.nagula.entity.User;

import java.util.List;

public interface UserService {

    void createUser(User user);
    User getUserById(long id);
    User getUserByLogin(String login);
    List<User> getAll();
    void removeUser(long id);
    void update(User user);
    void update(String name, long id);

}
