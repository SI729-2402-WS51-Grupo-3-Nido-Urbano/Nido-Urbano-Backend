package pe.edu.upc.nido_urbano_platform.feedback.interfaces.acl;

import org.springframework.stereotype.Service;
import pe.edu.upc.nido_urbano_platform.feedback.domain.model.commands.CreateFeedbackCommand;
import pe.edu.upc.nido_urbano_platform.feedback.domain.model.commands.DeleteFeedbackCommand;
import pe.edu.upc.nido_urbano_platform.feedback.domain.model.commands.UpdateFeedbackCommand;
import pe.edu.upc.nido_urbano_platform.feedback.domain.model.valueobjects.Score;
import pe.edu.upc.nido_urbano_platform.feedback.domain.services.FeedbackCommandService;
import pe.edu.upc.nido_urbano_platform.feedback.domain.services.FeedbackQueryService;

import java.util.Date;

@Service
public class FeedbacksContextFacade {
    private final FeedbackCommandService feedbackCommandService;
    private final FeedbackQueryService feedbackQueryService;

    public FeedbacksContextFacade(FeedbackCommandService feedbackCommandService, FeedbackQueryService feedbackQueryService) {
        this.feedbackCommandService = feedbackCommandService;
        this.feedbackQueryService = feedbackQueryService;
    }


    public Long createFeedback(Long propertyId, Long userId, int score, String comments, Date ratingDate) {
        var CreateFeedbackCommand = new CreateFeedbackCommand(propertyId, userId, score, comments, ratingDate);
        var profileId = feedbackCommandService.handle(CreateFeedbackCommand);
        if (profileId.equals(null)) {
            return 0L;
        }
        return profileId;
    }


    public Long updateFeedback(Long id,Long propertyId, Long userId, int score, String comments, Date ratingDate) {
        var updateFeedbackCommand = new UpdateFeedbackCommand(id, propertyId, userId, score, comments, ratingDate);
        var optionalFeedback = feedbackCommandService.handle(updateFeedbackCommand);
        if (optionalFeedback.isEmpty()) {
            return 0L;
        }
        return optionalFeedback.get().getId();
    }

    public void deleteFeedback(Long feedbackId) {
        var deleteFeedbackCommand = new DeleteFeedbackCommand(feedbackId);
        feedbackCommandService.handle(deleteFeedbackCommand);
    }



}
