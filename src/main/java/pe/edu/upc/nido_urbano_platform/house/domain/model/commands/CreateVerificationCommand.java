package pe.edu.upc.nido_urbano_platform.house.domain.model.commands;

public record CreateVerificationCommand(
        String ownerName,
        String condition,
        String uniqueRegistrationNumber
) {
}
