package pe.edu.upc.nido_urbano_platform.feedback.application.internal.commandservices;

import org.springframework.stereotype.Service;
import pe.edu.upc.nido_urbano_platform.feedback.domain.model.aggregates.Feedback;
import pe.edu.upc.nido_urbano_platform.feedback.domain.model.commands.CreateFeedbackCommand;
import pe.edu.upc.nido_urbano_platform.feedback.domain.model.commands.DeleteFeedbackCommand;
import pe.edu.upc.nido_urbano_platform.feedback.domain.model.commands.UpdateFeedbackCommand;
import pe.edu.upc.nido_urbano_platform.feedback.domain.model.valueobjects.Score;
import pe.edu.upc.nido_urbano_platform.feedback.domain.services.FeedbackCommandService;
import pe.edu.upc.nido_urbano_platform.feedback.infrastructure.persistence.jpa.repositories.FeedbackRepository;

import java.util.Optional;

@Service
public class FeedbackCommandServiceImpl implements FeedbackCommandService {

    private final FeedbackRepository feedbackRepository;

    public FeedbackCommandServiceImpl(FeedbackRepository feedbackRepository) {
        this.feedbackRepository = feedbackRepository;
    }

    @Override
    public Long handle(CreateFeedbackCommand command) {
        var feedback = new Feedback(command);
        try {
            this.feedbackRepository.save(feedback);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while saving feedback: " + e.getMessage());
        }
        return feedback.getId();
    }

    @Override
    public Optional<Feedback> handle(UpdateFeedbackCommand command) {
        var feedbackId = command.Id();
        // If the feedback does not exist, throw an exception
        if (!this.feedbackRepository.existsById(feedbackId)) {
            throw new IllegalArgumentException("Feedback with id " + feedbackId + " does not exist");
        }

        var feedbackToUpdate = this.feedbackRepository.findById(feedbackId).get();
        feedbackToUpdate.updateFeedback(command.score(), command.comments(), command.ratingDate());

        try {
            var updatedFeedback = this.feedbackRepository.save(feedbackToUpdate);
            return Optional.of(updatedFeedback);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while updating feedback: " + e.getMessage());
        }
    }

    @Override
    public void handle(DeleteFeedbackCommand command) {
        // If the feedback does not exist, throw an exception
        if (!this.feedbackRepository.existsById(command.feedbackId())) {
            throw new IllegalArgumentException("Feedback with id " + command.feedbackId() + " does not exist");
        }

        // Try to delete the feedback, if an error occurs, throw an exception
        try {
            this.feedbackRepository.deleteById(command.feedbackId());
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while deleting feedback: " + e.getMessage());
        }

    }
}
