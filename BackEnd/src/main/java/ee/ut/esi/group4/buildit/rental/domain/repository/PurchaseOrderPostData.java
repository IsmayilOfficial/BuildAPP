package ee.ut.esi.group4.buildit.rental.domain.repository;

import ee.ut.esi.group4.buildit.common.domain.BusinessPeriod;

public class PurchaseOrderPostData {

    Long plantItemId;
    BusinessPeriod period;

    public PurchaseOrderPostData(BusinessPeriod period, Long plantItemId){
        this.plantItemId = plantItemId;
        this.period = period;
    }

}