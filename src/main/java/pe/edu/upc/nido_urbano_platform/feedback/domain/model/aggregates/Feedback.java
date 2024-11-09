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

    @Column(name = "property_id", nullable = false)
    private Long propertyId;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    //@Column(name = "house_name", nullable = false)
    //private String houseName;

    //@Column(name = "address", nullable = false)
    //private String address;

    @Embedded
    private Score score;

    @Column(name = "comments", columnDefinition = "TEXT")
    private String comments;

    @Column(name = "rating_date", nullable = false)
    private Date ratingDate;


    public Feedback(Long propertyId, Long userId, Score score, String comments, Date ratingDate) {
        this.propertyId = propertyId;
        this.userId = userId;
        this.score = score;
        this.comments = comments;
        this.ratingDate = ratingDate;
    }

    public Feedback(CreateFeedbackCommand command) {
        this.propertyId = command.propertyId();
        this.userId = command.userId();
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

