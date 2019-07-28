package ee.ut.esi.group4.buildit.rental.application.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
public class Link {
   @JsonInclude(JsonInclude.Include.ALWAYS)
   HrefDefinition fetch;
   @JsonInclude(JsonInclude.Include.ALWAYS)
   HrefDefinition self;
}
