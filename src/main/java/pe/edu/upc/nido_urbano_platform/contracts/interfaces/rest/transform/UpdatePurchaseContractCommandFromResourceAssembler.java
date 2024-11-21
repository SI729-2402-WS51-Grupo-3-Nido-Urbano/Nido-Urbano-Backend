package pe.edu.upc.nido_urbano_platform.contracts.interfaces.rest.transform;

import pe.edu.upc.nido_urbano_platform.contracts.domain.model.commands.UpdatePurchaseContractCommand;
import pe.edu.upc.nido_urbano_platform.contracts.interfaces.rest.resources.PurchaseContractResource;

public class UpdatePurchaseContractCommandFromResourceAssembler {
    public static UpdatePurchaseContractCommand toCommandFromResource(Long Id, PurchaseContractResource resource){
        return new UpdatePurchaseContractCommand(Id, resource.status(), resource.purchasePrice(),
                resource.paymentMethod(), resource.closingDate(), resource.agreedTerms());
    }
}
