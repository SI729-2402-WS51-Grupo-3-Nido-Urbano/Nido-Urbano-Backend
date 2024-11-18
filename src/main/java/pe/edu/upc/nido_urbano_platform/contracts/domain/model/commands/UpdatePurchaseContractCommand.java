package pe.edu.upc.nido_urbano_platform.contracts.domain.model.commands;

import pe.edu.upc.nido_urbano_platform.contracts.domain.model.valueobjects.Term;

import java.util.Date;

public record UpdatePurchaseContractCommand (
        Long Id, String status, Double purchasePrice, String paymentMethod, Date closingDate, Term terms
){
}
