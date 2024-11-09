package pe.edu.upc.nido_urbano_platform.feedback.domain.model.commands;


import java.util.Date;

public record CreateFeedbackCommand(Long propertyId, Long userId, int score, String comments,
                                    Date ratingDate) {
}
