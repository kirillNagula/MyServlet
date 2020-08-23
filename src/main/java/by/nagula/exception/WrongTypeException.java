package by.nagula.exception;

public class WrongTypeException extends Exception{
    @Override
    public String getMessage() {
        return "Неправильный тип операции";
    }
}
