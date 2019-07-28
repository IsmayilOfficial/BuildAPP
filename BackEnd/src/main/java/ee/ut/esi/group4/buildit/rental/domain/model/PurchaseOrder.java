package ee.ut.esi.group4.buildit.rental.domain.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
@Getter
@NoArgsConstructor(force = true, access = AccessLevel.PROTECTED)
@AllArgsConstructor(staticName = "of")
public class PurchaseOrder {
    @Id @GeneratedValue
    Long id;
    Long poId;
    String href;
    String supplierName;

    @OneToOne
    PlantHireRequest request;

    public static PurchaseOrder of(PurchaseOrder po) {
        PurchaseOrder newpo = new PurchaseOrder();
        newpo.href = po.getHref();
        newpo.poId = po.getPoId();
        newpo.supplierName = po.supplierName;




        return newpo;
    }

    public static PurchaseOrder of(String href, Long poId, String supplierName) {
        PurchaseOrder newpo = new PurchaseOrder();
        newpo.href = href;
        newpo.poId = poId;
        newpo.supplierName = supplierName;

        return newpo;
    }
}
