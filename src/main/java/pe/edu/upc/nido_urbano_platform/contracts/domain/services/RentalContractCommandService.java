package pe.edu.upc.nido_urbano_platform.contracts.domain.services;

import pe.edu.upc.nido_urbano_platform.contracts.domain.model.aggregates.RentalContract;
import pe.edu.upc.nido_urbano_platform.contracts.domain.model.commands.CreateRentalContractCommand;
import pe.edu.upc.nido_urbano_platform.contracts.domain.model.commands.DeleteRentalContractCommand;
import pe.edu.upc.nido_urbano_platform.contracts.domain.model.commands.UpdateRentalContractCommand;

import java.util.Optional;

public interface RentalContractCommandService {
    Long handle(CreateRentalContractCommand command);

    Optional<RentalContract> handle(UpdateRentalContractCommand command);

    void handle(DeleteRentalContractCommand command);
}
