package by.bsuir.app.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.Objects;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Car extends BaseEntity {

    static final long serialVersionUID = 42L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "vin")
    private String VIN;

    @Column(name = "body_type")
    private String bodyType;

    @Column(name = "fuel_type")
    private String fuelType;
    private String gearbox;
    private BigDecimal price;

    @Column(name = "issue_date")
    private Date issueDate;
    private BigDecimal rate;

    @Column(name="photo_url")
    private String photoURL;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "model_id")
    private Model model;

}
