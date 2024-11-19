package pe.edu.upc.nido_urbano_platform.contracts.interfaces.rest.transform;

import pe.edu.upc.nido_urbano_platform.contracts.domain.model.commands.CreatePurchaseContractCommand;
import pe.edu.upc.nido_urbano_platform.contracts.interfaces.rest.resources.CreatePurchaseContractResource;

public class CreatePurchaseContractCommandFromResourceAssembler {
    public static CreatePurchaseContractCommand toCommandFromResource(CreatePurchaseContractResource resource){
        return new CreatePurchaseContractCommand(resource.propertyId(), resource.userId(), resource.landlordId(),
                resource.status(), resource.purchasePrice(), resource.paymentMethod(), resource.downPayment(),
                resource.terms(), resource.agreedTerms(),resource.closingDate(),resource.transferCostsIncluded());
    }
}
