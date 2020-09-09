package by.nagula.exception;

public class NoResultException extends RuntimeException {

    @Override
    public String getMessage() {
        return "No result";
    }
}
