package by.bsuir.app.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HistoryLog extends BaseEntity{

    static final long serialVersionUID = 42L;

    private Long id;
    private Timestamp entrance;
    private Date entranceDate;
    private Long account_id;

    public HistoryLog(Timestamp timestamp) {
        super();
        entrance = timestamp;
    }
}
