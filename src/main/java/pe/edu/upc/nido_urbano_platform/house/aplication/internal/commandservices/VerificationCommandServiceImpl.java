package pe.edu.upc.nido_urbano_platform.house.aplication.internal.commandservices;

import org.springframework.stereotype.Service;
import pe.edu.upc.nido_urbano_platform.house.domain.model.commands.CreateVerificationCommand;
import pe.edu.upc.nido_urbano_platform.house.domain.model.commands.DeleteVerificationCommand;
import pe.edu.upc.nido_urbano_platform.house.domain.model.commands.UpdateVerificationCommand;
import pe.edu.upc.nido_urbano_platform.house.domain.model.entities.Verification;
import pe.edu.upc.nido_urbano_platform.house.domain.services.VerificationCommandService;
import pe.edu.upc.nido_urbano_platform.house.infrastructure.persistence.jpa.repositories.VerificationRepository;

import java.util.Optional;

@Service
public class VerificationCommandServiceImpl implements VerificationCommandService {

    private final VerificationRepository verificationRepository;

    public VerificationCommandServiceImpl(VerificationRepository verificationRepository) {
        this.verificationRepository = verificationRepository;
    }

    @Override
    public Long handle(CreateVerificationCommand command) {
        var existesUniqueRegistrationNumber = command.uniqueRegistrationNumber();
        if(this.verificationRepository.existsByUniqueRegistrationNumber(existesUniqueRegistrationNumber)){
            throw new IllegalArgumentException("Verification with unique registration number " + existesUniqueRegistrationNumber + " already exists");
        }
        var verification= new Verification(command);
        try {
            this.verificationRepository.save(verification);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while saving verification: " + e.getMessage());
        }
        return verification.getId();
    }

    @Override
    public Optional<Verification> handle(UpdateVerificationCommand command) {
        var verificationId = command.verificationId();
        var uniqueRegistrationNumber = command.uniqueRegistrationNumber();
        if (this.verificationRepository.existsByUniqueRegistrationNumberAndIdIsNot(uniqueRegistrationNumber, verificationId)) {
            throw new IllegalArgumentException("Verification with unique registration number " + uniqueRegistrationNumber + " already exists");
        }
        if (!this.verificationRepository.existsById(verificationId)) {
            throw new IllegalArgumentException("Verification with id " + verificationId + " does not exist");
        }

        var verificationToUpdate = this.verificationRepository.findById(verificationId).get();
        verificationToUpdate.updateInformantion(command.ownerName(), command.condition(), command.uniqueRegistrationNumber());

        try {
            var updatedVerification = this.verificationRepository.save(verificationToUpdate);
            return Optional.of(updatedVerification);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while updating verification: " + e.getMessage());
        }
    }

    @Override
    public void handle(DeleteVerificationCommand command) {
        if(!this.verificationRepository.existsById(command.verificationId())){
            throw new IllegalArgumentException("Verification with id " + command.verificationId() + " does not exist");
        }

        try{
            this.verificationRepository.deleteById(command.verificationId());
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while deleting verification: " + e.getMessage());
        }

    }
}
