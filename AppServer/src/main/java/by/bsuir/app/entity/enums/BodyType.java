package by.bsuir.app.entity.enums;

public enum BodyType {
    SEDAN("Седан"),
    COUPE("Купе"),
    HATCHBACK("Хетчбек"),
    MINIVAN("Минивен"),
    CONVERTIBLE("Кабриолет");

    private final String bodyType;

    BodyType(String bodyTypeRus){
        this.bodyType = bodyTypeRus;
    }

    public String getBodyType() {
        return bodyType;
    }
}
