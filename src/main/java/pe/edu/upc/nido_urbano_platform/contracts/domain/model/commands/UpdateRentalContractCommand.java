package pe.edu.upc.nido_urbano_platform.contracts.domain.model.commands;

import pe.edu.upc.nido_urbano_platform.contracts.domain.model.valueobjects.Term;

public record UpdateRentalContractCommand(
        Long Id,String status, Double rent, String paymentFrequency, Double depositAmount, Double terminationFee, String paymentMethod,Term terms) {
}

