package ee.ut.esi.group4.buildit.rental.application.dto;

import ee.ut.esi.group4.buildit.common.application.dto.BusinessPeriodDTO;
import ee.ut.esi.group4.buildit.rental.domain.model.POStatus;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;


@Data
public class PurchaseOrderDTO {
    Long _id;
    BusinessPeriodDTO rentalPeriod;
    PlantInventoryEntryDTO plant;

    POStatus status;
    String supplier;

    List<PlantInventoryItemDTO> reservations = new ArrayList<PlantInventoryItemDTO>();

}
