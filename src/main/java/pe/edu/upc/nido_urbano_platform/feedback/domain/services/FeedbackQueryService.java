package pe.edu.upc.nido_urbano_platform.feedback.domain.services;

import pe.edu.upc.nido_urbano_platform.feedback.domain.model.aggregates.Feedback;
import pe.edu.upc.nido_urbano_platform.feedback.domain.model.queries.GetAllFeedbacksByPropertyIdQuery;
import pe.edu.upc.nido_urbano_platform.feedback.domain.model.queries.GetFeedbackByIdQuery;
import pe.edu.upc.nido_urbano_platform.feedback.domain.model.queries.GetAllFeedbacksByUserIdQuery;

import java.util.List;
import java.util.Optional;

public interface FeedbackQueryService {
    List<Feedback> handle(GetAllFeedbacksByPropertyIdQuery query);
    Optional<Feedback> handle(GetAllFeedbacksByUserIdQuery query);
    Optional<Feedback> handle(GetFeedbackByIdQuery getFeedbackByIdQuery);
}

