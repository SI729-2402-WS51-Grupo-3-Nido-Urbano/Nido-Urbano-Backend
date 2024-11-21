package pe.edu.upc.nido_urbano_platform.payments.interfaces.rest;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.nido_urbano_platform.payments.application.internal.commandservices.PaymentManagementCommandServiceImpl;
import pe.edu.upc.nido_urbano_platform.payments.application.internal.queryservices.PaymentManagementQueryServiceImpl;
import pe.edu.upc.nido_urbano_platform.payments.domain.model.commands.DeletePaymentManagementCommand;
import pe.edu.upc.nido_urbano_platform.payments.domain.model.queries.GetAllPaymentManagementsQuery;
import pe.edu.upc.nido_urbano_platform.payments.domain.model.queries.GetPaymentManagementByIdQuery;
import pe.edu.upc.nido_urbano_platform.payments.interfaces.rest.resources.CreatePaymentManagementResource;
import pe.edu.upc.nido_urbano_platform.payments.interfaces.rest.resources.PaymentManagementResource;
import pe.edu.upc.nido_urbano_platform.payments.interfaces.rest.transform.CreatePaymentManagementCommandFromResourceAssembler;
import pe.edu.upc.nido_urbano_platform.payments.interfaces.rest.transform.PaymentManagementResourceFromEntityAssembler;
import pe.edu.upc.nido_urbano_platform.payments.interfaces.rest.transform.UpdatePaymentManagementCommandFromResourceAssembler;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", methods = { RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT, RequestMethod.DELETE })
@RestController
@RequestMapping(value = "/api/v1/paymentManagements", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "PaymentManagements", description = "Payment Management Endpoints")
public class PaymentManagementController {
    private final PaymentManagementCommandServiceImpl paymentManagementCommandService;
    private final PaymentManagementQueryServiceImpl paymentManagementQueryService;

    public PaymentManagementController(PaymentManagementCommandServiceImpl paymentManagementCommandService,
                                       PaymentManagementQueryServiceImpl paymentManagementQueryService) {
        this.paymentManagementCommandService = paymentManagementCommandService;
        this.paymentManagementQueryService = paymentManagementQueryService;
    }

    @PostMapping
    public ResponseEntity<PaymentManagementResource> createPaymentManagement(
            @RequestBody CreatePaymentManagementResource resource) {
        var createPaymentManagementCommand = CreatePaymentManagementCommandFromResourceAssembler
                .toCommandFromResource(resource);
        var paymentManagementId = this.paymentManagementCommandService.handle(createPaymentManagementCommand);

        if (paymentManagementId.equals(0L)) {
            return ResponseEntity.badRequest().build();
        }

        var getPaymentManagementByIdQuery = new GetPaymentManagementByIdQuery(paymentManagementId);
        var optionalPaymentManagement = this.paymentManagementQueryService.handle(getPaymentManagementByIdQuery);

        var paymentManagementResource = PaymentManagementResourceFromEntityAssembler.
                toResourceFromEntity(optionalPaymentManagement.get());
        return new ResponseEntity<>(paymentManagementResource, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<PaymentManagementResource>> getAllPaymentManagements() {
        var getAllPaymentManagementsQuery = new GetAllPaymentManagementsQuery();
        var paymentManagements = this.paymentManagementQueryService.handle(getAllPaymentManagementsQuery);
        var paymentManagementResources = paymentManagements.stream()
                .map(PaymentManagementResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(paymentManagementResources);
    }

    @GetMapping("/{paymentManagementId}")
    public ResponseEntity<PaymentManagementResource> getPaymentManagementById(@PathVariable Long paymentManagementId) {
        var getPaymentManagementByIdQuery = new GetPaymentManagementByIdQuery(paymentManagementId);
        var optionalPaymentManagement = this.paymentManagementQueryService.handle(getPaymentManagementByIdQuery);
        if (optionalPaymentManagement.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        var paymentManagementResource = PaymentManagementResourceFromEntityAssembler.toResourceFromEntity(optionalPaymentManagement.get());
        return ResponseEntity.ok(paymentManagementResource);
    }

    @PutMapping("/{paymentManagementId}")
    public ResponseEntity<PaymentManagementResource> updatePaymentManagement(@PathVariable Long paymentManagementId,
                                                                             @RequestBody PaymentManagementResource resource) {
        var updatePaymentManagementCommand = UpdatePaymentManagementCommandFromResourceAssembler
                .toCommandFromResource(paymentManagementId, resource);
        var optionalPaymentManagement = this.paymentManagementCommandService.handle(updatePaymentManagementCommand);

        if (optionalPaymentManagement.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        var paymentManagementResource = PaymentManagementResourceFromEntityAssembler.toResourceFromEntity(optionalPaymentManagement.get());
        return ResponseEntity.ok(paymentManagementResource);
    }

    @DeleteMapping("/{paymentManagementId}")
    public ResponseEntity<PaymentManagementResource> deletePaymentManagement(@PathVariable Long paymentManagementId) {
        var deletePaymentManagementCommand = new DeletePaymentManagementCommand(paymentManagementId);
        this.paymentManagementCommandService.handle(deletePaymentManagementCommand);
        return ResponseEntity.noContent().build();
    }
}
