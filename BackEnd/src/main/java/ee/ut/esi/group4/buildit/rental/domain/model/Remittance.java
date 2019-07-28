package ee.ut.esi.group4.buildit.rental.domain.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor(force = true)
@AllArgsConstructor(staticName = "of")
public class Remittance {
    Long poId;
    BigDecimal amount;
    String description;

}
