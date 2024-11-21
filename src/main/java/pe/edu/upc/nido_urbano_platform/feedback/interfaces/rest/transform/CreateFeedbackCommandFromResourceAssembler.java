package pe.edu.upc.nido_urbano_platform.feedback.interfaces.rest.transform;

import pe.edu.upc.nido_urbano_platform.feedback.domain.model.commands.CreateFeedbackCommand;
import pe.edu.upc.nido_urbano_platform.feedback.interfaces.rest.resources.CreateFeedbackResource;

public class CreateFeedbackCommandFromResourceAssembler {
    public static CreateFeedbackCommand toCommandFromResource(CreateFeedbackResource resource) {
        return new CreateFeedbackCommand(resource.propertyId(),
                resource.userId(), resource.userName(), resource.score(),
                resource.comments());
    }
}
