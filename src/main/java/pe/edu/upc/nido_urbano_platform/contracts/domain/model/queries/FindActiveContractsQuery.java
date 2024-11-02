package pe.edu.upc.nido_urbano_platform.contracts.domain.model.queries;

import java.util.Date;

public record FindActiveContractsQuery(Date startDate, Date endDate) {
}
