package pe.edu.upc.nido_urbano_platform.feedback.domain.model.commands;


import java.util.Date;

public record CreateFeedbackCommand(Long propertyId, Long userId, String userName, int score, String comments) {
}
