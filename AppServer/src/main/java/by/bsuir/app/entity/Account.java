package by.bsuir.app.entity;

import by.bsuir.app.entity.enums.BlockStatus;
import by.bsuir.app.entity.enums.Role;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Proxy(lazy = false)
public class Account extends BaseEntity {

    static final long serialVersionUID = 42L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(nullable = false, length = 45)
    String login;
    @Column(nullable = false, length = 45)
    String password;
    @Column(nullable = false, length = 45)
    String email;

    @Column(name = "is_blocked")
    boolean isBlocked;

    String role;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "personal_data_id")
    @NotNull
    PersonalData data;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "account_id")
    List<HistoryLog> logs;

    @Transient
    @OneToMany(cascade = CascadeType.REFRESH)
    @LazyCollection(LazyCollectionOption.FALSE)
    @JoinColumn(name = "sender_id")
    List<Feedback> feedbacks;

    public void addLog(HistoryLog log) {
        if (logs == null)
            logs = new ArrayList<>();

        logs.add(log);
    }

    public void addFeedback(Feedback feedback) {
        if (feedbacks == null)
            feedbacks = new ArrayList<>();

        feedbacks.add(feedback);
    }
}
