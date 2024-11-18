package pe.edu.upc.nido_urbano_platform.housing_management.interfaces.rest.transform;

import pe.edu.upc.nido_urbano_platform.housing_management.domain.model.commands.UpdateHouseCommand;
import pe.edu.upc.nido_urbano_platform.housing_management.domain.model.valueobjects.HouseCode;
import pe.edu.upc.nido_urbano_platform.housing_management.interfaces.rest.resources.HouseResource;

public class UpdateHouseCommandFromResourceAssembler {
    public static UpdateHouseCommand toCommandFromResource(String houseCode, HouseResource resource) {
        return new UpdateHouseCommand(new HouseCode(houseCode),
                resource.startDate(),
                resource.endDate(),
                resource.address(),
                resource.tenantName()
        );
    }
}
