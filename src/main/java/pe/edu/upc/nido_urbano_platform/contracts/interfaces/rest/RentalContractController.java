package pe.edu.upc.nido_urbano_platform.contracts.interfaces.rest;


import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.nido_urbano_platform.contracts.domain.model.commands.DeleteRentalContractCommand;
import pe.edu.upc.nido_urbano_platform.contracts.domain.model.commands.UpdateRentalContractCommand;
import pe.edu.upc.nido_urbano_platform.contracts.domain.model.queries.GetContractByIdQuery;
import pe.edu.upc.nido_urbano_platform.contracts.domain.services.RentalContractCommandService;
import pe.edu.upc.nido_urbano_platform.contracts.domain.services.RentalContractQueryService;
import pe.edu.upc.nido_urbano_platform.contracts.interfaces.rest.resources.CreateRentalContractResource;
import pe.edu.upc.nido_urbano_platform.contracts.interfaces.rest.resources.RentalContractResource;
import pe.edu.upc.nido_urbano_platform.contracts.interfaces.rest.transform.CreateRentalContractCommandFromResourceAssembler;
import pe.edu.upc.nido_urbano_platform.contracts.interfaces.rest.transform.RentalContractResourceFromEntityAssembler;
import pe.edu.upc.nido_urbano_platform.contracts.interfaces.rest.transform.UpdateRentalContractCommandFromResourceAssembler;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "**", methods = { RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT, RequestMethod.DELETE })
@RestController
@RequestMapping(value = "/api/v1/rentalContracts", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Rental Contracts", description = "Rental Contracts Management Endpoints")
public class RentalContractController {
    private final RentalContractCommandService rentalContractCommandService;
    private final RentalContractQueryService rentalContractQueryService;

    public RentalContractController(RentalContractCommandService rentalContractCommandService, RentalContractQueryService rentalContractQueryService) {
        this.rentalContractCommandService = rentalContractCommandService;
        this.rentalContractQueryService = rentalContractQueryService;
    }

    @PostMapping
    public ResponseEntity<RentalContractResource> createRentalContract(@RequestBody CreateRentalContractResource resource){
        var createRentalContractCommand = CreateRentalContractCommandFromResourceAssembler
                .toCommandFromResource(resource);
        var rentalContractId = this.rentalContractCommandService.handle(createRentalContractCommand);

        if(rentalContractId.equals(0L)){
            return ResponseEntity.badRequest().build();
        }

        var getRentalContractId = new GetContractByIdQuery(rentalContractId);
        var optionsRentalContract = this.rentalContractQueryService.handle(getRentalContractId);

        var rentalContractResource = RentalContractResourceFromEntityAssembler.toResourceFromEntity(optionsRentalContract.get());
        return new ResponseEntity<>(rentalContractResource, HttpStatus.OK);
    }
    @GetMapping("/{rentalContractId}")
    public ResponseEntity<List<RentalContractResource>> getContractById(@PathVariable Long rentalContractId){
        var getRentContractByIdQuery = new GetContractByIdQuery(rentalContractId);
        var RentalContracts = this.rentalContractQueryService.handle(getRentContractByIdQuery);
        var rentalContractResources = RentalContracts.stream()
                .map(RentalContractResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(rentalContractResources);
    }
    @PutMapping("/{rentalContractId}")
    public ResponseEntity<RentalContractResource> updateRentalContract(@PathVariable Long rentalContractId, @RequestBody RentalContractResource resource){
        var updateRentContractCommand = UpdateRentalContractCommandFromResourceAssembler.toCommandFromResource(rentalContractId, resource);
        var optionalRentContract = this.rentalContractCommandService.handle(updateRentContractCommand);
        if(optionalRentContract.isEmpty())
            return ResponseEntity.badRequest().build();
        var RentContractResource = RentalContractResourceFromEntityAssembler.toResourceFromEntity(optionalRentContract.get());
        return ResponseEntity.ok(RentContractResource);
    }

    @DeleteMapping("/{rentalContractId}")
    public ResponseEntity<?> deleteRentContract(@PathVariable Long rentalContractId){
        var deleteRentContractCommand = new DeleteRentalContractCommand(rentalContractId);
        this.rentalContractCommandService.handle(deleteRentContractCommand);
        return ResponseEntity.noContent().build();
    }
}
