package by.bsuir.app.entity;

public enum Status {
    OK(200),
    REQUEST_ERROR(404),
    SERVER_ERROR(502),
    CLOSE_CONNECTION(100),
    ACCOUNT_NOT_EXISTS(400);

    private final int code;

    Status(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}