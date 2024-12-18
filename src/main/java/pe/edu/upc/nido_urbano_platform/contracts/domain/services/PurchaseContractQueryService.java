package pe.edu.upc.nido_urbano_platform.contracts.domain.services;

import pe.edu.upc.nido_urbano_platform.contracts.domain.model.aggregates.PurchaseContract;
import pe.edu.upc.nido_urbano_platform.contracts.domain.model.queries.GetAllContractsByLandLordIdQuery;
import pe.edu.upc.nido_urbano_platform.contracts.domain.model.queries.GetAllContractsByUserIdQuery;
import pe.edu.upc.nido_urbano_platform.contracts.domain.model.queries.GetContractByIdQuery;
import pe.edu.upc.nido_urbano_platform.contracts.domain.model.queries.GetContractIdByPropertyIdQuery;

import java.util.Optional;

public interface PurchaseContractQueryService {

    Optional<PurchaseContract> handle(GetAllContractsByUserIdQuery query);
    Optional<PurchaseContract> handle(GetAllContractsByLandLordIdQuery query);
    Optional<PurchaseContract> handle(GetContractIdByPropertyIdQuery query);
    Optional<PurchaseContract> handle(GetContractByIdQuery query);
}
