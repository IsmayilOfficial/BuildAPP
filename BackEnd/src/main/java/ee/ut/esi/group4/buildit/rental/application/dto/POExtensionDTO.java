package ee.ut.esi.group4.buildit.rental.application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
    @NoArgsConstructor(force = true)
    @AllArgsConstructor(staticName = "of")
    public class POExtensionDTO {
        Long entityId;
        LocalDate oldEndDate;
        LocalDate endDate;
        String client;

        //    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        //    po.createdby  = authentication.getName();
    }