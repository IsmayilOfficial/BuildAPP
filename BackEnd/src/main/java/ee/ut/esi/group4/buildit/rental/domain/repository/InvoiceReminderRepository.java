package ee.ut.esi.group4.buildit.rental.domain.repository;

import ee.ut.esi.group4.buildit.rental.application.dto.Reminder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvoiceReminderRepository extends JpaRepository<Reminder, Long> {
}
