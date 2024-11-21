package pe.edu.upc.nido_urbano_platform.payments.application.internal.queryservices;

import org.springframework.stereotype.Service;
import pe.edu.upc.nido_urbano_platform.payments.domain.model.aggregates.PaymentManagement;
import pe.edu.upc.nido_urbano_platform.payments.domain.model.aggregates.Payments;
import pe.edu.upc.nido_urbano_platform.payments.domain.model.queries.GetAllPaymentsQuery;
import pe.edu.upc.nido_urbano_platform.payments.domain.model.queries.GetPaymentByIdQuery;
import pe.edu.upc.nido_urbano_platform.payments.domain.model.queries.GetPaymentsByPaymentManagementIdQuery;
import pe.edu.upc.nido_urbano_platform.payments.domain.services.PaymentQueryService;
import pe.edu.upc.nido_urbano_platform.payments.infrastructure.persistence.jpa.repositories.PaymentManagementRepository;
import pe.edu.upc.nido_urbano_platform.payments.infrastructure.persistence.jpa.repositories.PaymentRepository;

import java.util.List;
import java.util.Optional;

@Service
public class PaymentQueryServiceImpl implements PaymentQueryService {
    private final PaymentRepository paymentRepository;
    private final PaymentManagementRepository paymentManagementRepository;

    public PaymentQueryServiceImpl(PaymentRepository paymentRepository, PaymentManagementRepository paymentManagementRepository) {
        this.paymentRepository = paymentRepository;
        this.paymentManagementRepository = paymentManagementRepository;
    }

    @Override
    public List<Payments> handle(GetAllPaymentsQuery query) {
        return this.paymentRepository.findAll();
    }

    @Override
    public Optional<Payments> handle(GetPaymentByIdQuery query) {
        return this.paymentRepository.findById(query.paymentId());
    }

    @Override
    public List<Payments> handle(GetPaymentsByPaymentManagementIdQuery query) {
        var optionalPaymentManagement = this.paymentManagementRepository.findById(query.paymentManagementId());
        if (optionalPaymentManagement.isEmpty()) {
            throw new IllegalArgumentException("Payment management not found with id " + query.paymentManagementId());
        }
        return this.paymentRepository.findByPaymentManagement(optionalPaymentManagement.get());
    }
}
