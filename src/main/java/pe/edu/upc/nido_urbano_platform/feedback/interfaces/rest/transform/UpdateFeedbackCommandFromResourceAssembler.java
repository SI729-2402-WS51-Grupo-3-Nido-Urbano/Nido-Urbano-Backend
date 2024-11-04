package pe.edu.upc.nido_urbano_platform.feedback.interfaces.rest.transform;

import pe.edu.upc.nido_urbano_platform.feedback.domain.model.commands.UpdateFeedbackCommand;
import pe.edu.upc.nido_urbano_platform.feedback.interfaces.rest.resources.FeedbackResource;

public class UpdateFeedbackCommandFromResourceAssembler {
    public static UpdateFeedbackCommand toCommandFromResource(Long profileId, FeedbackResource resource) {
        return new UpdateFeedbackCommand(profileId, resource.houseName(), resource.address(),
                resource.ratedUserId(), resource.ratingUserId(), resource.score(),
                resource.comments(), resource.ratingDate());
    }
}
