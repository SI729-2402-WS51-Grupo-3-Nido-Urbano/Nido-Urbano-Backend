package pe.edu.upc.nido_urbano_platform.contracts.interfaces.rest.transform;

import pe.edu.upc.nido_urbano_platform.contracts.domain.model.aggregates.Contract;
import pe.edu.upc.nido_urbano_platform.contracts.interfaces.rest.resources.ContractResource;

public class ContractResourceFromEntityAssembler {
    public static ContractResource toResourceFromEntity(Contract contract) {
        return new ContractResource(
                contract.getId(),
                (Long) contract.getProperty().getId(),
                (Long) contract.getTenant().getId(),
                (Long) contract.getLandlord().getId(),
                contract.getProperty().getPrice(),
                contract.getTerms().getDescription(),
                contract.getStartDate(),
                contract.getEndDate()
        );
    }
}
