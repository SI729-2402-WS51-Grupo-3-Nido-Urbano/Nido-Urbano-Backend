package pe.edu.upc.nido_urbano_platform.contracts.interfaces.rest.resources;

import jakarta.validation.constraints.NotNull;

import java.util.Date;

public record ContractResource(
        @NotNull Long id,
        @NotNull Long propertyId,
        @NotNull Long tenantId,
        @NotNull Long landlordId,
        @NotNull Double price,
        @NotNull String termsDescription,
        Date startDate,
        Date endDate
) {}