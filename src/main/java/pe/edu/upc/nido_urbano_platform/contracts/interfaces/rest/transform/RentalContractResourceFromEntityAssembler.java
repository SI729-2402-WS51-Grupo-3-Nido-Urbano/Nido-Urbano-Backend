package pe.edu.upc.nido_urbano_platform.contracts.interfaces.rest.transform;

import pe.edu.upc.nido_urbano_platform.contracts.domain.model.aggregates.RentalContract;
import pe.edu.upc.nido_urbano_platform.contracts.interfaces.rest.resources.RentalContractResource;

public class RentalContractResourceFromEntityAssembler {
    public static RentalContractResource toResourceFromEntity(RentalContract entity){
        return new RentalContractResource(entity.getId(), entity.getPropertyId(), entity.getUserId(),
                entity.getLandlordId(), entity.getStatus(), entity.getRent(), entity.getPaymentFrequency(), entity.getDepositAmount(),
                entity.getTerminationFee(), entity.getPaymentMethod(), entity.getTerms(),
                entity.getAgreedTerms(), entity.getStartDate(), entity.getEndDate());
    }
}
