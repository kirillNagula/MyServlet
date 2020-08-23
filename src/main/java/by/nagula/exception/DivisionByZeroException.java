package by.nagula.exception;

public class DivisionByZeroException extends Exception{

    @Override
    public String getMessage() {
        return "Нельзя делить на ноль";
    }
}
