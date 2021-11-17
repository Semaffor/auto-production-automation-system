package by.bsuir.app.exception;

public class EntityNotExist extends Exception{
    private static final String msg = "Account doesn't exist, id: ";
    
    public EntityNotExist() {
        super();
    }

    public EntityNotExist(String message) {
        super(msg + message);
    }

    public EntityNotExist(String message, Throwable cause) {
        super(message, cause);
    }

    public EntityNotExist(Throwable cause) {
        super(cause);
    }
}
