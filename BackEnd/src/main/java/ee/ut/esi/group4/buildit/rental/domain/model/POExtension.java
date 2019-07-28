package ee.ut.esi.group4.buildit.rental.domain.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor(force = true, access = AccessLevel.PROTECTED)
@AllArgsConstructor(staticName = "of")
public class POExtension {
    @Id
    @GeneratedValue
    Long id;
    Long entityId;
    LocalDate oldEndDate;
    LocalDate endDate;

    public static POExtension of(POExtension poExtension) {
        POExtension newpoe = new POExtension();
        newpoe.entityId = poExtension.entityId;
        newpoe.oldEndDate = poExtension.oldEndDate;
        newpoe.endDate = poExtension.endDate;


        return newpoe;
    }
}
