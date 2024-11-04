package pe.edu.upc.nido_urbano_platform.house.infrastructure.persistence.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.upc.nido_urbano_platform.house.domain.model.entities.Verification;

import java.util.Optional;
@Repository
public interface VerificationRepository extends JpaRepository<Verification,Long> {
    boolean existsByUniqueRegistrationNumber(String uniqueRegistrationNumber);
    boolean existsByUniqueRegistrationNumberAndIdIsNot(String uniqueRegistrationNumber, Long id);
    //Optional<Verification> findById(Verification verificationId);

}
