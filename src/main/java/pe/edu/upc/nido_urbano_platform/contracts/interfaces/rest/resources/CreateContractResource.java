package pe.edu.upc.nido_urbano_platform.contracts.interfaces.rest.resources;

import jakarta.validation.constraints.NotNull;

import java.util.Date;

public record CreateContractResource(
        @NotNull Long propertyId,
        @NotNull Long tenantId,
        @NotNull Long landlordId,
        @NotNull Double price,
        @NotNull String termsDescription,
        @NotNull Date startDate,
        @NotNull Date endDate
) {}