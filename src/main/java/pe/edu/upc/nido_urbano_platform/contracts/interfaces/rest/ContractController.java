package pe.edu.upc.nido_urbano_platform.contracts.interfaces.rest;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.edu.upc.nido_urbano_platform.contracts.domain.model.aggregates.Contract;
import pe.edu.upc.nido_urbano_platform.contracts.domain.model.commands.CreateContractCommand;
import pe.edu.upc.nido_urbano_platform.contracts.domain.services.ContractCommandService;
import pe.edu.upc.nido_urbano_platform.contracts.domain.services.ContractQueryService;
import pe.edu.upc.nido_urbano_platform.contracts.interfaces.rest.resources.ContractResource;
import pe.edu.upc.nido_urbano_platform.contracts.interfaces.rest.transform.ContractResourceFromEntityAssembler;
import pe.edu.upc.nido_urbano_platform.contracts.interfaces.rest.transform.CreateContractCommandFromResourceAssembler;
import pe.edu.upc.nido_urbano_platform.contracts.interfaces.rest.resources.CreateContractResource;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/contracts")
@Tag(name = "Contracts", description = "Endpoints for managing contracts")
public class ContractController {

    private final ContractCommandService contractCommandService;
    private final ContractQueryService contractQueryService;

    public ContractController(ContractCommandService contractCommandService, ContractQueryService contractQueryService) {
        this.contractCommandService = contractCommandService;
        this.contractQueryService = contractQueryService;
    }

    @PostMapping
    public ResponseEntity<ContractResource> createContract(@RequestBody CreateContractResource resource) {
        CreateContractCommand createContractCommand = CreateContractCommandFromResourceAssembler.toCommandFromResource(resource);
        Long contractId = contractCommandService.handle(createContractCommand);

        if (contractId.equals(0L)) {
            return ResponseEntity.badRequest().build();
        }

        Optional<Contract> optionalContract = contractQueryService.getContractDetails(contractId);

        if (optionalContract.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        
        if (resource.price() == null) {
            throw new IllegalArgumentException("El precio no puede ser nulo");
        }
        ContractResource contractResource = ContractResourceFromEntityAssembler.toResourceFromEntity(optionalContract.get());
        return new ResponseEntity<>(contractResource, HttpStatus.CREATED);
    }
}