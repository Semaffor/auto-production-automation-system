package by.bsuir.app.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Position extends BaseEntity {

    static final long serialVersionUID = 42L;

    private Long id;
    private String name;
    private BigDecimal salary;
    private String description;
}
