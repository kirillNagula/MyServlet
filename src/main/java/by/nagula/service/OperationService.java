package by.nagula.service;

import by.nagula.entity.Operation;

import java.util.List;

public interface OperationService {
    void setOperations(List<Operation> operations);
    List<Operation> getOperations(long userId);
    void deleteOperation(long userId);
    boolean isOperation(long userId);
}
