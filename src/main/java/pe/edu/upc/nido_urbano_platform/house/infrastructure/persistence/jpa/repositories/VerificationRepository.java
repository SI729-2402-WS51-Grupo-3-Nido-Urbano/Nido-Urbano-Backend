package pe.edu.upc.nido_urbano_platform.house.infrastructure.persistence.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.upc.nido_urbano_platform.house.domain.model.entities.Verification;

import java.util.Optional;

public interface VerificationRepository extends JpaRepository<Verification,Long> {
    boolean existsByUniqueRegistrationNumber(String uniqueRegistrationNumber);
    Optional<Verification> findByVerificationId(Verification verificationId);

}
