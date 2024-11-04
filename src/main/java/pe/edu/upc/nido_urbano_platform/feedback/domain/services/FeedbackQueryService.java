package pe.edu.upc.nido_urbano_platform.feedback.domain.services;

import pe.edu.upc.nido_urbano_platform.feedback.domain.model.aggregates.Feedback;
import pe.edu.upc.nido_urbano_platform.feedback.domain.model.queries.GetAllFeedbacksByHouseNameQuery;
import pe.edu.upc.nido_urbano_platform.feedback.domain.model.queries.GetFeedbackByIdQuery;
import pe.edu.upc.nido_urbano_platform.feedback.domain.model.queries.GetAllFeedbacksByUserIdQuery;

import java.util.List;
import java.util.Optional;

public interface FeedbackQueryService {
    List<Feedback> handle(GetAllFeedbacksByHouseNameQuery query);
    Optional<Feedback> handle(GetAllFeedbacksByUserIdQuery query);
    Optional<Feedback> handle(GetFeedbackByIdQuery getFeedbackByIdQuery);
}

