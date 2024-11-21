package pe.edu.upc.nido_urbano_platform.feedback.domain.model.valueobjects;

public record Score(int value) {

    public Score {
        if(value < 1 || value > 5) {
            throw new IllegalArgumentException("Feedback score has to be between 1 and 5");
        }
    }
}
