package pe.edu.upc.nido_urbano_platform.housing_management.domain.model.commands;

public record CreateHouseCommand(String startDate,
                                 String endDate,
                                 String street,
                                 String tenantName) {
}
