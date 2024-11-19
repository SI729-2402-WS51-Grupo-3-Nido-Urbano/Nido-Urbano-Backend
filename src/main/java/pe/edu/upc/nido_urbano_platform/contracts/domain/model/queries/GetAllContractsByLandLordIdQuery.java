package pe.edu.upc.nido_urbano_platform.contracts.domain.model.queries;

import pe.edu.upc.nido_urbano_platform.contracts.domain.model.valueobjects.LandlordId;

public record GetAllContractsByLandLordIdQuery(LandlordId landlordId) {
}
