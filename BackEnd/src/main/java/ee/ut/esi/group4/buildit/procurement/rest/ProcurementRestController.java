package ee.ut.esi.group4.buildit.procurement.rest;

import ee.ut.esi.group4.buildit.common.application.service.AutheticationService;
import ee.ut.esi.group4.buildit.rental.application.dto.*;
import ee.ut.esi.group4.buildit.rental.application.services.RentalService;
import ee.ut.esi.group4.buildit.rental.domain.model.StatusMessagingData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/procurements")
public class ProcurementRestController {

    @Autowired
    RentalService rentalService;

    @Autowired
    AutheticationService authenticationService;

    @GetMapping("/plants")
    @Secured({"ROLE_SITE-ENGINEER", "ROLE_WORKS-ENGINEER"})
    public List<PlantInventoryEntryDTO> findAvailablePlants(
            @RequestParam(name = "name", required = false) Optional<String> plantName,
            @RequestParam(name = "startDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Optional<LocalDate> startDate,
            @RequestParam(name = "endDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Optional<LocalDate> endDate
        ){
        return rentalService.findAvailablePlants(plantName.get(), startDate.get(), endDate.get());

    }


    @PostMapping("/hire")
    @Secured({"ROLE_SITE-ENGINEER"})
    public ResponseEntity<PlantHireRequestDTO> createPlantHireRequest(@RequestBody PlantHireRequestDTO plantHireRequestDTO) throws Exception {

        PlantHireRequestDTO confirmedPODTO = rentalService.createPlantHireRequest(plantHireRequestDTO);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(new URI(confirmedPODTO.getId().getHref()));

        return new ResponseEntity<PlantHireRequestDTO>(confirmedPODTO, headers, HttpStatus.CREATED);

    }

    @GetMapping("/{id}")
    @Secured({"ROLE_SITE-ENGINEER", "ROLE_WORKS-ENGINEER"})
    public PlantHireRequestDTO getPlantHireRequestById(@PathVariable long id) {

        return rentalService.plantHireRequest(id);

    }

    @DeleteMapping("hire/{id}/reject")
    @Secured({"ROLE_SITE-ENGINEER", "ROLE_WORKS-ENGINEER"})
    public ResponseEntity<PlantHireRequestDTO> rejectPlantHireRequest(@PathVariable Long id, @RequestBody(required=false) String comment) throws Exception {

        PlantHireRequestDTO confirmedPODTO = rentalService.rejectPlantHireRequest(id, comment);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(new URI(confirmedPODTO.getId().getHref()));

        return new ResponseEntity<PlantHireRequestDTO>(confirmedPODTO, headers, HttpStatus.CREATED);

    }

    @PostMapping("hire/{id}/approve")
    @Secured({"ROLE_SITE-ENGINEER", "ROLE_WORKS-ENGINEER"})
    public ResponseEntity<PlantHireRequestDTO> approvePlantHireRequest(@PathVariable Long id, @RequestBody(required=false) String comment) throws Exception {

            PlantHireRequestDTO confirmedPODTO = rentalService.approvePlantHireRequest(id, comment);

            HttpHeaders headers = new HttpHeaders();
            headers.setLocation(new URI(confirmedPODTO.getId().getHref()));

            return new ResponseEntity<PlantHireRequestDTO>(confirmedPODTO, headers, HttpStatus.CREATED);

        }

    @PatchMapping("/hire/update")
    @Secured({"ROLE_SITE-ENGINEER", "ROLE_WORKS-ENGINEER"})
    public ResponseEntity<PlantHireRequestDTO> updatePlantHireRequest(@RequestBody PlantHireRequestDTO plantHireRequestDTO) throws Exception {

        PlantHireRequestDTO confirmedPODTO =  rentalService.updatePlantHireRequest(plantHireRequestDTO);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(new URI(confirmedPODTO.getId().getHref()));

        return new ResponseEntity<PlantHireRequestDTO>(confirmedPODTO, headers, HttpStatus.CREATED);
    }

    @PostMapping("/hire/extension")
    @Secured({"ROLE_SITE-ENGINEER", "ROLE_WORKS-ENGINEER"})
    public ResponseEntity<PlantHireRequestDTO> createPOExtension(@RequestBody POExtensionDTO extensionDTO) throws Exception{
        PlantHireRequestDTO confirmedPODTO =  rentalService.createPOExtension(extensionDTO);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(new URI(confirmedPODTO.getId().getHref()));

        return new ResponseEntity<PlantHireRequestDTO>(confirmedPODTO, headers, HttpStatus.CREATED);

    }

    @GetMapping("/hires")
    @Secured({"ROLE_SITE-ENGINEER", "ROLE_WORKS-ENGINEER"})
    public List<PlantHireRequestDTO> plantHireRequests() throws Exception {

        return rentalService.plantHireRequests();
    }

    @GetMapping("/pos")
    @Secured({"ROLE_SITE-ENGINEER", "ROLE_WORKS-ENGINEER"})
    public List<PurchaseOrderDTO> loadPurchaseOrder() throws Exception {

        return rentalService.loadPurchaseOrder();
    }

    @PostMapping("/hire/statusupdate")
    @Secured({"ROLE_SUPPLIER", "ROLE_SITE-ENGINEER", "ROLE_WORKS-ENGINEER"})
    public HttpStatus updatePlantHireRequestState(@RequestBody StatusMessagingData data) throws Exception {

        rentalService.updatePlantHireRequestState(data);
        return  HttpStatus.ACCEPTED;
    }

    @PostMapping("/hire/invoice/submit")
    public HttpStatus submitInvoiceforReturnedPO(@RequestBody InvoiceDTO inv){

        rentalService.submitInvoiceforReturnedPO(inv);
        return  HttpStatus.ACCEPTED;
    }

    @PostMapping("/hire/invoice/{id}/approve")
    public PlantHireRequestDTO approveInvoiceforPO(@PathVariable Long id){
        return rentalService.approveInvoiceforPO(id);
    }

    @PostMapping("/orders/invoice/reminder")
    public HttpStatus submitInvoiceReminder(@PathVariable InvoiceReminderDTO reminder) {
        rentalService.submitInvoiceforReminderPO(reminder);

        return  HttpStatus.CREATED;
    }

//    @PostMapping("/orders/invoice/{id}/reminder")
//    public PurchaseOrderDTO plantDispatched(@PathVariable Long id) {
//        return rentalService.approveInvoiceforPO(id);
//    }

}
