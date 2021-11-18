package by.bsuir.app.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "personal_data", schema = "car-manufacturer")
public class PersonalData extends BaseEntity implements Serializable {

    static final long serialVersionUID = 42L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String surname;

    @Column(name = "thirdname")
    private String thirdName;
    private String gender;
    private String phone;
    private String address;
    private String social;

    private Date emplStartDate;
    private Date emplEndDate;
}
