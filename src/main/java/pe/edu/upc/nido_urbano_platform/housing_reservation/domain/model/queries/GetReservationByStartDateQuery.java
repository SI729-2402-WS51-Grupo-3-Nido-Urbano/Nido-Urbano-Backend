package pe.edu.upc.nido_urbano_platform.housing_reservation.domain.model.queries;

import javax.print.attribute.DateTimeSyntax;
import java.util.Date;

public record GetReservationByStartDateQuery(Date startDate) {
}
