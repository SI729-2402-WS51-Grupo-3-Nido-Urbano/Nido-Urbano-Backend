package pe.edu.upc.nido_urbano_platform.house.domain.services;

import pe.edu.upc.nido_urbano_platform.house.domain.model.entities.Verification;
import pe.edu.upc.nido_urbano_platform.house.domain.model.queries.GetAllVerificationQuery;
import pe.edu.upc.nido_urbano_platform.house.domain.model.queries.GetVerificationByIdQuery;

import java.util.List;
import java.util.Optional;

public interface VerificationQueryService {
    List<Verification> handle(GetAllVerificationQuery query);
    Optional<Verification> handle(GetVerificationByIdQuery query);
}
