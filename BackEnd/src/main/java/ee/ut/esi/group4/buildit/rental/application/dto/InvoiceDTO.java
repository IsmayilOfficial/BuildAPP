package ee.ut.esi.group4.buildit.rental.application.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class InvoiceDTO  {
//    Long _id;
//    List<LineItems> name;
    Long poId;
    BigDecimal total;

    
}