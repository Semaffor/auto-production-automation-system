package by.bsuir.app.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Account extends BaseEntity {

    static final long serialVersionUID = 42L;

    Long id;
    String login;
    String password;
    String email;
    boolean isBlocked;

    String role;

    PersonalData data;

    //List<HistoryLog> logs;

//    List<Feedback> feedbacks;

    public Account(String login, String password, String email) {
        this.login = login;
        this.password = password;
        this.email = email;
    }

    public Account(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public PersonalData getData() {
        if (data == null)
            data = new PersonalData();
        return data;
    }

//    public List<Feedback> getFeedbacks() {
//        if (feedbacks == null)
//            feedbacks = new ArrayList<>();
//        return feedbacks;
//    }

//    public void addFeedback(Feedback feedback) {
//        if (feedbacks == null)
//            feedbacks = new ArrayList<>();
//        feedbacks.add(feedback);
//    }
}



