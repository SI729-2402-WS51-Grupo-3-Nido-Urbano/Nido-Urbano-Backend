package pe.edu.upc.nido_urbano_platform.contracts.interfaces.rest.transform;

import pe.edu.upc.nido_urbano_platform.contracts.domain.model.commands.CreateRentalContractCommand;
import pe.edu.upc.nido_urbano_platform.contracts.interfaces.rest.resources.CreateContractResource;

public class CreateContractCommandFromResourceAssembler {
    public static CreateRentalContractCommand toCommandFromResource(CreateContractResource resource) {
        return new CreateRentalContractCommand(
                resource.propertyId(),
                resource.tenantId(),
                resource.landlordId(),
                resource.price(),
                resource.termsDescription(),
                resource.startDate(),
                resource.endDate()
        );
    }
}
