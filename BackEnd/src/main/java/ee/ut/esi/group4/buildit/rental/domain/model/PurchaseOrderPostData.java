package ee.ut.esi.group4.buildit.rental.domain.model;

import ee.ut.esi.group4.buildit.common.domain.BusinessPeriod;
import lombok.Data;

@Data
public class PurchaseOrderPostData {

    Long plantItemId;
    BusinessPeriod period;

    public PurchaseOrderPostData(BusinessPeriod period, Long plantItemId){
        this.plantItemId = plantItemId;
        this.period = period;
    }

}
