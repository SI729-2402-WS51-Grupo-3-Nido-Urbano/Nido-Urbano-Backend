package pe.edu.upc.nido_urbano_platform.feedback.interfaces.rest.resources;

import java.util.Date;

public record FeedbackResource(Long id, Long propertyId, Long userId, String userName,
                               int score,
                               String comments) {
}
