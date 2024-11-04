package pe.edu.upc.nido_urbano_platform.house.domain.model.commands;

public record UpdateVerificationCommand(
        Long verificationId,
        String ownerName,
        String condition,
        String uniqueRegistrationNumber
) {
}
