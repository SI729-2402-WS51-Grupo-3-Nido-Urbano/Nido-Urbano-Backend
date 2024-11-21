package pe.edu.upc.nido_urbano_platform.contracts.interfaces.rest.transform;

import pe.edu.upc.nido_urbano_platform.contracts.domain.model.aggregates.PurchaseContract;
import pe.edu.upc.nido_urbano_platform.contracts.interfaces.rest.resources.PurchaseContractResource;

public class PurchaseContractResourceFromEntityAssembler {
    public static PurchaseContractResource toResourceFromEntity(PurchaseContract entity){
        return new PurchaseContractResource(entity.getId(), entity.getPropertyId(), entity.getUserId(),
                entity.getLandlordId(), entity.getStatus(), entity.getPurchasePrice(), entity.getPaymentMethod(),
                entity.getDownPayment(), entity.getTerms(), entity.getAgreedTerms(), entity.getClosingDate(),
                entity.getTransferCostsIncluded());
    }
}
