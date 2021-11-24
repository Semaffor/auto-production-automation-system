package by.bsuir.app.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Proxy(lazy = false)
public class Feedback extends BaseEntity{

    static final long serialVersionUID = 42L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 180)
    private String question;

    @Column(length = 180)
    private String answer;

    @Column(name = "question_date")
    private Date questionDate;

    @Column(name = "answer_date")
    private Date answerDate;

//    @OneToOne
//    @MapsId
//    @JoinColumn(name = "id")
    @Column(name = "sender_id")
    private Long sender_id;
}
