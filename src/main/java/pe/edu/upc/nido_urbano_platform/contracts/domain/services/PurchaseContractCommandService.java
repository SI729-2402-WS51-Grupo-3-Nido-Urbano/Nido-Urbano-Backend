package pe.edu.upc.nido_urbano_platform.contracts.domain.services;

import pe.edu.upc.nido_urbano_platform.contracts.domain.model.aggregates.PurchaseContract;
import pe.edu.upc.nido_urbano_platform.contracts.domain.model.commands.*;

import java.util.Optional;

public interface PurchaseContractCommandService {
    Long handle(CreatePurchaseContractCommand command);

    Optional<PurchaseContract> handle(UpdatePurchaseContractCommand command);

    void handle(DeletePurchaseContractCommand command);
}
