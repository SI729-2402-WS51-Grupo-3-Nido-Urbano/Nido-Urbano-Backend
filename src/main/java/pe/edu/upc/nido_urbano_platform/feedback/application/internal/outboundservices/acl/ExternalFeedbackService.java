package pe.edu.upc.nido_urbano_platform.feedback.application.internal.outboundservices.acl;

import pe.edu.upc.nido_urbano_platform.feedback.domain.model.valueobjects.FeedbackId;
import pe.edu.upc.nido_urbano_platform.feedback.domain.model.valueobjects.Score;
import pe.edu.upc.nido_urbano_platform.feedback.interfaces.acl.FeedbacksContextFacade;

import java.util.Date;
import java.util.Optional;

public class ExternalFeedbackService {
    private final FeedbacksContextFacade feedbacksContextFacade;

    public ExternalFeedbackService(FeedbacksContextFacade feedbacksContextFacade) {
        this.feedbacksContextFacade = feedbacksContextFacade;
    }

    public Optional<FeedbackId> createFeedback(Long propertyId, Long userId, int score, String comments, Date ratingDate) {
        var feedbackId = feedbacksContextFacade.createFeedback(propertyId, userId, score, comments, ratingDate);
        if (feedbackId.equals(0L))
            return Optional.empty();
        return Optional.of(new FeedbackId(feedbackId));
    }


    public Optional<FeedbackId> updateFeedback(Long id, Long propertyId, Long userId, int score, String comments, Date ratingDate) {
        var feedbackIdUpdated = feedbacksContextFacade.updateFeedback(id, propertyId, userId, score, comments, ratingDate);
        if (feedbackIdUpdated.equals(0L))
            return Optional.empty();
        return Optional.of(new FeedbackId(feedbackIdUpdated));
    }

    public void deleteFeedback(Long feedbackId) {
        feedbacksContextFacade.deleteFeedback(feedbackId);
    }


}
