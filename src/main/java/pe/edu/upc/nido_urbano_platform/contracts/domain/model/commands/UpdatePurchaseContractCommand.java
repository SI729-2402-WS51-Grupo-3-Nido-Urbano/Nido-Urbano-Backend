package pe.edu.upc.nido_urbano_platform.contracts.domain.model.commands;

import java.util.Date;

public record UpdatePurchaseContractCommand (
        Long Id, String status, Double purchasePrice, String paymentMethod, Date closingDate, Boolean agreedTerms
){
}
