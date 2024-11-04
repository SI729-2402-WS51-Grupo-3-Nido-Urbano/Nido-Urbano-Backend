package pe.edu.upc.nido_urbano_platform.housing_management.interfaces.rest.transform;

import pe.edu.upc.nido_urbano_platform.housing_management.domain.model.commands.CreateHouseCommand;
import pe.edu.upc.nido_urbano_platform.housing_management.interfaces.rest.resources.CreateHouseResource;

public class CreateHouseCommandFromResourceAssembler {
    public static CreateHouseCommand toCommandFromResource(CreateHouseResource resource) {
        return new CreateHouseCommand(resource.startDate(), resource.endDate(), resource.address());
    }
}
