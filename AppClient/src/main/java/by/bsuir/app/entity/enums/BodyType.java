package by.bsuir.app.entity.enums;

public enum BodyType {
    SEDAN("Седан"),
    COUPE("Купе"),
    HATCHBACK("Хэтчбек"),
    MINIVAN("Минивен"),
    CONVERTIBLE("Кабриолет"),
    NONE("None");

    private final String bodyType;

    BodyType(String bodyTypeRus){
        this.bodyType = bodyTypeRus;
    }

    public String getBodyType() {
        return bodyType;
    }
}
