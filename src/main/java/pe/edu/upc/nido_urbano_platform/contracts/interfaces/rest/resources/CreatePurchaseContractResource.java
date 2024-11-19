package pe.edu.upc.nido_urbano_platform.contracts.interfaces.rest.resources;

import java.util.Date;

public record CreatePurchaseContractResource(Long  propertyId,
                                             Long  userId,
                                             Long  landlordId,
                                             String status,
                                             Double purchasePrice,
                                             String paymentMethod,
                                             String downPayment,
                                             String terms,
                                             Boolean agreedTerms,
                                             Date closingDate,
                                             Boolean transferCostsIncluded) {
}
