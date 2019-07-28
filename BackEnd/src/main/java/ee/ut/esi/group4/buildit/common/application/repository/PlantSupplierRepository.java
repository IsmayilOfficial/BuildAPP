package ee.ut.esi.group4.buildit.common.application.repository;

import ee.ut.esi.group4.buildit.common.domain.PlantSupplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface PlantSupplierRepository extends JpaRepository<PlantSupplier, Long> {
    @Query("select p from PlantSupplier p where LOWER(p.ourSystemAccount) = ?1")
    Optional<PlantSupplier> findByOwnSystemAccount(String currentPrincipalName);

    @Query("select p from PlantSupplier p where LOWER(p.clientSystemAccount) = ?1")
    Optional<PlantSupplier> findByClientSystemAccount(String currentPrincipalName);
}
