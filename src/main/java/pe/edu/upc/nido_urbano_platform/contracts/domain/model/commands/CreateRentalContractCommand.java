package pe.edu.upc.nido_urbano_platform.contracts.domain.model.commands;


import lombok.Builder;
import pe.edu.upc.nido_urbano_platform.contracts.domain.model.valueobjects.LandlordId;
import pe.edu.upc.nido_urbano_platform.contracts.domain.model.valueobjects.PropertyId;
import pe.edu.upc.nido_urbano_platform.contracts.domain.model.valueobjects.Term;
import pe.edu.upc.nido_urbano_platform.contracts.domain.model.valueobjects.UserId;

import java.util.Date;

public record CreateRentalContractCommand(
        Long  propertyId,
        Long  userId,
        Long  landlordId,
        String status,
        Double rent,
        Double depositAmount,
        Double terminationFee,
        String paymentMethod,
        Term terms,
        Date startDate,
        Date endDate
) {
}
