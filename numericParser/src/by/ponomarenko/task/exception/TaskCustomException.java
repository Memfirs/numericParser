package by.ponomarenko.task.exception;

public class TaskCustomException extends Exception {

    public TaskCustomException() {
    }

    public TaskCustomException(String message) {
        super(message);
    }

    public TaskCustomException(Throwable cause) {
        super(cause);
    }

    public TaskCustomException(String message, Throwable cause) {
        super(message, cause);
    }
}