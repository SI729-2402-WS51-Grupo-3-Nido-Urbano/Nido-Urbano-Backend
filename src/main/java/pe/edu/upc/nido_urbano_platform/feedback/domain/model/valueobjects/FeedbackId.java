package pe.edu.upc.nido_urbano_platform.feedback.domain.model.valueobjects;

public record FeedbackId(Long feedbackId) {
    public FeedbackId {

        if (feedbackId < 0) {
            throw new IllegalArgumentException("Feedback feedbackId cannot be negative");
        }
    }

    public FeedbackId() {
        this(0L);
    }
}
