package by.bsuir.app.entity;

import by.bsuir.app.dao.BaseDao;
import by.bsuir.app.entity.enums.Role;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Proxy(lazy = false)
public class Account extends BaseEntity implements Serializable {

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
    @Column(name = "personal_data_id")
    Long personalInfoId;
    Role role;

    @OneToMany
    @JoinTable(name = "feedback", joinColumns = {@JoinColumn(name="id")},
            inverseJoinColumns = {@JoinColumn(name="sender_id")} )
    List<Feedback> feedbacks;
}
