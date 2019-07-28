package ee.ut.esi.group4.buildit.rental.domain.model;

import ee.ut.esi.group4.buildit.common.domain.BusinessPeriod;
import ee.ut.esi.group4.buildit.rental.application.dto.PlantHireRequestDTO;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(force = true, access = AccessLevel.PROTECTED)
public class PlantHireRequest {

    @Id @GeneratedValue
    Long id;


    String site;
    String supplier;

    @Embedded
    BusinessPeriod rentalPeriod;
    String siteEngineer;

    @Column(precision=8,scale=2)
    BigDecimal cost;

    @OneToOne(cascade = CascadeType.PERSIST)
    PlantInventoryEntry plant;

    @OneToOne(optional = true, cascade = CascadeType.PERSIST)
    PurchaseOrder po;

    @Enumerated(EnumType.STRING)
    PHRStatus status;
    String comment;
    String statusBy;

    @OneToOne(optional = true, cascade = CascadeType.PERSIST)
    POExtension extension;

    @OneToMany( cascade = CascadeType.PERSIST)
    List<StatusMessagingData> messages;

    @OneToOne(optional = true, cascade = CascadeType.PERSIST)
    Invoice invoice;


    public static PlantHireRequest of(PlantHireRequestDTO plantHireRequestDTO) {
        PlantHireRequest phr = new PlantHireRequest();
        phr.site = plantHireRequestDTO.getSite();
        phr.supplier = plantHireRequestDTO.getSupplier();
        phr.rentalPeriod = plantHireRequestDTO.getRentalPeriod();
        phr.siteEngineer = plantHireRequestDTO.getSiteEngineer();

        phr.cost = plantHireRequestDTO.getCost();

        phr.plant = PlantInventoryEntry.of(plantHireRequestDTO.getPlant());
        phr.messages = new ArrayList<>();

        phr.comment = plantHireRequestDTO.getStatus();
        phr.statusBy = plantHireRequestDTO.getStatusBy();

        return phr;
    }


    public void setCurrentState(PHRStatus status) {
        this.status = status;
    }

    public void setComment(String comment) { this.comment = this.comment; }


//    public void addStatusMessage(StatusMessagingData message) {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        this.messages.add(StatusMessagingData.of(message.getEntityId(), message.getState(), authentication.getName(), message.getComment()));
//    }

    public void setPurchaseOrder(String po, Long poid, String supplier) {
        if(po != null)
        this.po = PurchaseOrder.of(po, poid, supplier);
    }

    public void setInvoice (Invoice invoice){
        this.invoice = invoice;
    }


//    public void setInvoice(String po, Long poId, String supplier) {
//        if(po != null)
//            this.invoice = PurchaseOrder.of(po, poId, supplier);
//    }


//    public void requestExtension(POExtension extension) {
//        this.extension = POExtension.of(extension);
//        this.status = PHRStatus.PO_PENDING_EXTENSION;
//    }

    public void setRentalPeriod(BusinessPeriod period) {

        this.rentalPeriod = period;
    }



    public void setPlantSuppllierToken(String basicAuthString) {
        this.plant._ownerToken = basicAuthString;
    }

    public void setPOExtension(POExtension ext) {
        this.extension = ext;
    }
}
