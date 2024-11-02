package pe.edu.upc.nido_urbano_platform.contracts.domain.services;

import pe.edu.upc.nido_urbano_platform.contracts.domain.model.aggregates.Contract;
import pe.edu.upc.nido_urbano_platform.contracts.domain.model.commands.CreateContractCommand;
import pe.edu.upc.nido_urbano_platform.contracts.domain.model.commands.DeleteContractCommand;
import pe.edu.upc.nido_urbano_platform.contracts.domain.model.commands.UpdateContractCommand;

import java.util.Date;
import java.util.Optional;

public interface ContractCommandService {
    Long handle(CreateContractCommand command);
    Optional<Contract> handle(UpdateContractCommand command);
    void handle(DeleteContractCommand command);
}
