package pe.edu.upc.nido_urbano_platform.house.domain.services;

import pe.edu.upc.nido_urbano_platform.house.domain.model.commands.CreateVerificationCommand;
import pe.edu.upc.nido_urbano_platform.house.domain.model.commands.DeleteVerificationCommand;
import pe.edu.upc.nido_urbano_platform.house.domain.model.commands.UpdateVerificationCommand;
import pe.edu.upc.nido_urbano_platform.house.domain.model.entities.Verification;

import java.util.Optional;

public interface VerificationCommandService {
    Long handle(CreateVerificationCommand command);
    Optional<Verification> handle(UpdateVerificationCommand command);
    void handle(DeleteVerificationCommand command);
}
