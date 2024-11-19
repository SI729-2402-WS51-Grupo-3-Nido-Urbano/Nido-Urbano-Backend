package pe.edu.upc.nido_urbano_platform.contracts.domain.model.commands;

public record UpdateRentalContractCommand(
        Long Id, String status, Double rent, String paymentFrequency, Double depositAmount, Double terminationFee, String paymentMethod, Boolean agreedTerms) {
}

