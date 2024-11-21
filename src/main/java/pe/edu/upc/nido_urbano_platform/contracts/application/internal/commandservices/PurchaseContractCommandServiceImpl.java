package pe.edu.upc.nido_urbano_platform.contracts.application.internal.commandservices;

import org.springframework.stereotype.Service;
import pe.edu.upc.nido_urbano_platform.contracts.domain.model.aggregates.PurchaseContract;
import pe.edu.upc.nido_urbano_platform.contracts.domain.model.commands.CreatePurchaseContractCommand;
import pe.edu.upc.nido_urbano_platform.contracts.domain.model.commands.DeletePurchaseContractCommand;
import pe.edu.upc.nido_urbano_platform.contracts.domain.model.commands.UpdatePurchaseContractCommand;
import pe.edu.upc.nido_urbano_platform.contracts.domain.services.PurchaseContractCommandService;
import pe.edu.upc.nido_urbano_platform.contracts.infrastructure.persistence.jpa.repositories.PurchaseContractRepository;

import java.util.Optional;

@Service
public class PurchaseContractCommandServiceImpl implements PurchaseContractCommandService {

    private final PurchaseContractRepository purchaseContractRepository;

    public PurchaseContractCommandServiceImpl(PurchaseContractRepository purchaseContractRepository) {
        this.purchaseContractRepository = purchaseContractRepository;
    }


    @Override
    public Long handle(CreatePurchaseContractCommand command) {
        var purchaseContract = new PurchaseContract(command);
        try {
            this.purchaseContractRepository.save(purchaseContract);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while saving purchase contract: " + e.getMessage());
        }
        return purchaseContract.getId();
    }

    @Override
    public Optional<PurchaseContract> handle(UpdatePurchaseContractCommand command) {
        var purchaseContractId = command.Id();
        // If the feedback does not exist, throw an exception
        if (!this.purchaseContractRepository.existsById(purchaseContractId)) {
            throw new IllegalArgumentException("Purchase contract with id " + purchaseContractId + " does not exist");
        }

        var purchaseContractToUpdate = this.purchaseContractRepository.findById(purchaseContractId).get();
        purchaseContractToUpdate.updatePurchaseDetails(command.status(), command.purchasePrice(), command.paymentMethod(), command.closingDate(), command.agreedTerms());

        try {
            var updatedPurchaseContract = this.purchaseContractRepository.save(purchaseContractToUpdate);
            return Optional.of(updatedPurchaseContract);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while updating purchase contract: " + e.getMessage());
        }
    }

    @Override
    public void handle(DeletePurchaseContractCommand command) {
        if (!this.purchaseContractRepository.existsById(command.contractId())) {
            throw new IllegalArgumentException("Purchase contract with id " + command.contractId() + " does not exist");
        }

        try {
            this.purchaseContractRepository.deleteById(command.contractId());
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while deleting purchase contract: " + e.getMessage());
        }
    }
}
