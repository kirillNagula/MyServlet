package by.nagula.service;

import by.nagula.dao.UserDao;
import by.nagula.dao.UserDbDao;
import by.nagula.entity.User;
import by.nagula.exception.UserNotFoundException;
import by.nagula.exception.UserNotUniqueException;

import java.sql.Connection;
import java.util.List;

public class UserServiceImpl implements UserService {
    private UserDao userDao;

    private UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    private static UserService instance = null;

    public static  UserService getInstance(Connection connection) {
        if (instance == null){
            return new UserServiceImpl(UserDbDao.getInstance(connection));
        }else
            return instance;
    }

    @Override
    public void createUser(User user) {
        if (userDao.containsByLogin(user.getLogin())){
           throw new UserNotUniqueException();
        } else {
            userDao.create(user);
        }
    }

    @Override
    public User getUserById(long id) {
        if (userDao.containsById(id)){
            return userDao.getById(id);
        }
        throw new UserNotFoundException();
    }

    @Override
    public User getUserByLogin(String login) {
        if (userDao.containsByLogin(login)){
            return userDao.getByLogin(login);
        }
        throw new UserNotFoundException();
    }

    @Override
    public List<User> getAll() {
        return userDao.getAll();
    }

    @Override
    public void removeUser(long id) {
        if (userDao.containsById(id)){
            userDao.removeById(id);
        } else
            throw new UserNotFoundException();
    }

    @Override
    public void update(User user) {
        if (userDao.containsById(user.getId())){
            userDao.update(user);
        }
    }

    @Override
    public void update(String name, long id) {
    }
}
