package pe.edu.upc.nido_urbano_platform.feedback.interfaces.rest.transform;

import pe.edu.upc.nido_urbano_platform.feedback.domain.model.aggregates.Feedback;
import pe.edu.upc.nido_urbano_platform.feedback.interfaces.rest.resources.FeedbackResource;

public class FeedbackResourceFromEntityAssembler {
    public static FeedbackResource toResourceFromEntity(Feedback entity) {
        return new FeedbackResource(entity.getId(), entity.getPropertyId().propertyId(), entity.getUserId().userId(),
                entity.getUserName(), entity.getScore().value(), entity.getComments());
    }
}
