package pe.edu.upc.nido_urbano_platform.contracts.domain.model.commands;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Builder;
import pe.edu.upc.nido_urbano_platform.contracts.domain.model.valueobjects.Term;
import java.util.Date;

public record CreateContractCommand(
        @NotNull Long propertyId,
        @NotNull Long tenantId,
        @NotNull Long landlordId,
        @Positive double price,
        @NotNull Term terms,
        @NotNull Date startDate,
        @NotNull Date endDate
) {
    @Builder
    public CreateContractCommand {}
}
