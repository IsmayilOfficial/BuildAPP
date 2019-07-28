package ee.ut.esi.group4.buildit.rental.application.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class PlantInventoryEntryDTOes {
    List<PlantInventoryEntryDTO> plantInventoryEntryDToes;

    public PlantInventoryEntryDTOes() {

        plantInventoryEntryDToes = new ArrayList<>();
    }

    public PlantInventoryEntryDTOes(List<PlantInventoryEntryDTO> list) {

        plantInventoryEntryDToes = list;
    }

}
