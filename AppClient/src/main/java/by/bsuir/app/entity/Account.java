package by.bsuir.app.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Account extends BaseEntity implements Serializable {

    static final long serialVersionUID = 42L;

    String login;
    String password;
    String name;
    String surname;

    Role role;

    public Account(String login, String password) {
        this.login = login;
        this.password = password;
    }
}
