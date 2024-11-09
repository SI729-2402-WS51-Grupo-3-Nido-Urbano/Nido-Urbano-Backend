package pe.edu.upc.nido_urbano_platform.feedback.domain.model.commands;

import pe.edu.upc.nido_urbano_platform.feedback.domain.model.valueobjects.Score;

import java.util.Date;

public record UpdateFeedbackCommand(Long Id, Long propertyId, Long userId, int score, String comments, Date ratingDate) {
}
