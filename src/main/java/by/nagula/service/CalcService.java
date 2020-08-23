package by.nagula.service;

import by.nagula.exception.DivisionByZeroException;
import by.nagula.exception.WrongTypeException;

public class CalcService {
    public double calc(double num1, double num2, String type) throws WrongTypeException, DivisionByZeroException {
        if (num2 == 0 && type.equals("div")){
            throw new DivisionByZeroException();
        }
        return operation(type,num1,num2);
    }

    private double operation(String type, double num1, double num2) throws WrongTypeException {
        switch (type){
            case "sum": return num1 + num2;
            case "sub": return num1 - num2;
            case "mult": return num1 * num2;
            case "div": return num1 / num2;
            default: throw new WrongTypeException();
        }
    }


}
