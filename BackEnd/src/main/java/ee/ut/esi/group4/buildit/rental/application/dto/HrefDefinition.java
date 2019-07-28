package ee.ut.esi.group4.buildit.rental.application.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
public class HrefDefinition {
   @JsonInclude(JsonInclude.Include.ALWAYS)
   String href;
   @JsonInclude(JsonInclude.Include.ALWAYS)
   String type;
}
