package pe.edu.upc.nido_urbano_platform.feedback.domain.model.commands;

import pe.edu.upc.nido_urbano_platform.feedback.domain.model.valueobjects.Score;

import java.util.Date;

public record CreateFeedbackCommand(Long propertyId, Long userId, Score score, String comments,
                                    Date ratingDate) {
}
