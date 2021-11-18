package by.bsuir.app.entity.enums;

public enum Role {
    UNDEFINED(0),
    ADMIN(1),
    USER(2),
    GUEST(3);

    private final int id;

    Role(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
