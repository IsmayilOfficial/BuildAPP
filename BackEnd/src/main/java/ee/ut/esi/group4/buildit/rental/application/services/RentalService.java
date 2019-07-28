package ee.ut.esi.group4.buildit.rental.application.services;

import ee.ut.esi.group4.buildit.common.application.repository.PlantSupplierRepository;
import ee.ut.esi.group4.buildit.common.application.service.AutheticationService;
import ee.ut.esi.group4.buildit.common.domain.BusinessPeriod;
import ee.ut.esi.group4.buildit.common.domain.PlantSupplier;
import ee.ut.esi.group4.buildit.rental.application.dto.*;
import ee.ut.esi.group4.buildit.rental.domain.model.*;
import ee.ut.esi.group4.buildit.rental.domain.model.PurchaseOrderPostData;
import ee.ut.esi.group4.buildit.rental.domain.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class RentalService {
    @Autowired
    RestTemplate restTemplate;

    @Autowired
    PlantHireRepository plantHireRepository;

    @Autowired
    PurchaseOrderRepository purchaseOrderRepository;

    @Autowired
    PlantHireRequestAssembler plantHireRequestAssembler;

    @Autowired
    PlantSupplierRepository plantSupplierRepository;

    @Autowired
    AutheticationService autheticationService;

    @Autowired
    StatusMessageDataRepository statusMessageDataRepository;

    @Autowired
    POExtensionRepository poExtensionRepository;

    @Autowired
    InvoiceRepository invoiceRepository;

    @Autowired
    InvoiceReminderRepository invoiceReminderRepository;


    public List<PlantInventoryEntryDTO> findAvailablePlants(String plantName, LocalDate startDate, LocalDate endDate) {

        List<PlantSupplier> plantSuppliers = plantSupplierRepository.findAll();

        List<PlantInventoryEntryDTO> plantList = new ArrayList<PlantInventoryEntryDTO>();

        for (PlantSupplier plantSupplier : plantSuppliers ) {

                restTemplate = new RestTemplate(autheticationService.getClientHttpRequestFactory(plantSupplier.getClientSystemAccount(), plantSupplier.getPassword()));

                final String baseUrl = plantSupplier.getPlantListHref()+"?name="+plantName+"&startDate="+startDate+"&endDate="+endDate;

              PlantInventoryEntryDTO[] plants = restTemplate.getForObject(baseUrl, PlantInventoryEntryDTO[].class);

                for (PlantInventoryEntryDTO plantInventoryEntryDTO: plants){
                    plantInventoryEntryDTO.setSupplier(plantSupplier.getOurSystemAccount());
//                    plantInventoryEntryDTO.set_self(plantInventoryEntryDTO.get_links().getSelf().getHref());
//                    plantInventoryEntryDTO.set_ownerToken(autheticationService.createBasicAuthString(plantSupplier.getUsername(), plantSupplier.getPassword()));
                }

//            Arrays.asList(plants)
//                        .stream(array)
//                        .collect(Collectors.toList())

                plantList.addAll(Arrays.asList(plants));

        }

        return plantList;
    }

    public List<PurchaseOrderDTO> loadPurchaseOrder() {
        List<PlantSupplier> plantSuppliers = plantSupplierRepository.findAll();

        List<PurchaseOrderDTO> polist = new ArrayList<PurchaseOrderDTO>();

        for (PlantSupplier plantSupplier : plantSuppliers ) {

            restTemplate = new RestTemplate(autheticationService.getClientHttpRequestFactory(plantSupplier.getClientSystemAccount(), plantSupplier.getPassword()));

            final String baseUrl = plantSupplier.getPoListHref();
            //PurchaseOrderDTO
            PurchaseOrderDTO[] podto = restTemplate.getForObject(baseUrl, PurchaseOrderDTO[].class);
//
//            List<PlantInventoryEntryDTO> returnedPlantInventoryEntryDTOes = plants.getBody().get_embedded().getPlantInventoryEntryDToes();
            for (PurchaseOrderDTO purchaseOrderDTO: podto){
                 purchaseOrderDTO.setSupplier(plantSupplier.getOurSystemAccount());
//                plantInventoryEntryDTO.set_self(plantInventoryEntryDTO.get_links().getSelf().getHref());
//                plantInventoryEntryDTO.set_ownerToken(autheticationService.createBasicAuthString(plantSupplier.getUsername(), plantSupplier.getPassword()));
            }
//
            polist.addAll(Arrays.asList(podto));

        }

        return polist;
    }

    public PlantHireRequestDTO createPlantHireRequest(PlantHireRequestDTO plantHireRequestDTO){

        PlantHireRequest plantHire = PlantHireRequest.of(plantHireRequestDTO);

        updatePlantHireRequest(plantHire, PHRStatus.CREATED, "", null);

        PlantSupplier plantSupplier = plantSupplierRepository.findByOwnSystemAccount(plantHire.getSupplier()).orElse(null);

        return plantHireRequestAssembler.toResourceWithToken(plantHire, plantSupplier);
    }

    public PlantHireRequestDTO rejectPlantHireRequest(Long id, String comment) throws Exception {

        PlantHireRequest plantHire = checkPlantHireRequest(id, PHRStatus.CREATED, "The status is not open");
        PlantSupplier plantSupplier = plantSupplierRepository.findByOwnSystemAccount(plantHire.getSupplier()).orElse(null);

        Long daysBeforeStart = ChronoUnit.DAYS.between(plantHire.getRentalPeriod().getStartDate(), LocalDate.now());

        if(daysBeforeStart<=1){
            throw new Exception("PlantHire Request can no longer be canceled is already due");
//            return plantHireRequestAssembler.toResourceWithToken(plantHire, plantSupplier);
        }

        if(plantHire.getStatus() == PHRStatus.PO_CREATED){
            restTemplate = new RestTemplate(autheticationService.getClientHttpRequestFactory(plantSupplier.getClientSystemAccount(), plantSupplier.getPassword()));

            final String baseUrl = plantSupplier.getPoUpdateStatusHref();
            //Todo: Add to the url
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

            StatusMessagingData messagingData = StatusMessagingData.of(plantHire.getPo().getPoId(), POStatus.REJECTED, authentication.getName(), "The Plant Hire Request is cancelled");

            ResponseEntity<Object> response = restTemplate.postForEntity(baseUrl, messagingData ,Object.class);
        }

        updatePlantHireRequest(plantHire, PHRStatus.REJECTED, comment, null);

        return plantHireRequestAssembler.toResourceWithToken(plantHire, plantSupplier);
    }

    public PlantHireRequestDTO approvePlantHireRequest(Long id, String comment) throws Exception  {


        PlantHireRequest plantHire = checkPlantHireRequest(id, PHRStatus.CREATED, "The status is not open");

        createPurchaseOrder(plantHire.getPlant().getItemId(), plantHire.getRentalPeriod(), plantHire, plantHire.getSupplier());

        updatePlantHireRequest(plantHire, PHRStatus.APPROVED, comment, null);

        PlantSupplier plantSupplier = plantSupplierRepository.findByOwnSystemAccount(plantHire.getSupplier()).orElse(null);

        return plantHireRequestAssembler.toResourceWithToken(plantHire, plantSupplier);
    }

    public PlantHireRequestDTO updatePlantHireRequest(PlantHireRequestDTO plantHireRequestDTO) throws Exception {

        PlantHireRequest plantHire = checkPlantHireRequest(plantHireRequestDTO.get_id(), PHRStatus.CREATED, "The status is not open");

        updatePlantHireRequest(plantHire, PHRStatus.CREATED, plantHireRequestDTO.getComment(), plantHireRequestDTO);

        PlantSupplier plantSupplier = plantSupplierRepository.findByOwnSystemAccount(plantHire.getSupplier()).orElse(null);

        return plantHireRequestAssembler.toResourceWithToken(plantHire, plantSupplier);
    }

    public PlantHireRequestDTO  createPOExtension(POExtensionDTO extension){
        PlantHireRequest phr = plantHireRepository.findById(extension.getEntityId()).orElse(null);

        PlantSupplier plantSupplier = plantSupplierRepository.findByOwnSystemAccount(phr.getSupplier()).orElse(null);


        phr.setRentalPeriod(BusinessPeriod.of(phr.getRentalPeriod().getStartDate(), extension.getEndDate()));

        restTemplate = new RestTemplate(autheticationService.getClientHttpRequestFactory(plantSupplier.getClientSystemAccount(), plantSupplier.getPassword()));

        final String baseUrl = plantSupplier.getPoExtensionStatusHref();
        POExtensionDTO rentitPOExtension = POExtensionDTO.of(phr.getPo().getPoId(), extension.getOldEndDate(), extension.getEndDate(), plantSupplier.getClientSystemAccount());
        Object podto = restTemplate.postForEntity(baseUrl, rentitPOExtension, Object.class);

        POExtension newPOextension  = poExtensionRepository.save(POExtension.of(0l, phr.getPo().getPoId(), extension.getOldEndDate(), extension.getEndDate() ));
        phr.setPOExtension(newPOextension);
        phr.setCurrentState(PHRStatus.PO_PENDING_EXTENSION);
        plantHireRepository.save(phr);
        return plantHireRequestAssembler.toResourceWithToken(phr, plantSupplier);
    }

    private PlantHireRequest checkPlantHireRequest(Long id, PHRStatus status, String invalidStateExceptionText) throws Exception {

        PlantHireRequest plantHire = plantHireRepository.findById(id).orElse(null);

        if (plantHire == null) {
            throw new Exception("invalid Plant Hire Request");
        }

        if(status != null){
            if (plantHire.getStatus() != status) {
                throw new Exception(invalidStateExceptionText);
            }
        }

        return plantHire;
    }

    private void updatePlantHireRequest(PlantHireRequest plantHire, PHRStatus status, String comment, PlantHireRequestDTO newPHRValues){
        plantHire.setCurrentState(status);
        plantHire.setComment(comment);
        if(newPHRValues != null && newPHRValues.getRentalPeriod() != null)
        plantHire.setRentalPeriod(newPHRValues.getRentalPeriod());

        plantHireRepository.save(plantHire);
    }

    public void createPurchaseOrder(Long itemId, BusinessPeriod period, PlantHireRequest plantHireRequest, String supplier) {

        PlantSupplier plantSupplier = plantSupplierRepository.findByOwnSystemAccount(supplier).orElse(null);

        restTemplate = new RestTemplate(autheticationService.getClientHttpRequestFactory(plantSupplier.getClientSystemAccount(), plantSupplier.getPassword()));

        ResponseEntity<PurchaseOrderLinks> sample = restTemplate.postForEntity(plantSupplier.getPoCreateHref(), new PurchaseOrderPostData(period, itemId),PurchaseOrderLinks.class);

        plantHireRequest.setPurchaseOrder(sample.getBody().get_links().getFetch().getHref(), sample.getBody().get_id(), plantSupplier.getOurSystemAccount());

        plantHireRepository.save(plantHireRequest);
    }

    public List<PlantHireRequestDTO> plantHireRequests() {

       return plantHireRequestAssembler.toResourcesWithToken(plantHireRepository.findAll(), plantSupplierRepository.findAll());
    }

    public PlantHireRequestDTO plantHireRequest(long id) {
        return plantHireRequestAssembler.toResource(plantHireRepository.findById(id).orElse(null));
    }

    public void updatePlantHireRequestState(StatusMessagingData data) {

        PlantHireRequest phr = getPlantHireRequestByPOAndAuth(data.getEntityId());
        phr.setCurrentState(ConvertToPHRState(data.getState()));

        if(data.getState() == POStatus.REJECT_EXTENSION){
            phr.setRentalPeriod(BusinessPeriod.of(phr.getRentalPeriod().getStartDate(), phr.getExtension().getOldEndDate()));
        }

        if(data.getComment() != null){
            phr.setComment(data.getComment());
        }


        StatusMessagingData returned =  statusMessageDataRepository.save(data);

        phr.getMessages().add(returned);

        plantHireRepository.save(phr);

    }

    private PlantHireRequest getPlantHireRequestByPOAndAuth(Long poid) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();

        PurchaseOrder po = purchaseOrderRepository.findByOwnerAndPoId(currentPrincipalName, poid).orElse(null);
        return plantHireRepository.findByOwnerAndPoId(currentPrincipalName, po).orElse(null);
    }

    public PlantHireRequestDTO approveInvoiceforPO(Long id){
        PlantHireRequest phr = plantHireRepository.findById(id).orElse(null);

        PlantSupplier plantSupplier = plantSupplierRepository.findByOwnSystemAccount(phr.getSupplier()).orElse(null);

        restTemplate = new RestTemplate(autheticationService.getClientHttpRequestFactory(plantSupplier.getClientSystemAccount(), plantSupplier.getPassword()));

        ResponseEntity<Object> sample = restTemplate.postForEntity(plantSupplier.getRemittanceLink(), Remittance.of(phr.getPo().getPoId(), phr.getInvoice().getTotal(), "Invoice Remittance for PO "+ phr.getPo().getPoId() + " of sum " + phr.getInvoice().getTotal()), Object.class);

        phr.setCurrentState(PHRStatus.CLOSED);
        plantHireRepository.save(phr);

        return plantHireRequestAssembler.toResourceWithToken(phr, plantSupplier);
    }

    public PlantHireRequestDTO plantDispatchedforPO(Long id){
        return null;
    }

    public void submitInvoiceforReturnedPO(InvoiceDTO invoice  ){
        PlantHireRequest phr = getPlantHireRequestByPOAndAuth(invoice.getPoId());

        Invoice inv = Invoice.of(0l, invoice.getTotal(), null);
        Invoice newInvoice = invoiceRepository.save(inv);

        phr.setInvoice(newInvoice);
        phr.setCurrentState(PHRStatus.INVOICED);

        plantHireRepository.save(phr);
    }

    public void submitInvoiceforReminderPO(InvoiceReminderDTO invoice){
        PlantHireRequest phr = getPlantHireRequestByPOAndAuth(invoice.getPoId());

        Reminder reminder = Reminder.of(0l, invoice.getDescription());

        Reminder newReminder = invoiceReminderRepository.save(reminder);

        phr.getInvoice().getReminder().add(newReminder);

        plantHireRepository.save(phr);
    }

    private PHRStatus ConvertToPHRState(POStatus state) {
        switch (state){
            case OPEN: return PHRStatus.PO_OPEN;

            case CLOSED: return PHRStatus.PO_CLOSED;

            case REJECT_EXTENSION: return  PHRStatus.PO_REJECT_EXTENSION;

            case PENDING: return PHRStatus.PO_CREATED;

            case REJECTED: return PHRStatus.PO_REJECTED;

            case PENDING_EXTENSION: return PHRStatus.PO_PENDING_EXTENSION;
            default:
            return PHRStatus.PO_UNMAPPED;

        }
    }
}
