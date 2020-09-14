package by.nagula.service;

import by.nagula.dao.OperationDao;
import by.nagula.dao.OperationDaoImpl;
import by.nagula.entity.Operation;
import by.nagula.exception.NoListOperationException;
import by.nagula.exception.NoOperationException;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class OperationServiceImpl implements OperationService {
    private OperationDao operationDao;

    private OperationServiceImpl(OperationDao operationDao) {
        this.operationDao = operationDao;
    }

    private static OperationService instance = null;

    public static OperationService getInstance(Connection connection){
        if (instance == null){
            return new OperationServiceImpl(OperationDaoImpl.getInstance(connection));
        } else
            return instance;
    }

    @Override
    public void setOperations(List<Operation> operations) {
        if(operations.isEmpty()){
            throw new NoListOperationException();
        } else
            operationDao.setOperation(operations);
    }

    @Override
    public List<Operation> getOperations(long userId) {
        if (operationDao.operationIsEmpty(userId)){
            return operationDao.getOperationByUserId(userId);
        } else
            return new ArrayList<>();
    }

    @Override
    public void deleteOperation(long userId) {
        if (operationDao.operationIsEmpty(userId)){
            operationDao.deleteOperationById(userId);
        } else
            throw new NoOperationException();
    }

    @Override
    public boolean isOperation(long userId) {
        return operationDao.operationIsEmpty(userId);
    }

    public static void main(String[] args) {
        String URL = "jdbc:mysql://localhost/testservlet?serverTimezone=UTC";
        String USER = "root";
        String PASSWORD = "5454136RbHbKk";
        try(Connection connection = DriverManager.getConnection(URL,USER,PASSWORD)) {
            OperationService operationService = OperationServiceImpl.getInstance(connection);
            List<Operation> operations = operationService.getOperations(1);
            System.out.println(operations);
            System.out.println(operationService.isOperation(1));
            operationService.deleteOperation(1);
            System.out.println(operationService.isOperation(1));

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
