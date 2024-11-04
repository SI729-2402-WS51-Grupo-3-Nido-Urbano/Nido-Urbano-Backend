package pe.edu.upc.nido_urbano_platform.feedback.interfaces.rest.transform;

import pe.edu.upc.nido_urbano_platform.feedback.domain.model.aggregates.Feedback;
import pe.edu.upc.nido_urbano_platform.feedback.interfaces.rest.resources.FeedbackResource;

public class FeedbackResourceFromEntityAssembler {
    public static FeedbackResource toResourceFromEntity(Feedback entity) {
        return new FeedbackResource(entity.getId(), entity.getHouseName(), entity.getAddress(), entity.getRatedUserId(),
                entity.getRatingUserId(), entity.getScore(), entity.getComments(), entity.getRatingDate());
    }
}
