package pe.edu.upc.nido_urbano_platform.payments.interfaces.rest;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.nido_urbano_platform.payments.application.internal.commandservices.TransactionsCommandServiceImpl;
import pe.edu.upc.nido_urbano_platform.payments.application.internal.queryservices.TransactionsQueryServiceImpl;
import pe.edu.upc.nido_urbano_platform.payments.domain.model.commands.DeleteTransactionCommand;
import pe.edu.upc.nido_urbano_platform.payments.domain.model.commands.UpdateTransactionCommand;
import pe.edu.upc.nido_urbano_platform.payments.domain.model.queries.GetAllTransactionsQuery;
import pe.edu.upc.nido_urbano_platform.payments.domain.model.queries.GetTransactionByIdQuery;
import pe.edu.upc.nido_urbano_platform.payments.domain.model.queries.GetTransactionsByPaymentIdQuery;
import pe.edu.upc.nido_urbano_platform.payments.interfaces.rest.resources.CreateTransactionResource;
import pe.edu.upc.nido_urbano_platform.payments.interfaces.rest.resources.TransactionResource;
import pe.edu.upc.nido_urbano_platform.payments.interfaces.rest.transform.CreateTransactionCommandFromResourceAssembler;
import pe.edu.upc.nido_urbano_platform.payments.interfaces.rest.transform.TransactionResourceFromEntityAssembler;
import pe.edu.upc.nido_urbano_platform.payments.interfaces.rest.transform.UpdateTransactionCommandFromResourceAssembler;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", methods = { RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT, RequestMethod.DELETE })
@RestController
@RequestMapping(value = "/api/v1/transactions", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Transactions", description = "Transactions Management Endpoints")
public class TransactionController {
    private final TransactionsCommandServiceImpl transactionsCommandService;
    private final TransactionsQueryServiceImpl transactionsQueryService;

    public TransactionController(TransactionsCommandServiceImpl transactionsCommandService,
                                 TransactionsQueryServiceImpl transactionsQueryService) {
        this.transactionsCommandService = transactionsCommandService;
        this.transactionsQueryService = transactionsQueryService;
    }

    @PostMapping
    public ResponseEntity<TransactionResource> createTransaction(@RequestBody CreateTransactionResource resource) {
        var createTransactionCommand = CreateTransactionCommandFromResourceAssembler.toCommandFromResource(resource);
        var transactionId = this.transactionsCommandService.handle(createTransactionCommand);

        if (transactionId.equals(0L)) {
            return ResponseEntity.badRequest().build();
        }

        var getTransactionByIdQuery = new GetTransactionByIdQuery(transactionId);
        var optionalTransaction = this.transactionsQueryService.handle(getTransactionByIdQuery);

        var transactionResource = TransactionResourceFromEntityAssembler.toResourceFromEntity(optionalTransaction.get());
        return new ResponseEntity<>(transactionResource, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<TransactionResource>> getAllTransactions() {
        var getAllTransactionsQuery = new GetAllTransactionsQuery();
        var transactions = this.transactionsQueryService.handle(getAllTransactionsQuery);
        var transactionResources = transactions.stream()
                .map(TransactionResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(transactionResources);
    }

    @GetMapping("/{transactionId}")
    public ResponseEntity<TransactionResource> getTransactionById(@PathVariable Long transactionId) {
        var getTransactionByIdQuery = new GetTransactionByIdQuery(transactionId);
        var optionalTransaction = this.transactionsQueryService.handle(getTransactionByIdQuery);
        if (optionalTransaction.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        var transactionResource = TransactionResourceFromEntityAssembler.toResourceFromEntity(optionalTransaction.get());
        return ResponseEntity.ok(transactionResource);
    }

    @GetMapping("/payment/{paymentId}")
    public ResponseEntity<List<TransactionResource>> getTransactionByPaymentId(@PathVariable Long paymentId) {
        var getTransactionsByPaymentIdQuery = new GetTransactionsByPaymentIdQuery(paymentId);
        var transaction = this.transactionsQueryService.handle(getTransactionsByPaymentIdQuery);
        var transactionResources = transaction.stream()
                .map(TransactionResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(transactionResources);
    }

    @PutMapping("/{transactionId}")
    public ResponseEntity<TransactionResource> updateTransaction(@PathVariable Long transactionId,
                                                                 @RequestBody TransactionResource resource) {
        var updateTransactionCommand = UpdateTransactionCommandFromResourceAssembler
                .toCommandFromResource(transactionId, resource);
        var optionalTransaction = this.transactionsCommandService.handle(updateTransactionCommand);

        if (optionalTransaction.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        var transactionResource = TransactionResourceFromEntityAssembler.toResourceFromEntity(optionalTransaction.get());
        return ResponseEntity.ok(transactionResource);
    }

    @DeleteMapping("/{transactionId}")
    public ResponseEntity<?> deleteTransaction(@PathVariable Long transactionId) {
        var deleteTransactionCommand = new DeleteTransactionCommand(transactionId);
        this.transactionsCommandService.handle(deleteTransactionCommand);
        return ResponseEntity.ok().build();
    }
}
