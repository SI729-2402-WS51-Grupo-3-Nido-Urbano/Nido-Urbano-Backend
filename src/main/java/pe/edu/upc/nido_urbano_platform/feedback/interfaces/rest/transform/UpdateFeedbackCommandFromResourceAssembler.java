package pe.edu.upc.nido_urbano_platform.feedback.interfaces.rest.transform;

import pe.edu.upc.nido_urbano_platform.feedback.domain.model.commands.UpdateFeedbackCommand;
import pe.edu.upc.nido_urbano_platform.feedback.domain.model.valueobjects.PropertyId;
import pe.edu.upc.nido_urbano_platform.feedback.interfaces.rest.resources.FeedbackResource;

public class UpdateFeedbackCommandFromResourceAssembler {
    public static UpdateFeedbackCommand toCommandFromResource(Long feedbackId, FeedbackResource resource) {
        return new UpdateFeedbackCommand(feedbackId, resource.propertyId(), resource.userId(),
                resource.userName(), resource.score(), resource.comments());
    }
}
