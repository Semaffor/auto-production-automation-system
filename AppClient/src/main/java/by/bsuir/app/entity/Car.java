package by.bsuir.app.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Car extends BaseEntity {

    static final long serialVersionUID = 42L;

    private Long id;
    private String VIN;
    private String bodyType;
    private String fuelType;
    private String gearbox;
    private BigDecimal price;
    private Date issueDate;
    private String description;
}
