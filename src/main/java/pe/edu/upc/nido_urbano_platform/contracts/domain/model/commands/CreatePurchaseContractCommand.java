package pe.edu.upc.nido_urbano_platform.contracts.domain.model.commands;

import pe.edu.upc.nido_urbano_platform.contracts.domain.model.valueobjects.Term;

import java.util.Date;

public record CreatePurchaseContractCommand (
        Long  propertyId,
        Long  userId,
        Long  landlordId,
        String status,
        Double purchasePrice,
        String paymentMethod,
        String downPayment,
        Term terms,
        Date closingDate,
        Boolean transferCostsIncluded
      ){
}
