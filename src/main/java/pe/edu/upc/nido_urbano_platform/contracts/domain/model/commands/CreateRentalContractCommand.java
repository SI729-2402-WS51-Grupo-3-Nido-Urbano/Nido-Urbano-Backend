package pe.edu.upc.nido_urbano_platform.contracts.domain.model.commands;


import java.util.Date;

public record CreateRentalContractCommand(
        Long  propertyId,
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
) {
}
