package ee.ut.esi.group4.buildit.rental.application.dto;

import lombok.Data;
import org.springframework.hateoas.ResourceSupport;

import java.math.BigDecimal;

@Data
public class PlantInventoryEntryDTO extends ResourceSupport {
    Long _id;
    String name;
    String description;
    BigDecimal price;
    String supplier;
//    String _self;
//    String _ownerToken;
//    @JsonInclude(JsonInclude.Include.ALWAYS)
//    Link links;

}
