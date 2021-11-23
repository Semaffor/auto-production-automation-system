package by.bsuir.app.entity.enums;

public enum PositionType {
    COLLECTOR("Сборщик"),
    PICKER("Комплектовщик"),
    DESIGNER("Проектировщик"),
    ARCHITECT("Архитектор"),
    CLEANER("Уборщик");

    private String positionRU;

    PositionType(String positionRU) {
        this.positionRU = positionRU;
    }

    public String getPositionRU() {
        return positionRU;
    }
}
