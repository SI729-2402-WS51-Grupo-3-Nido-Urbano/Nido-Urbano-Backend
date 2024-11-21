package pe.edu.upc.nido_urbano_platform.contracts.interfaces.rest.transform;

import pe.edu.upc.nido_urbano_platform.contracts.domain.model.commands.UpdateRentalContractCommand;
import pe.edu.upc.nido_urbano_platform.contracts.interfaces.rest.resources.RentalContractResource;

public class UpdateRentalContractCommandFromResourceAssembler {
    public static UpdateRentalContractCommand toCommandFromResource(Long Id, RentalContractResource resource){
        return new UpdateRentalContractCommand(Id, resource.status(), resource.rent(),
                resource.paymentFrequency(), resource.depositAmount(), resource.terminationFee(),
                resource.paymentMethod(), resource.agreedTerms());
    }
}
