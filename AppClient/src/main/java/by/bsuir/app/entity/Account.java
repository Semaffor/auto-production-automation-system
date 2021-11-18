package by.bsuir.app.entity;

import by.bsuir.app.entity.enums.Role;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;
import java.util.List;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Account extends BaseEntity implements Serializable {

    static final long serialVersionUID = 42L;

    Long id;
    String login;
    String password;
    String email;
    Role role;
    Long personalInfoId;

    List<Feedback> feedbacks;

    public Account(String login) {
        this.login = login;
    }

    public Account(String login, String password, String email) {
        this.login = login;
        this.password = password;
        this.email = email;
    }

    public Account(String login, String password) {
        this.login = login;
        this.password = password;
    }
}
