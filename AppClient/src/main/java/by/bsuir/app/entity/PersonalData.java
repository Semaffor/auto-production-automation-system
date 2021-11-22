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
public class PersonalData extends BaseEntity{

    static final long serialVersionUID = 42L;

    private Long id;

    private String name;
    private String surname;
    private String thirdName;

    private int age;
    private String gender;

    private String phone;
    private String social;

    private Date emplStartDate;
    private Date emplEndDate;

    Position position;

    public Position getPosition() {
        if (position == null)
            position = new Position();
        return position;
    }
}
