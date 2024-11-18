package pe.edu.upc.nido_urbano_platform.contracts.application.internal.commandservices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.nido_urbano_platform.contracts.domain.model.aggregates.Contract;
import pe.edu.upc.nido_urbano_platform.contracts.domain.model.commands.CreateContractCommand;
import pe.edu.upc.nido_urbano_platform.contracts.domain.model.commands.DeleteContractCommand;
import pe.edu.upc.nido_urbano_platform.contracts.domain.model.commands.UpdateContractCommand;
import pe.edu.upc.nido_urbano_platform.contracts.domain.services.ContractCommandService;
import pe.edu.upc.nido_urbano_platform.contracts.infrastructure.persistence.jpa.repositories.ContractRepository;

import java.util.Optional;

@Service
public class ContractCommandServiceImpl implements ContractCommandService {


    private final ContractRepository contractRepository;

    public ContractCommandServiceImpl(ContractRepository contractRepository) {
        this.contractRepository = contractRepository;
    }

    @Override
    public Long handle(CreateContractCommand command) {
        // Verificar si ya existe un contrato con el mismo propertyId e inquilino
        if (contractRepository.existsByPropertyId(command.propertyId())) {
            throw new IllegalArgumentException("Ya existe un contrato para la propiedad con ID " + command.propertyId());
        }

        // Crear el contrato a partir del comando
        var contract = new Contract(
                command.propertyId(),
                command.tenantId(),
                command.landlordId(),
                command.price(),
                command.terms(),
                command.startDate(),
                command.endDate(),
                "pending"
        );

        try {
            contractRepository.save(contract);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error al guardar el contrato: " + e.getMessage());
        }

        return contract.getId();
    }

    @Override
    public Optional<Contract> handle(UpdateContractCommand command) {
        var contractId = command.contractId();

        // Verificar si el contrato existe
        if (!contractRepository.existsById((Long) contractId)) {
            throw new IllegalArgumentException("El contrato con ID " + contractId + " no existe");
        }

        // Obtener y actualizar la información del contrato
        var contractToUpdate = contractRepository.findById(contractId).get();
        contractToUpdate.updateContractDetails(command.price(), command.terms(), command.startDate(), command.endDate());

        try {
            var updatedContract = contractRepository.save(contractToUpdate);
            return Optional.of(updatedContract);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error al actualizar el contrato: " + e.getMessage());
        }
    }

    @Override
    public void handle(DeleteContractCommand command) {
        var contractId = command.contractId();

        // Verificar si el contrato existe antes de eliminar
        if (!contractRepository.existsById(contractId)) {
            throw new IllegalArgumentException("El contrato con ID " + contractId + " no existe");
        }

        try {
            contractRepository.deleteById(contractId);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error al eliminar el contrato: " + e.getMessage());
        }
    }
}
