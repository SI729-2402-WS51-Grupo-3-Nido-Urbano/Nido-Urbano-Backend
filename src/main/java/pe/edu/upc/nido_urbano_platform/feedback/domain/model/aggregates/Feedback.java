package pe.edu.upc.nido_urbano_platform.feedback.domain.model.aggregates;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pe.edu.upc.nido_urbano_platform.feedback.domain.model.commands.CreateFeedbackCommand;
import pe.edu.upc.nido_urbano_platform.feedback.domain.model.valueobjects.PropertyId;
import pe.edu.upc.nido_urbano_platform.feedback.domain.model.valueobjects.Score;
import pe.edu.upc.nido_urbano_platform.feedback.domain.model.valueobjects.UserId;
import pe.edu.upc.nido_urbano_platform.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;

import java.util.Date;


@Entity
@Table(name = "feedbacks")
@Getter
@Setter
@NoArgsConstructor
public class Feedback extends AuditableAbstractAggregateRoot<Feedback> {

    @Embedded
    @AttributeOverrides( {
            @AttributeOverride(name = "propertyId", column = @Column(name = "property_id", nullable = false))
    })
    private PropertyId propertyId;

    @Embedded
    @AttributeOverrides( {
            @AttributeOverride(name = "userId", column = @Column(name = "user_id", nullable = false))
    })
    private UserId userId;

    @Embedded
    @AttributeOverrides( {
            @AttributeOverride(name = "score", column = @Column(name = "score", nullable = false))
    })
    private Score score;

    @Column(name = "comments", columnDefinition = "TEXT")
    private String comments;

    @Column(name = "rating_date", nullable = false)
    private Date ratingDate;


    public Feedback(CreateFeedbackCommand command) {
        this.propertyId = new PropertyId(command.propertyId());
        this.userId = new UserId(command.userId());
        this.score = new Score(command.score());
        this.comments = command.comments();
        this.ratingDate = command.ratingDate();
    }

    public Feedback updateFeedback(int score, String comments, Date ratingDate) {
        this.score = new Score(score);
        this.comments = comments;
        this.ratingDate = ratingDate;
        return this;
    }
}

