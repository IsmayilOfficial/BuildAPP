package ee.ut.esi.group4.buildit.rental.domain.repository;

import ee.ut.esi.group4.buildit.rental.domain.model.StatusMessagingData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatusMessageDataRepository extends JpaRepository<StatusMessagingData, Long> {
}
