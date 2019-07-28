package ee.ut.esi.group4.buildit.rental.domain.repository;

import ee.ut.esi.group4.buildit.rental.domain.model.PlantHireRequest;
import ee.ut.esi.group4.buildit.rental.domain.model.PurchaseOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface PlantHireRepository extends JpaRepository<PlantHireRequest, Long> {
    @Query("select p from PlantHireRequest p where LOWER(p.supplier) = ?1 and p.po = ?2")
    Optional<PlantHireRequest> findByOwnerAndPoId(String supplier, PurchaseOrder po);
}
