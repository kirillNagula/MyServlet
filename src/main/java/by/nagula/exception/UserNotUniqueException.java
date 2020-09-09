package by.nagula.exception;

public class UserNotUniqueException extends RuntimeException {
    @Override
    public String getMessage() {
        return "User already exist";
    }
}
