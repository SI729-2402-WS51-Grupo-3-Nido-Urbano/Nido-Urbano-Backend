package pe.edu.upc.nido_urbano_platform.house_managers.interfaces.rest.transform;

import pe.edu.upc.nido_urbano_platform.house_managers.domain.model.commands.UpdateHouseCommand;
import pe.edu.upc.nido_urbano_platform.house_managers.interfaces.rest.resources.HouseResource;

public class UpdateHouseCommandFromResourceAssembler {
    public static UpdateHouseCommand toCommandFromResource(Long houseId, HouseResource resource) {
        return new UpdateHouseCommand(houseId,
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
