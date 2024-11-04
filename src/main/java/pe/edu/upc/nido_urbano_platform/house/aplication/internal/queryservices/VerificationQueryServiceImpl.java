package pe.edu.upc.nido_urbano_platform.house.aplication.internal.queryservices;

import org.springframework.stereotype.Service;
import pe.edu.upc.nido_urbano_platform.house.domain.model.entities.Verification;
import pe.edu.upc.nido_urbano_platform.house.domain.model.queries.GetAllVerificationQuery;
import pe.edu.upc.nido_urbano_platform.house.domain.model.queries.GetVerificationByIdQuery;
import pe.edu.upc.nido_urbano_platform.house.domain.services.VerificationQueryService;
import pe.edu.upc.nido_urbano_platform.house.infrastructure.persistence.jpa.repositories.VerificationRepository;

import java.util.List;
import java.util.Optional;

@Service
public class VerificationQueryServiceImpl implements VerificationQueryService {

    private final VerificationRepository verificationRepository;

    public VerificationQueryServiceImpl(VerificationRepository verificationRepository) {
        this.verificationRepository = verificationRepository;
    }

    @Override
    public List<Verification> handle(GetAllVerificationQuery query) {
        return this.verificationRepository.findAll();
    }

    @Override
    public Optional<Verification> handle(GetVerificationByIdQuery query) {
        return this.verificationRepository.findById(query.verificationId());
    }
}
