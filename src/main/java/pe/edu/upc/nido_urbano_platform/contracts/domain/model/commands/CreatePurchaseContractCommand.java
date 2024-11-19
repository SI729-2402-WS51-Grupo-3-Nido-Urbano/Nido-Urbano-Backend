package pe.edu.upc.nido_urbano_platform.contracts.domain.model.commands;

import java.util.Date;

public record CreatePurchaseContractCommand (
        Long  propertyId,
        Long  userId,
        Long  landlordId,
        String status,
        Double purchasePrice,
        String paymentMethod,
        String downPayment,
        String terms,
        Boolean agreedTerms,
        Date closingDate,
        Boolean transferCostsIncluded
      ){
}
