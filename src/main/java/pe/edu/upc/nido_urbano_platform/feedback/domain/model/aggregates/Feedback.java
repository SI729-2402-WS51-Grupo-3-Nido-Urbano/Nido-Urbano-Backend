package pe.edu.upc.nido_urbano_platform.feedback.domain.model.aggregates;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pe.edu.upc.nido_urbano_platform.feedback.domain.model.commands.CreateFeedbackCommand;
import pe.edu.upc.nido_urbano_platform.feedback.domain.model.valueobjects.Score;
import pe.edu.upc.nido_urbano_platform.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;

import java.util.Date;


@Entity
@Table(name = "feedbacks")
@Getter
@Setter
@NoArgsConstructor
public class Feedback extends AuditableAbstractAggregateRoot<Feedback> {
    @Column(name = "house_name", nullable = false)
    private String houseName;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "rated_user_id", nullable = false)
    private Long ratedUserId;

    @Column(name = "rating_user_id", nullable = false)
    private Long ratingUserId;

    @Embedded
    private Score score;

    @Column(name = "comments", columnDefinition = "TEXT")
    private String comments;

    @Column(name = "rating_date", nullable = false)
    private Date ratingDate;

    // Constructor con argumentos para facilitar la creación de objetos Feedback
    public Feedback(String houseName, String address, Long ratedUserId, Long ratingUserId, Score score, String comments, Date ratingDate) {
        this.houseName = houseName;
        this.address = address;
        this.ratedUserId = ratedUserId;
        this.ratingUserId = ratingUserId;
        this.score = score;
        this.comments = comments;
        this.ratingDate = ratingDate;
    }

    public Feedback(CreateFeedbackCommand command) {
        this.houseName = command.houseName();
        this.address = command.address();
        this.ratedUserId = command.ratedUserId();
        this.ratingUserId = command.ratingUserId();
        this.score = command.score();
        this.comments = command.comments();
        this.ratingDate = command.ratingDate();
    }

    public Feedback updateFeedback(Score score, String comments, Date ratingDate) {
        this.score = score;
        this.comments = comments;
        this.ratingDate = ratingDate;
        return this;
    }
}

