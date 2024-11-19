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

    public Optional<FeedbackId> createFeedback(Long propertyId, Long userId, String userName, int score, String comments) {
        var feedbackId = feedbacksContextFacade.createFeedback(propertyId, userId, userName, score, comments);
        if (feedbackId.equals(0L))
            return Optional.empty();
        return Optional.of(new FeedbackId(feedbackId));
    }


    public Optional<FeedbackId> updateFeedback(Long id, Long propertyId, Long userId, String userName, int score, String comments) {
        var feedbackIdUpdated = feedbacksContextFacade.updateFeedback(id, propertyId, userId, userName, score, comments);
        if (feedbackIdUpdated.equals(0L))
            return Optional.empty();
        return Optional.of(new FeedbackId(feedbackIdUpdated));
    }

    public void deleteFeedback(Long feedbackId) {
        feedbacksContextFacade.deleteFeedback(feedbackId);
    }


}
