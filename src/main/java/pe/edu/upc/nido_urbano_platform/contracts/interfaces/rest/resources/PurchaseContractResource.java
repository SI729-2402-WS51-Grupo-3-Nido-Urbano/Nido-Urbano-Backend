package pe.edu.upc.nido_urbano_platform.contracts.interfaces.rest.resources;

import java.util.Date;

public record PurchaseContractResource(Long id,
                                       pe.edu.upc.nido_urbano_platform.contracts.domain.model.valueobjects.PropertyId propertyId,
                                       pe.edu.upc.nido_urbano_platform.contracts.domain.model.valueobjects.UserId userId,
                                       pe.edu.upc.nido_urbano_platform.contracts.domain.model.valueobjects.LandlordId landlordId,
                                       String status,
                                       Double purchasePrice,
                                       String paymentMethod,
                                       String downPayment,
                                       pe.edu.upc.nido_urbano_platform.contracts.domain.model.valueobjects.Terms terms,
                                       Boolean agreedTerms,
                                       Date closingDate,
                                       Boolean transferCostsIncluded) {
}
