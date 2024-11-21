package pe.edu.upc.nido_urbano_platform.feedback.domain.services;

import pe.edu.upc.nido_urbano_platform.feedback.domain.model.aggregates.Feedback;
import pe.edu.upc.nido_urbano_platform.feedback.domain.model.commands.CreateFeedbackCommand;
import pe.edu.upc.nido_urbano_platform.feedback.domain.model.commands.DeleteFeedbackCommand;
import pe.edu.upc.nido_urbano_platform.feedback.domain.model.commands.UpdateFeedbackCommand;

import java.util.Optional;

public interface FeedbackCommandService {
    Long handle(CreateFeedbackCommand command);
    Optional<Feedback> handle(UpdateFeedbackCommand command);
    void handle(DeleteFeedbackCommand command);
}
