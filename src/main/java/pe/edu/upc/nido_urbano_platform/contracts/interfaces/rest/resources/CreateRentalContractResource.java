package pe.edu.upc.nido_urbano_platform.contracts.interfaces.rest.resources;

import java.util.Date;

public record CreateRentalContractResource(Long  propertyId,
                                           Long  userId,
                                           Long  landlordId,
                                           String status,
                                           Double rent,
                                           String paymentFrequency,
                                           Double depositAmount,
                                           Double terminationFee,
                                           String paymentMethod,
                                           String terms,
                                           Boolean agreedTerms,
                                           Date startDate,
                                           Date endDate
) {}