package pe.edu.upc.nido_urbano_platform.contracts.interfaces.rest.transform;

import pe.edu.upc.nido_urbano_platform.contracts.domain.model.commands.CreateContractCommand;
import pe.edu.upc.nido_urbano_platform.contracts.interfaces.rest.resources.CreateContractResource;

public class CreateContractCommandFromResourceAssembler {
    public static CreateContractCommand toCommandFromResource(CreateContractResource resource) {
        return new CreateContractCommand(
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
