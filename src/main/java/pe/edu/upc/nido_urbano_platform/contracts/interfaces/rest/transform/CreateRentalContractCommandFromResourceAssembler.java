package pe.edu.upc.nido_urbano_platform.contracts.interfaces.rest.transform;

import pe.edu.upc.nido_urbano_platform.contracts.domain.model.commands.CreateRentalContractCommand;
import pe.edu.upc.nido_urbano_platform.contracts.interfaces.rest.resources.CreateRentalContractResource;

public class CreateRentalContractCommandFromResourceAssembler {
    public static CreateRentalContractCommand toCommandFromResource(CreateRentalContractResource resource){
        return new CreateRentalContractCommand(resource.propertyId(), resource.userId(), resource.landlordId(),
                resource.status(), resource.rent(), resource.paymentFrequency(), resource.depositAmount(), resource.terminationFee(),
                resource.paymentMethod(), resource.terms(), resource.agreedTerms(), resource.startDate(), resource.endDate());
    }
}
