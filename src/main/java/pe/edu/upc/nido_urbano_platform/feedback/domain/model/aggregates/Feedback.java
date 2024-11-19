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

    @Column(name = "userName", columnDefinition = "TEXT")
    private String userName;

    @Embedded
    @AttributeOverrides( {
            @AttributeOverride(name = "score", column = @Column(name = "score", nullable = false))
    })
    private Score score;

    @Column(name = "comments", columnDefinition = "TEXT")
    private String comments;


    public Feedback(CreateFeedbackCommand command) {
        this.propertyId = new PropertyId(command.propertyId());
        this.userId = new UserId(command.userId());
        this.userName = command.userName();
        this.score = new Score(command.score());
        this.comments = command.comments();
    }

    public Feedback updateFeedback(int score, String comments) {
        this.score = new Score(score);
        this.comments = comments;
        return this;
    }
}

