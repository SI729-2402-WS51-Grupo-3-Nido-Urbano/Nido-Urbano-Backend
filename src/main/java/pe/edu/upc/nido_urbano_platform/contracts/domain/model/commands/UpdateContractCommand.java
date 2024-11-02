package pe.edu.upc.nido_urbano_platform.contracts.domain.model.commands;

import pe.edu.upc.nido_urbano_platform.contracts.domain.model.valueobjects.Term;

import java.util.Date;

public record UpdateContractCommand(
        Long contractId,
        double price,
        Term terms,
        Date startDate,
        Date endDate
) {}

