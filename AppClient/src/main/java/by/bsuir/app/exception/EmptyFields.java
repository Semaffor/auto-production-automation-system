package by.bsuir.app.exception;

public class EmptyFields extends Exception{
    public EmptyFields() {
        super();
    }

    public EmptyFields(String message) {
        super(message);
    }

    public EmptyFields(String message, Throwable cause) {
        super(message, cause);
    }

    public EmptyFields(Throwable cause) {
        super(cause);
    }
}
