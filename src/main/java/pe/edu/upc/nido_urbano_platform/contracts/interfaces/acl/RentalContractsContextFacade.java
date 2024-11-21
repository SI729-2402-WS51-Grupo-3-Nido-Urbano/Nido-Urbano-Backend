package pe.edu.upc.nido_urbano_platform.contracts.interfaces.acl;

import org.springframework.stereotype.Service;
import pe.edu.upc.nido_urbano_platform.contracts.domain.model.commands.CreateRentalContractCommand;
import pe.edu.upc.nido_urbano_platform.contracts.domain.model.commands.DeleteRentalContractCommand;
import pe.edu.upc.nido_urbano_platform.contracts.domain.model.commands.UpdateRentalContractCommand;
import pe.edu.upc.nido_urbano_platform.contracts.domain.model.valueobjects.Terms;
import pe.edu.upc.nido_urbano_platform.contracts.domain.services.RentalContractCommandService;
import pe.edu.upc.nido_urbano_platform.contracts.domain.services.RentalContractQueryService;

import java.util.Date;

@Service
public class RentalContractsContextFacade {
    private final RentalContractCommandService rentalContractCommandService;
    private final RentalContractQueryService rentalContractQueryService;

    public RentalContractsContextFacade(RentalContractCommandService rentalContractCommandService, RentalContractQueryService rentalContractQueryService) {
        this.rentalContractCommandService = rentalContractCommandService;
        this.rentalContractQueryService = rentalContractQueryService;
    }

    public Long createRentalContract(Long  propertyId,
                                     Long  userId,
                                     Long  landlordId,
                                     String status,
                                     Double rent,
                                     String paymentFrequency,
                                     Double depositAmount,
                                     Double terminationFee,
                                     String paymentMethod,
                                     String terms,
                                     Boolean agreedTerms,
                                     Date startDate,
                                     Date endDate){
        var CreateRentalContractCommand = new CreateRentalContractCommand(propertyId,userId,landlordId,status,rent,paymentFrequency,
                depositAmount,terminationFee,paymentMethod,terms,agreedTerms,startDate,endDate);
        var rentalContractId = rentalContractCommandService.handle(CreateRentalContractCommand);
        if (rentalContractId.equals(null)){
            return 0L;
        }
        return rentalContractId;
    }
    public Long updateRentalContract(Long Id, String status, Double rent, String paymentFrequency, Double depositAmount, Double terminationFee, String paymentMethod, Boolean agreedTerms){
        var updateRentalContractCommand = new UpdateRentalContractCommand(Id, status, rent, paymentFrequency, depositAmount, terminationFee, paymentMethod, agreedTerms);
        var optionalRentalContract = rentalContractCommandService.handle(updateRentalContractCommand);
        if (optionalRentalContract.isEmpty()){
            return 0L;
        }
        return optionalRentalContract.get().getId();
    }
    public void deleteRentalContract(Long Id){
        var deleteRentalContract = new DeleteRentalContractCommand(Id);
        rentalContractCommandService.handle(deleteRentalContract);
    }


}
