package pe.edu.upc.nido_urbano_platform.contracts.application.internal.commandservices;

import org.springframework.stereotype.Service;
import pe.edu.upc.nido_urbano_platform.contracts.domain.model.aggregates.RentalContract;
import pe.edu.upc.nido_urbano_platform.contracts.domain.model.commands.CreateRentalContractCommand;
import pe.edu.upc.nido_urbano_platform.contracts.domain.model.commands.DeleteRentalContractCommand;
import pe.edu.upc.nido_urbano_platform.contracts.domain.model.commands.UpdateRentalContractCommand;
import pe.edu.upc.nido_urbano_platform.contracts.domain.services.RentalContractCommandService;
import pe.edu.upc.nido_urbano_platform.contracts.infrastructure.persistence.jpa.repositories.RentContractRepository;

import java.util.Optional;

@Service
public class RentContractCommandServiceImpl implements RentalContractCommandService {


    private final RentContractRepository rentContractRepository;

    public RentContractCommandServiceImpl(RentContractRepository rentcontractRepository) {
        this.rentContractRepository = rentcontractRepository;
    }

    @Override
    public Long handle(CreateRentalContractCommand command) {
        var rentalContract = new RentalContract(command);
        try {
            this.rentContractRepository.save(rentalContract);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while saving rent contract: " + e.getMessage());
        }
        return rentalContract.getId();
    }

    @Override
    public Optional<RentalContract> handle(UpdateRentalContractCommand command) {
        var rentalContractId = command.Id();
        // If the feedback does not exist, throw an exception
        if (!this.rentContractRepository.existsById(rentalContractId)) {
            throw new IllegalArgumentException("Rent contract with id " + rentalContractId + " does not exist");
        }

        var rentalContractToUpdate = this.rentContractRepository.findById(rentalContractId).get();
        rentalContractToUpdate.updateRentalDetails(command.status(), command.rent(), command.paymentFrequency(), command.depositAmount(), command.terminationFee(),command.paymentMethod(),command.agreedTerms());

        try {
            var updatedRentContract = this.rentContractRepository.save(rentalContractToUpdate);
            return Optional.of(updatedRentContract);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while updating rent contract: " + e.getMessage());
        }
    }

    @Override
    public void handle(DeleteRentalContractCommand command) {
        if (!this.rentContractRepository.existsById(command.contractId())) {
            throw new IllegalArgumentException("Rent contract with id " + command.contractId() + " does not exist");
        }

        try {
            this.rentContractRepository.deleteById(command.contractId());
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while deleting rent contract: " + e.getMessage());
        }
    }
}

