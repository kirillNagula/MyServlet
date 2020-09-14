package by.nagula.exception;

public class NoOperationException extends RuntimeException {

    @Override
    public String getMessage() {
        return "нет такой операции в базе";
    }
}
