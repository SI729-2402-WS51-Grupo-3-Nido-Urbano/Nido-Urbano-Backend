package pe.edu.upc.nido_urbano_platform.feedback.infrastructure.persistence.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.upc.nido_urbano_platform.feedback.domain.model.aggregates.Feedback;

import java.util.List;
import java.util.Optional;

@Repository
public interface FeedbackRepository extends JpaRepository<Feedback, Long> {
    List<Feedback> findByHouseName(String houseName);
}
