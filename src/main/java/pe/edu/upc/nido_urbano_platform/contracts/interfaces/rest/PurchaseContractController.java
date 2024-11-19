package pe.edu.upc.nido_urbano_platform.contracts.interfaces.rest;


import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.nido_urbano_platform.contracts.domain.model.commands.DeletePurchaseContractCommand;
import pe.edu.upc.nido_urbano_platform.contracts.domain.model.queries.GetContractByIdQuery;
import pe.edu.upc.nido_urbano_platform.contracts.domain.services.PurchaseContractCommandService;
import pe.edu.upc.nido_urbano_platform.contracts.domain.services.PurchaseContractQueryService;
import pe.edu.upc.nido_urbano_platform.contracts.interfaces.rest.resources.CreatePurchaseContractResource;
import pe.edu.upc.nido_urbano_platform.contracts.interfaces.rest.resources.PurchaseContractResource;
import pe.edu.upc.nido_urbano_platform.contracts.interfaces.rest.transform.CreatePurchaseContractCommandFromResourceAssembler;
import pe.edu.upc.nido_urbano_platform.contracts.interfaces.rest.transform.PurchaseContractResourceFromEntityAssembler;
import pe.edu.upc.nido_urbano_platform.contracts.interfaces.rest.transform.UpdatePurchaseContractCommandFromResourceAssembler;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", methods = { RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT, RequestMethod.DELETE })
@RestController
@RequestMapping(value = "/api/v1/purchaseContracts", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "PurchaseContracts", description = "Purchase Contracts Management Endpoints")
public class PurchaseContractController {
    private final PurchaseContractQueryService purchaseContractQueryService;
    private final PurchaseContractCommandService purchaseContractCommandService;

    public PurchaseContractController(PurchaseContractQueryService purchaseContractQueryService, PurchaseContractCommandService purchaseContractCommandService) {
        this.purchaseContractQueryService = purchaseContractQueryService;
        this.purchaseContractCommandService = purchaseContractCommandService;
    }

    @PostMapping
    public ResponseEntity<PurchaseContractResource> createPurchaseContract(@RequestBody CreatePurchaseContractResource resource){
        var createPurchaseCommand = CreatePurchaseContractCommandFromResourceAssembler
                .toCommandFromResource(resource);
        var purchaseContractId = this.purchaseContractCommandService.handle(createPurchaseCommand);

        if (purchaseContractId.equals(0L)){
            return ResponseEntity.badRequest().build();
        }
        var getPurchaseContractByIdQuery = new GetContractByIdQuery(purchaseContractId);
        var optionalPurchaseContract = this.purchaseContractQueryService.handle(getPurchaseContractByIdQuery);

        var purchaseContractResource = PurchaseContractResourceFromEntityAssembler.toResourceFromEntity(optionalPurchaseContract.get());
        return new ResponseEntity<>(purchaseContractResource, HttpStatus.OK);
    }

    @GetMapping("/{purchaseContractId}")
    public ResponseEntity<List<PurchaseContractResource>> getPurchaseContractById(@PathVariable long purchaseContractId){
        var getPurchaseContractByIdQuery = new GetContractByIdQuery((purchaseContractId));
        var PurchaseContract = this.purchaseContractQueryService.handle(getPurchaseContractByIdQuery);
        var purchaseContractResources = PurchaseContract.stream()
                .map(PurchaseContractResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(purchaseContractResources);
    }

    @PutMapping("/{purchaseContractId}")
    public ResponseEntity<PurchaseContractResource>updatePurchaseContract(@PathVariable Long purchaseContractId, @RequestBody PurchaseContractResource resource){
        var updatePurchaseContractCommand = UpdatePurchaseContractCommandFromResourceAssembler.toCommandFromResource(purchaseContractId, resource);
        var optionalPurchaseContract = this.purchaseContractCommandService.handle(updatePurchaseContractCommand);

        if(optionalPurchaseContract.isEmpty())
            return ResponseEntity.badRequest().build();
        var purchaseContractResource =  PurchaseContractResourceFromEntityAssembler.toResourceFromEntity(optionalPurchaseContract.get());
        return ResponseEntity.ok(purchaseContractResource);
    }

    @DeleteMapping("/{purchaseContractId}")
    public ResponseEntity<?> deletePurchaseContract(@PathVariable Long purchaseContractId){
        var deletePurchaseContract = new DeletePurchaseContractCommand(purchaseContractId);
        this.purchaseContractCommandService.handle(deletePurchaseContract);
        return ResponseEntity.noContent().build();
    }
}
