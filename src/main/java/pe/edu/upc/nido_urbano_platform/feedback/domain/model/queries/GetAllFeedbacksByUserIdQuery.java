package pe.edu.upc.nido_urbano_platform.feedback.domain.model.queries;

import pe.edu.upc.nido_urbano_platform.feedback.domain.model.valueobjects.UserId;

public record GetAllFeedbacksByUserIdQuery(UserId userId) {
}
