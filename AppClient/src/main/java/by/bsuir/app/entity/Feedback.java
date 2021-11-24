package by.bsuir.app.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Feedback extends BaseEntity{

    static final long serialVersionUID = 42L;

    private Long id;
    private String question;
    private String answer;
    private Date questionDate;
    private Date answerDate;

    private Long sender_id;
}
