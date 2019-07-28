package ee.ut.esi.group4.buildit.rental.application.services;

import ee.ut.esi.group4.buildit.common.application.service.AutheticationService;
import ee.ut.esi.group4.buildit.common.domain.BusinessPeriod;
import ee.ut.esi.group4.buildit.common.domain.PlantSupplier;
import ee.ut.esi.group4.buildit.procurement.rest.ProcurementRestController;
import ee.ut.esi.group4.buildit.rental.application.dto.PlantHireRequestDTO;
import ee.ut.esi.group4.buildit.rental.domain.model.PlantHireRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Service
public class PlantHireRequestAssembler extends ResourceAssemblerSupport<PlantHireRequest, PlantHireRequestDTO> {

    @Autowired
    PlantHireRequestAssembler plantHireRequestAssembler;

    @Autowired
    AutheticationService autheticationService;

    public PlantHireRequestAssembler() {
        super(ProcurementRestController.class, PlantHireRequestDTO.class);
    }

    public PlantHireRequestDTO toResourceWithToken(PlantHireRequest plantHireRequest, PlantSupplier supplier) {
        plantHireRequest.setPlantSuppllierToken(autheticationService.createBasicAuthString(supplier.getClientSystemAccount(), supplier.getPassword()));
       return toResource( plantHireRequest);
    }

    @Override
    public PlantHireRequestDTO toResource(PlantHireRequest plantHireRequest) {
        if (plantHireRequest == null) return null;

        PlantHireRequestDTO dto = super.createResourceWithId(plantHireRequest.getId(), plantHireRequest);
        dto.set_id(plantHireRequest.getId());
        dto.setSite(plantHireRequest.getSite());
        dto.setSupplier(plantHireRequest.getSupplier());

        dto.setRentalPeriod(BusinessPeriod.of(plantHireRequest.getRentalPeriod().getStartDate(), plantHireRequest.getRentalPeriod().getEndDate()));
        dto.setSiteEngineer(plantHireRequest.getSiteEngineer());

        dto.setCost(plantHireRequest.getCost());

        dto.setStatus(plantHireRequest.getStatus().toString());
        dto.setComment(plantHireRequest.getComment());
        dto.setStatusBy(plantHireRequest.getStatusBy());

        dto.setPlant(plantHireRequest.getPlant());
        dto.setPo(plantHireRequest.getPo());

        dto.add(linkTo(methodOn(ProcurementRestController.class)
                .getPlantHireRequestById(dto.get_id().longValue())).withRel("details")
                            .withType(HttpMethod.GET.toString()));

        try {
            switch (plantHireRequest.getStatus()) {
                case CREATED:
                    dto.add(linkTo(methodOn(ProcurementRestController.class)
                        .approvePlantHireRequest(dto.get_id().longValue(), ""))
                            .withRel("approve")
                            .withType(HttpMethod.POST.toString()));
                    dto.add(linkTo(methodOn(ProcurementRestController.class)
                        .rejectPlantHireRequest(dto.get_id().longValue(), ""))
                            .withRel("reject")
                            .withType(HttpMethod.DELETE.toString()));
                    dto.add(linkTo(methodOn(ProcurementRestController.class)
                            .updatePlantHireRequest(dto)).withRel("updated")
                            .withType(HttpMethod.PATCH.toString()));

                    break;

                case PO_OPEN:
                case PO_REJECT_EXTENSION:
                    dto.add(linkTo(methodOn(ProcurementRestController.class)
                            .createPOExtension(null))
                            .withRel("ExtendPO")
                            .withType(HttpMethod.POST.toString()));

                    break;

                case INVOICED:
                    dto.add(linkTo(methodOn(ProcurementRestController.class)
                            .approveInvoiceforPO(dto.get_id()))
                            .withRel("Approve-Inv")
                            .withType(HttpMethod.POST.toString()));


//                case CREATED:
//
//                    break;

                default:
                    break;
            }
        } catch (Exception e) {}

        return dto;
    }


    public List<PlantHireRequestDTO> toResourcesWithToken(List<PlantHireRequest> phrs, List<PlantSupplier> suppliers) {

        List<PlantHireRequest> phrss =   phrs.stream().map(phr -> {
//            PlantSupplier  supplier = ArrayList<PlantSupplier>(suppliers)
             for(PlantSupplier supplier:suppliers){
                 if(phr.getSupplier() == supplier.getOurSystemAccount()){
                     toResourceWithToken( phr, supplier);
                   break;
                 }
             }
             return phr;
          }).collect(Collectors.toList());

        return phrss.stream().map(phr -> toResource(phr)).collect(Collectors.toList());
    }
}
