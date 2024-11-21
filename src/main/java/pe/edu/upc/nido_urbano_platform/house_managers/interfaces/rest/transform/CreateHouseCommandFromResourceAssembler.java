package pe.edu.upc.nido_urbano_platform.house_managers.interfaces.rest.transform;

import pe.edu.upc.nido_urbano_platform.house_managers.domain.model.commands.CreateHouseCommand;
import pe.edu.upc.nido_urbano_platform.house_managers.interfaces.rest.resources.CreateHouseResource;

public class CreateHouseCommandFromResourceAssembler {
    public static CreateHouseCommand toCommandFromResource(CreateHouseResource resource) {
        return new CreateHouseCommand(
                resource.userPropertyID(),
                resource.houseName(),
                resource.address(),
                resource.houseType(),
                resource.houseModal(),
                resource.price(),
                resource.size(),
                resource.description(),
                resource.publicationDate(),
                resource.startsCalification(),
                resource.statusLandlord(),
                resource.photo(),
                resource.video(),
                resource.termsConditions()
        );
    }
}
