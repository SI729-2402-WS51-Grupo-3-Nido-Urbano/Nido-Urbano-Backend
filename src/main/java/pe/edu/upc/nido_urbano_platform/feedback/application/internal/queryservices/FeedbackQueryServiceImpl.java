package pe.edu.upc.nido_urbano_platform.feedback.application.internal.queryservices;

import org.springframework.stereotype.Service;
import pe.edu.upc.nido_urbano_platform.feedback.domain.model.aggregates.Feedback;
import pe.edu.upc.nido_urbano_platform.feedback.domain.model.queries.GetAllFeedbacksByHouseNameQuery;
import pe.edu.upc.nido_urbano_platform.feedback.domain.model.queries.GetFeedbackByIdQuery;
import pe.edu.upc.nido_urbano_platform.feedback.domain.model.queries.GetAllFeedbacksByUserIdQuery;
import pe.edu.upc.nido_urbano_platform.feedback.domain.services.FeedbackQueryService;
import pe.edu.upc.nido_urbano_platform.feedback.infrastructure.persistence.jpa.repositories.FeedbackRepository;

import java.util.List;
import java.util.Optional;

@Service
public class FeedbackQueryServiceImpl implements FeedbackQueryService {
    private final FeedbackRepository feedbackRepository;

    public FeedbackQueryServiceImpl(FeedbackRepository feedbackRepository) {
        this.feedbackRepository = feedbackRepository;
    }

    @Override
    public List<Feedback> handle(GetAllFeedbacksByHouseNameQuery query) {
        return this.feedbackRepository.findByHouseName(query.houseName());
    }

    @Override
    public Optional<Feedback> handle(GetFeedbackByIdQuery query) {
        return this.feedbackRepository.findById(query.feedbackId());
    }

    @Override
    public Optional<Feedback> handle(GetAllFeedbacksByUserIdQuery query) {
        return this.feedbackRepository.findById(query.ratingUserId());
    }
}
