package by.bsuir.app.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.sql.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "personal_data", schema = "car-manufacturer")
public class PersonalData extends BaseEntity {

    static final long serialVersionUID = 42L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String surname;
    @Column(name = "thirdname")
    private String thirdName;

    private int age;
    private String gender;

    private String phone;
    private String social;

    @Column(name = "empl_start_date")
    private Date emplStartDate;

    @Column(name = "empl_end_date")
    private Date emplEndDate;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "position_id")
    Position position;
}
