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
public class Car extends BaseEntity implements Serializable {

    static final long serialVersionUID = 42L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "vin")
    private String VIN;

    private String bodyType;
    private String fuelType;
    private String gearbox;
    private BigDecimal price;
    private Date issueDate;
    private String description;
}
