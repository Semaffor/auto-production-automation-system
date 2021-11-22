package by.bsuir.app.entity.enums;

public enum Gender {
    MALE("Мужской"),
    FEMALE("Женский"),
    UNDEFINED("Неизвестно");

    private String gender;

    Gender(String gender) {
        this.gender = gender;
    }

    public String getGender() {
        return gender;
    }
}
