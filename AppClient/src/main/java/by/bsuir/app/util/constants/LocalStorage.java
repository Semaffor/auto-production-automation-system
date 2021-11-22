package by.bsuir.app.util.constants;

import by.bsuir.app.entity.Account;
import by.bsuir.app.entity.Car;
import by.bsuir.app.entity.Feedback;

import java.util.List;

public class LocalStorage {
    private static Account account;
    private static List<Car> cars;
    private static List<Feedback> feedbacks;
    private static Long feedback_id;
    private static String question;

    public static Account getAccount() {
        return account;
    }

    public static void setAccount(Account a) {
        account = a;
    }

    public static Car getFirstCar() {
        if (!cars.isEmpty()) {
            Car car = cars.get(0);
            cars.remove(0);
            return car;
        }
        return null;
    }
    public static List<Car> getCars() {
        return cars;
    }

    public static void setCars(List<Car> cars) {
        LocalStorage.cars = cars;
    }

    public static Feedback getFirstFeedback() {
        if (!feedbacks.isEmpty()) {
            Feedback feedback = feedbacks.get(0);
            feedbacks.remove(0);
            return feedback;
        }
        return null;
    }

    public static String getQuestion() {
        return question;
    }

    public static List<Feedback> getFeedbacks() {
        return feedbacks;
    }

    public static void setFeedbacks(List<Feedback> feedbacks) {
        LocalStorage.feedbacks = feedbacks;
    }

    public static Long getFeedback_id() {
        return feedback_id;
    }

    public static void setFeedback_id(Long feedback_id) {
        LocalStorage.feedback_id = feedback_id;
    }
}
