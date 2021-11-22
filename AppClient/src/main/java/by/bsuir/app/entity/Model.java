package by.bsuir.app.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Model extends BaseEntity{

    static final long serialVersionUID = 42L;

    private Long id;
    private String name;
    private String description;
    private int quantity;

}
