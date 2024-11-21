package pe.edu.upc.nido_urbano_platform.payments.interfaces.rest;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.nido_urbano_platform.payments.application.internal.commandservices.PaymentCommandServiceImpl;
import pe.edu.upc.nido_urbano_platform.payments.application.internal.queryservices.PaymentQueryServiceImpl;
import pe.edu.upc.nido_urbano_platform.payments.domain.model.commands.DeletePaymentCommand;
import pe.edu.upc.nido_urbano_platform.payments.domain.model.queries.GetAllPaymentsQuery;
import pe.edu.upc.nido_urbano_platform.payments.domain.model.queries.GetPaymentByIdQuery;
import pe.edu.upc.nido_urbano_platform.payments.domain.model.queries.GetPaymentsByPaymentManagementIdQuery;
import pe.edu.upc.nido_urbano_platform.payments.interfaces.rest.resources.CreatePaymentsResource;
import pe.edu.upc.nido_urbano_platform.payments.interfaces.rest.resources.PaymentsResource;
import pe.edu.upc.nido_urbano_platform.payments.interfaces.rest.transform.CreatePaymentCommandFromResourceAssembler;
import pe.edu.upc.nido_urbano_platform.payments.interfaces.rest.transform.PaymentResourceFromEntityAssembler;
import pe.edu.upc.nido_urbano_platform.payments.interfaces.rest.transform.UpdatePaymentCommandFromResourceAssembler;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", methods = { RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT, RequestMethod.DELETE })
@RestController
@RequestMapping(value = "/api/v1/payments", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Payments", description = "Payments Endpoints")
public class PaymentController {
    private final PaymentQueryServiceImpl paymentQueryService;
    private final PaymentCommandServiceImpl paymentCommandService;

    public PaymentController(PaymentQueryServiceImpl paymentQueryService, PaymentCommandServiceImpl paymentCommandService) {
        this.paymentQueryService = paymentQueryService;
        this.paymentCommandService = paymentCommandService;
    }

    @PostMapping
    public ResponseEntity<PaymentsResource> createPayment(@RequestBody CreatePaymentsResource resource) {
        var createPaymentCommand = CreatePaymentCommandFromResourceAssembler.toCommandFromResource(resource);
        var paymentId = this.paymentCommandService.handle(createPaymentCommand);

        if (paymentId.equals(0L)) {
            return ResponseEntity.badRequest().build();
        }

        var getPaymentByIdQuery = new GetPaymentByIdQuery(paymentId);
        var optionalPayment = this.paymentQueryService.handle(getPaymentByIdQuery);

        var paymentResource = PaymentResourceFromEntityAssembler.toResourceFromEntity(optionalPayment.get());
        return new ResponseEntity<>(paymentResource, HttpStatus.CREATED);
    }


    @GetMapping
    public ResponseEntity<List<PaymentsResource>> getAllPayments() {
        var getAllPaymentsQuery = new GetAllPaymentsQuery();
        var payments = this.paymentQueryService.handle(getAllPaymentsQuery);
        var paymentsResources = payments.stream()
                .map(PaymentResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(paymentsResources);
    }

    @GetMapping("/{paymentId}")
    public ResponseEntity<PaymentsResource> getPaymentById(@PathVariable Long paymentId) {
        var getPaymentByIdQuery = new GetPaymentByIdQuery(paymentId);
        var optionalPayment = this.paymentQueryService.handle(getPaymentByIdQuery);
        if (optionalPayment.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        var paymentResource = PaymentResourceFromEntityAssembler.toResourceFromEntity(optionalPayment.get());
        return ResponseEntity.ok(paymentResource);
    }

    @GetMapping("/paymentManagement/{paymentManagementId}")
    public ResponseEntity<List<PaymentsResource>> getPaymentsByPaymentManagementId(
            @PathVariable Long paymentManagementId) {
        var getPaymentsByPaymentManagementIdQuery = new GetPaymentsByPaymentManagementIdQuery(paymentManagementId);
        var payments = this.paymentQueryService.handle(getPaymentsByPaymentManagementIdQuery);
        var paymentsResources = payments.stream()
                .map(PaymentResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(paymentsResources);
    }

    @PutMapping("/{paymentId}")
    public ResponseEntity<PaymentsResource> updatePayment(@PathVariable Long paymentId,
                                                          @RequestBody PaymentsResource resource) {
        var updatePaymentCommand = UpdatePaymentCommandFromResourceAssembler.toCommandFromResource(paymentId, resource);
        var optionalPayment = this.paymentCommandService.handle(updatePaymentCommand);

        if (optionalPayment.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        var paymentResource = PaymentResourceFromEntityAssembler.toResourceFromEntity(optionalPayment.get());
        return ResponseEntity.ok(paymentResource);
    }

    @DeleteMapping("/{paymentId}")
    public ResponseEntity<?> deletePayment(@PathVariable Long paymentId) {
        var deletePaymentCommand = new DeletePaymentCommand(paymentId);
        this.paymentCommandService.handle(deletePaymentCommand);
        return ResponseEntity.noContent().build();
    }
}
