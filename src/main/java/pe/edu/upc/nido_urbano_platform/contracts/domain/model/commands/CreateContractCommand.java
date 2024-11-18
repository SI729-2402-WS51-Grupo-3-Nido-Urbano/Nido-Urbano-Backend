package pe.edu.upc.nido_urbano_platform.contracts.domain.model.commands;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Builder;

import java.util.Date;

public record CreateContractCommand(
        @NotNull Long propertyId,
        @NotNull Long tenantId,
        @NotNull Long landlordId,
        @NotNull Double price,
        @NotNull String terms,
        @NotNull Date startDate,
        @NotNull Date endDate
) {
    @Builder
    public CreateContractCommand {}
}
