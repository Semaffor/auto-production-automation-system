package by.bsuir.app.entity.enums;

public enum FuelType {
    BATTERY("Электро"),
    GAS("Газ"),
    DIESEL("Дизель"),
    FUEL("Бензин"),
    HYBRID("Гибрид");

    private String rusName;

    FuelType(String rusName) {
        this.rusName = rusName;
    }

    public String getRusName() {
        return rusName;
    }
}
