package by.nagula.dao;

import by.nagula.entity.Operation;

import java.util.List;

public interface OperationDao {


    List<Operation> getOperationByUserId(long userId);
    void setOperation(List<Operation> operation);
    void deleteOperationById(long userId);
    boolean operationIsEmpty(long userId);
}
