package pe.edu.upc.nido_urbano_platform.feedback.interfaces.rest.resources;

import pe.edu.upc.nido_urbano_platform.feedback.domain.model.valueobjects.Score;

import java.util.Date;

public record CreateFeedbackResource(Long propertyId, Long userId, int score, String comments, Date ratingDate) {
}
