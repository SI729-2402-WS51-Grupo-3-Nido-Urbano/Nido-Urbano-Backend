package pe.edu.upc.nido_urbano_platform.contracts.interfaces.rest.resources;

import java.util.Date;

public record RentalContractResource(Long id,
                                     pe.edu.upc.nido_urbano_platform.contracts.domain.model.valueobjects.PropertyId propertyId,
                                     pe.edu.upc.nido_urbano_platform.contracts.domain.model.valueobjects.UserId userId,
                                     pe.edu.upc.nido_urbano_platform.contracts.domain.model.valueobjects.LandlordId landlordId,
                                     String status,
                                     Double rent,
                                     String paymentFrequency,
                                     Double depositAmount,
                                     Double terminationFee,
                                     String paymentMethod,
                                     pe.edu.upc.nido_urbano_platform.contracts.domain.model.valueobjects.Terms terms,
                                     Boolean agreedTerms,
                                     Date startDate,
                                     Date endDate
) {}