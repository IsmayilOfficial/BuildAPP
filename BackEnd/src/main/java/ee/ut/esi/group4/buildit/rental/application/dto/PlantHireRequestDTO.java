package ee.ut.esi.group4.buildit.rental.application.dto;

import ee.ut.esi.group4.buildit.common.domain.BusinessPeriod;
import ee.ut.esi.group4.buildit.rental.domain.model.PlantInventoryEntry;
import ee.ut.esi.group4.buildit.rental.domain.model.PurchaseOrder;
import lombok.Data;
import org.springframework.hateoas.ResourceSupport;

import java.math.BigDecimal;

@Data
public class PlantHireRequestDTO extends ResourceSupport {
    Long _id;
    String site;
    String supplier;
    BusinessPeriod rentalPeriod;
    String siteEngineer;
    BigDecimal cost;

    PlantInventoryEntry plant;
    PurchaseOrder po;

    String status;
    String comment;
    String statusBy;

}
