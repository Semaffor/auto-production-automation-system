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
public class PersonalData extends BaseEntity {

    static final long serialVersionUID = 42L;

    private Long id;
    private String name;
    private String surname;

    private String thirdName;
    private String gender;
    private String phone;
    private String address;
    private String social;

    private Date emplStartDate;
    private Date emplEndDate;
}
