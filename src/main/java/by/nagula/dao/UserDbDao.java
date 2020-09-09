package by.nagula.dao;

import by.nagula.entity.User;
import by.nagula.exception.NoResultException;
import by.nagula.exception.UserNotFoundException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDbDao implements UserDao{
    private Connection connection;
    private static final String CREATE_USER = "INSERT  INTO users(login, name, password) VALUES(?, ?, ?)";
    private static final String GET_USER_BY_ID = "SELECT * FROM users WHERE id = ?";
    private static final String GET_USER_BY_LOGIN = "SELECT * FROM users WHERE login = ?";
    private static final String GET_ALL = "SELECT * FROM users";
    private static final String UPDATE = "UPDATE users SET login = ?, name = ?, password = ? WHERE id = ?";
    private static final String DELETE = "DELETE FROM users WHERE id = ?";

    private static UserDao instance;


    private UserDbDao(Connection connection) {
        this.connection = connection;
    }

    public static UserDao getInstance(Connection connection){
        if (instance == null){
            return  new UserDbDao(connection);
        } else
            return instance;
    }

    @Override
    public void create(User user) {
        try {
            PreparedStatement st = connection.prepareStatement(CREATE_USER);
            st.setString(1, user.getLogin());
            st.setString(2, user.getName());
            st.setString(3, user.getPassword());
            st.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public User getById(long id) {
        try {
            PreparedStatement st = connection.prepareStatement(GET_USER_BY_ID);
            st.setLong(1, id);
            ResultSet resultSet = st.executeQuery();
            if (resultSet.next()) {
                String login = resultSet.getString("login");
                String password = resultSet.getString("password");
                String name = resultSet.getString("name");
                return new User(id, name,login,password);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        throw new UserNotFoundException();
    }

    @Override
    public User getByLogin(String login) {
        try {
            PreparedStatement st = connection.prepareStatement(GET_USER_BY_LOGIN);
            st.setString(1, login);
            ResultSet resultSet = st.executeQuery();
            if (resultSet.next()) {
                long id = resultSet.getLong("id");
                String password = resultSet.getString("password");
                String name = resultSet.getString("name");
                return new User(id,name,login,password);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        throw new UserNotFoundException();
    }

    @Override
    public List<User> getAll() {
        List<User> users = new ArrayList<>();
        try {
            Statement st = connection.createStatement();
            ResultSet resultSet = st.executeQuery(GET_ALL);
            while (resultSet.next()){
                long id = resultSet.getLong("id");
                String name = resultSet.getString("name");
                String login = resultSet.getString("login");
                String password = resultSet.getString("password");
                users.add(new User(id,name,login,password));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        if (users.isEmpty()){
            throw new NoResultException();
        }
        return users;
    }

    @Override
    public void update(User user) {
        try {
            PreparedStatement st = connection.prepareStatement(UPDATE);
            st.setString(1, user.getLogin());
            st.setString(2, user.getName());
            st.setString(3, user.getPassword());
            st.setLong(4, user.getId());
            st.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void removeById(long id) {
        try {
            PreparedStatement st = connection.prepareStatement(DELETE);
            st.setLong(1, id);
            st.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public boolean containsById(long id) {
        try {
            User user = getById(id);
            return true;
        } catch (Exception ex){
            return false;
        }
    }

    @Override
    public boolean containsByLogin(String login) {
        try {
            User user = getByLogin(login);
            return true;
        } catch (Exception ex){
            return false;
        }
    }

    public static void main(String[] args) {
        String URL = "jdbc:mysql://localhost/testservlet?serverTimezone=UTC";
        String USER = "root";
        String PASSWORD = "5454136RbHbKk";
        try(Connection connection = DriverManager.getConnection(URL,USER,PASSWORD)) {
            UserDao userDao = new UserDbDao(connection);
            System.out.println(userDao.containsById(8));

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
