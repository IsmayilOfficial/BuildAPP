package ee.ut.esi.group4.buildit.rental.domain.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;

@Entity
@Getter
@NoArgsConstructor(force = true, access = AccessLevel.PROTECTED)
@AllArgsConstructor(staticName = "of")
public class PlantInventoryEntry {
    @Id @GeneratedValue
    Long id;
    Long itemId;
    String name;
    String href;

    @JsonInclude()
    @Transient
    String _ownerToken;

    public static PlantInventoryEntry of(PlantInventoryEntry plantInventoryEntry) {
        PlantInventoryEntry ple = new PlantInventoryEntry();
        ple.name = plantInventoryEntry.name;
        ple.href = plantInventoryEntry.href;
        ple.itemId = plantInventoryEntry.itemId;
        ple._ownerToken = plantInventoryEntry._ownerToken;

        return ple;
    }
}