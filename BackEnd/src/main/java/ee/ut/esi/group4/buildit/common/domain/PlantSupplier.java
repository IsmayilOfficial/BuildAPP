package ee.ut.esi.group4.buildit.common.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@NoArgsConstructor(force = true, access = AccessLevel.PROTECTED)
@AllArgsConstructor(staticName = "of")
public class PlantSupplier {
    @Id @GeneratedValue
    Long id;
    String name;
    String plantListHref;
    String poListHref;
    String poCreateHref;
    String poUpdateStatusHref;
    String poExtensionStatusHref;
    @Column(unique = true)
    String ourSystemAccount;
    @Column(unique = true)
    String clientSystemAccount;
    String password;
    String remittanceLink;

//    public static PlantSupplier of(PlantSupplier plantInventoryEntry) {
//        PlantSupplier ple = new PlantSupplier();
//        ple.name = plantInventoryEntry.name;
//        ple.plantListHref = plantInventoryEntry.plantListHref;
//        ple.poListHref = plantInventoryEntry.poListHref;
//        ple.poCreateHref = plantInventoryEntry.poCreateHref;
//        ple.poUpdateStatusHref = plantInventoryEntry.poUpdateStatusHref;
//        ple.ourSystemAccount = plantInventoryEntry.ourSystemAccount;
//        ple.password = plantInventoryEntry.password;
//
//        return ple;
//    }
}