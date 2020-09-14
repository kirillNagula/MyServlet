package by.nagula.dao;

import by.nagula.entity.User;

import java.util.List;

public interface UserDao {
    void create(User user);
    User getById(long id);
    User getByLogin(String login);
    List<User> getAll();
    void update(User user);
    void removeById(long id);
    boolean containsById(long id);
    boolean containsByLogin(String login);
}
