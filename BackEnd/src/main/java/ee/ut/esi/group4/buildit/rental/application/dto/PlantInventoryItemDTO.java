package ee.ut.esi.group4.buildit.rental.application.dto;

import lombok.Data;

@Data

public class PlantInventoryItemDTO {

    Long id;

    String serialNumber;

//    EquipmentCondition equipmentCondition;

    PlantInventoryEntryDTO plantInfo;

    PlantLocation location;

}