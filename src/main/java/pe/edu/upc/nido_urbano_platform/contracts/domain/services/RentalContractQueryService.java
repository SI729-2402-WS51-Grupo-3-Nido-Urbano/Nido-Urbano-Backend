package pe.edu.upc.nido_urbano_platform.contracts.domain.services;

import pe.edu.upc.nido_urbano_platform.contracts.domain.model.aggregates.RentalContract;
import pe.edu.upc.nido_urbano_platform.contracts.domain.model.queries.GetAllContractsByPropertyIdQuery;
import pe.edu.upc.nido_urbano_platform.contracts.domain.model.queries.GetAllContractsByLandLordIdQuery;
import pe.edu.upc.nido_urbano_platform.contracts.domain.model.queries.GetAllContractsByUserIdQuery;
import pe.edu.upc.nido_urbano_platform.contracts.domain.model.queries.GetContractByIdQuery;

import java.util.Optional;

public interface RentalContractQueryService {
    Optional<RentalContract> handle(GetAllContractsByUserIdQuery query);
    Optional<RentalContract> handle(GetAllContractsByLandLordIdQuery query);
    Optional<RentalContract> handle(GetAllContractsByPropertyIdQuery query);
    Optional<RentalContract> handle(GetContractByIdQuery query);
}
