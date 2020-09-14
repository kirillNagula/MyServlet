package by.nagula.dao;

import by.nagula.entity.Operation;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OperationDaoImpl implements OperationDao {
    private Connection connection;
    private static OperationDao instance = null;
    private static final String SET_OPERATION = "INSERT INTO operations (user_id,num_1, num_2, type, result, date) VALUES (?,?,?,?,?,?)";
    private static final String GET_OPERATION = "SELECT * FROM operations WHERE user_id = ?";
    private static final String DELETE_OPERATION = "DELETE FROM operations WHERE user_id = ?";

    private OperationDaoImpl(Connection connection){
        this.connection = connection;
    }

    public static OperationDao getInstance(Connection connection){
        if (instance == null)
            return new OperationDaoImpl(connection);
        else
            return instance;
    }

    @Override
    public List<Operation> getOperationByUserId(long userId) {
        List<Operation> operations = new ArrayList<>();
        try {
            PreparedStatement st = connection.prepareStatement(GET_OPERATION);
            st.setLong(1,userId);
            ResultSet resultSet = st.executeQuery();
            while (resultSet.next()){
                operations.add(new Operation(userId,
                    resultSet.getDate("date"),
                    resultSet.getString("type"),
                    resultSet.getDouble("num_1"),
                    resultSet.getDouble("num_2"),
                    resultSet.getDouble("result")));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return operations;
    }

    @Override
    public void setOperation(List<Operation> operations) {
        try {
            PreparedStatement st = connection.prepareStatement(SET_OPERATION);
            for (Operation operation: operations) {
                st.setLong(1, operation.getUserId());
                st.setDouble(2, operation.getNum1());
                st.setDouble(3, operation.getNum2());
                st.setString(4, operation.getOperationType());
                st.setDouble(5, operation.getResult());
                st.setDate(6, operation.getDate());
                st.addBatch();
            }
            st.executeBatch();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void deleteOperationById(long userId) {
        try {
            PreparedStatement st = connection.prepareStatement(DELETE_OPERATION);
            st.setLong(1, userId);
            st.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public boolean operationIsEmpty(long userId) {
        try {
            PreparedStatement st = connection.prepareStatement(GET_OPERATION);
            st.setLong(1, userId);
            ResultSet resultSet = st.executeQuery();
            if (resultSet.next()){
                return true;
            }
        } catch (SQLException throwables) {
            return false;
        }
        return false;
    }
}
