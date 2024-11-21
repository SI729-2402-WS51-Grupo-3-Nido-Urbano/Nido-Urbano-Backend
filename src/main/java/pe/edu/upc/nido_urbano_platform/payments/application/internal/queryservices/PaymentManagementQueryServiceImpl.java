package pe.edu.upc.nido_urbano_platform.payments.application.internal.queryservices;

import org.springframework.stereotype.Service;
import pe.edu.upc.nido_urbano_platform.payments.domain.model.aggregates.PaymentManagement;
import pe.edu.upc.nido_urbano_platform.payments.domain.model.aggregates.Payments;
import pe.edu.upc.nido_urbano_platform.payments.domain.model.queries.GetAllPaymentManagementsQuery;
import pe.edu.upc.nido_urbano_platform.payments.domain.model.queries.GetPaymentManagementByContractIdQuery;
import pe.edu.upc.nido_urbano_platform.payments.domain.model.queries.GetPaymentManagementByIdQuery;
import pe.edu.upc.nido_urbano_platform.payments.domain.model.queries.GetPaymentsByPaymentManagementIdQuery;
import pe.edu.upc.nido_urbano_platform.payments.domain.model.valueobjects.ContractId;
import pe.edu.upc.nido_urbano_platform.payments.domain.services.PaymentManagementQueryService;
import pe.edu.upc.nido_urbano_platform.payments.infrastructure.persistence.jpa.repositories.PaymentManagementRepository;

import java.util.List;
import java.util.Optional;

@Service
public class PaymentManagementQueryServiceImpl implements PaymentManagementQueryService {
    private final PaymentManagementRepository paymentManagementRepository;

    public PaymentManagementQueryServiceImpl(PaymentManagementRepository paymentManagementRepository) {
        this.paymentManagementRepository = paymentManagementRepository;
    }

    @Override
    public List<PaymentManagement> handle(GetAllPaymentManagementsQuery query) {
        return this.paymentManagementRepository.findAll();
    }

    @Override
    public Optional<PaymentManagement> handle(GetPaymentManagementByIdQuery query) {
        return this.paymentManagementRepository.findById(query.paymentManagementId());
    }

    @Override
    public Optional<PaymentManagement> handle(GetPaymentManagementByContractIdQuery query) {
        return this.paymentManagementRepository.findByContractId(new ContractId(query.contractId()));
    }
}
