package by.nagula.entity;

import java.sql.Date;

public class Operation {
    private long id;
    private long userId;
    private java.sql.Date date;
    private String operationType;
    private double num1;
    private double num2;
    private double result;

    public Operation(){}

    public Operation(long id, long userId, java.sql.Date date, String operationType, double num1, double num2, double result) {
        this.id = id;
        this.userId = userId;
        this.date = date;
        this.operationType = operationType;
        this.num1 = num1;
        this.num2 = num2;
        this.result = result;
    }

    public Operation(long userId, java.sql.Date date, String operationType, double num1, double num2, double result) {
        this.userId = userId;
        this.date = date;
        this.operationType = operationType;
        this.num1 = num1;
        this.num2 = num2;
        this.result = result;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getOperationType() {
        return operationType;
    }

    public void setOperationType(String operationType) {
        this.operationType = operationType;
    }

    public double getNum1() {
        return num1;
    }

    public void setNum1(double num1) {
        this.num1 = num1;
    }

    public double getNum2() {
        return num2;
    }

    public void setNum2(double num2) {
        this.num2 = num2;
    }

    public double getResult() {
        return result;
    }

    public void setResult(double result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "Operation{" +
                "user_id=" + userId +
                ", date=" + date +
                ", operationType='" + operationType + '\'' +
                ", num1=" + num1 +
                ", num2=" + num2 +
                ", result=" + result +
                '}';
    }
}
