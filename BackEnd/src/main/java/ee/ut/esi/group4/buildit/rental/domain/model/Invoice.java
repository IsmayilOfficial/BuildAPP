package ee.ut.esi.group4.buildit.rental.domain.model;

import ee.ut.esi.group4.buildit.rental.application.dto.Reminder;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


@Entity
@Getter
@NoArgsConstructor(force = true, access = AccessLevel.PROTECTED)
@AllArgsConstructor(staticName = "of")
public class Invoice  {
    @Id
    @GeneratedValue
    Long id;
//    List<LineItems> name;

    BigDecimal total;

    @OneToMany( cascade = CascadeType.PERSIST)
    List<Reminder> reminder;

    public static Invoice of(BigDecimal total) {
        Invoice ir = new Invoice();
        ir.total = total;
        ir.reminder = new ArrayList<>();

        return ir;
    }
}
