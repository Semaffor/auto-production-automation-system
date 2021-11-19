package by.bsuir.app.entity.enums;

public enum GearBox {
    AKPP("АКПП"),
    MKPP("МКПП");

    private String rusName;

    GearBox(String rusName) {
        this.rusName = rusName;
    }

    public String getRusName() {
        return rusName;
    }
}
