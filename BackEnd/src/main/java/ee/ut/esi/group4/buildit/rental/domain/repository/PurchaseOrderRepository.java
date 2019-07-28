package ee.ut.esi.group4.buildit.rental.domain.repository;

import ee.ut.esi.group4.buildit.rental.domain.model.PurchaseOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface PurchaseOrderRepository extends JpaRepository<PurchaseOrder, Long> {

    @Query("select p from PurchaseOrder p where LOWER(p.supplierName) = ?1 and p.poId = ?2")
    Optional<PurchaseOrder> findByOwnerAndPoId(String supplier, Long poId);
}
