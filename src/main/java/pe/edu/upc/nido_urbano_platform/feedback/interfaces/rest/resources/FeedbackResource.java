package pe.edu.upc.nido_urbano_platform.feedback.interfaces.rest.resources;

import pe.edu.upc.nido_urbano_platform.feedback.domain.model.valueobjects.Score;

import java.util.Date;

public record FeedbackResource(Long id, String houseName, String address, Long ratedUserId, Long ratingUserId, Score score, String comments, Date ratingDate) {
}
