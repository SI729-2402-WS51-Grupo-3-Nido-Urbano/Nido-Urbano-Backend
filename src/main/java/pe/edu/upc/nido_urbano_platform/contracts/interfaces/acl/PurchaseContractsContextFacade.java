package pe.edu.upc.nido_urbano_platform.contracts.interfaces.acl;

import org.springframework.stereotype.Service;
import pe.edu.upc.nido_urbano_platform.contracts.domain.model.commands.CreatePurchaseContractCommand;
import pe.edu.upc.nido_urbano_platform.contracts.domain.model.commands.DeletePurchaseContractCommand;
import pe.edu.upc.nido_urbano_platform.contracts.domain.model.commands.UpdatePurchaseContractCommand;
import pe.edu.upc.nido_urbano_platform.contracts.domain.model.valueobjects.Terms;
import pe.edu.upc.nido_urbano_platform.contracts.domain.services.PurchaseContractCommandService;
import pe.edu.upc.nido_urbano_platform.contracts.domain.services.PurchaseContractQueryService;

import java.util.Date;

@Service
public class PurchaseContractsContextFacade {
    private final PurchaseContractCommandService purchaseContractCommandService;
    private final PurchaseContractQueryService purchaseContractQueryService;

    public PurchaseContractsContextFacade(PurchaseContractCommandService purchaseContractCommandService, PurchaseContractQueryService purchaseContractQueryService) {
        this.purchaseContractCommandService = purchaseContractCommandService;
        this.purchaseContractQueryService = purchaseContractQueryService;
    }

    public Long createPurchaseContract( Long  propertyId,
                                        Long  userId,
                                        Long  landlordId,
                                        String status,
                                        Double purchasePrice,
                                        String paymentMethod,
                                        String downPayment,
                                        String terms,
                                        Boolean agreedTerms,
                                        Date closingDate,
                                        Boolean transferCostsIncluded){
        var CreatePurchaseContractCommand = new CreatePurchaseContractCommand(propertyId,userId,landlordId,status,purchasePrice,
                paymentMethod,downPayment,terms,agreedTerms,closingDate,transferCostsIncluded);
        var purchaseContractId = purchaseContractCommandService.handle(CreatePurchaseContractCommand);
        if(purchaseContractId.equals(null)){
            return 0L;
        }
        return purchaseContractId;
    }
    public Long updatePurchaseContract(Long Id, String status, Double purchasePrice, String paymentMethod, Date closingDate, Boolean agreedTerms){
        var updatePurchaseContractCommand = new UpdatePurchaseContractCommand(Id, status,purchasePrice,paymentMethod,closingDate,agreedTerms);
        var optionalPurchaseContract = purchaseContractCommandService.handle(updatePurchaseContractCommand);
        if(optionalPurchaseContract.isEmpty()){
            return 0L;
        }
        return optionalPurchaseContract.get().getId();
    }
    public void deletePurchaseContract(Long Id){
        var deletePurchaseContractCommand = new DeletePurchaseContractCommand(Id);
        purchaseContractCommandService.handle(deletePurchaseContractCommand);
    }
}
