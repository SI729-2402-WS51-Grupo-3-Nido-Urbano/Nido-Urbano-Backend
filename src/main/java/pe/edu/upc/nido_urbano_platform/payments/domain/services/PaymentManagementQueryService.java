package pe.edu.upc.nido_urbano_platform.payments.domain.services;

import pe.edu.upc.nido_urbano_platform.payments.domain.model.aggregates.PaymentManagement;
import pe.edu.upc.nido_urbano_platform.payments.domain.model.aggregates.Payments;
import pe.edu.upc.nido_urbano_platform.payments.domain.model.queries.GetAllPaymentManagementsQuery;
import pe.edu.upc.nido_urbano_platform.payments.domain.model.queries.GetPaymentManagementByContractIdQuery;
import pe.edu.upc.nido_urbano_platform.payments.domain.model.queries.GetPaymentManagementByIdQuery;
import pe.edu.upc.nido_urbano_platform.payments.domain.model.queries.GetPaymentsByPaymentManagementIdQuery;

import java.util.List;
import java.util.Optional;

public interface PaymentManagementQueryService {
    List<PaymentManagement> handle(GetAllPaymentManagementsQuery query);
    Optional<PaymentManagement> handle(GetPaymentManagementByIdQuery query);
    Optional<PaymentManagement> handle(GetPaymentManagementByContractIdQuery query);
}
