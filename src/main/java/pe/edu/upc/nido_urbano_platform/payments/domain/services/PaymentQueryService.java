package pe.edu.upc.nido_urbano_platform.payments.domain.services;

import pe.edu.upc.nido_urbano_platform.payments.domain.model.aggregates.Payments;
import pe.edu.upc.nido_urbano_platform.payments.domain.model.queries.GetAllPaymentsQuery;
import pe.edu.upc.nido_urbano_platform.payments.domain.model.queries.GetPaymentByIdQuery;
import pe.edu.upc.nido_urbano_platform.payments.domain.model.queries.GetPaymentsByPaymentManagementIdQuery;

import java.util.List;
import java.util.Optional;

public interface PaymentQueryService {
    List<Payments> handle(GetAllPaymentsQuery query);
    Optional<Payments> handle(GetPaymentByIdQuery query);
    List<Payments> handle(GetPaymentsByPaymentManagementIdQuery query);
}
