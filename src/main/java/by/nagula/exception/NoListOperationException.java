package by.nagula.exception;

public class NoListOperationException extends RuntimeException {

    @Override
    public String getMessage() {
        return "Список операций пуст";
    }
}
