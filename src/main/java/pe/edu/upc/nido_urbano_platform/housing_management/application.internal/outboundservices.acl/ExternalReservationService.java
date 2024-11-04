package pe.edu.upc.nido_urbano_platform.housing_management.application.internal.outboundservices.acl;

import org.springframework.stereotype.Service;
import pe.edu.upc.nido_urbano_platform.housing_management.domain.model.aggregates.House;
import pe.edu.upc.nido_urbano_platform.housing_management.domain.model.valueobjects.ReservationId;
import pe.edu.upc.nido_urbano_platform.housing_management.interfaces.rest.resources.HouseResource;
import pe.edu.upc.nido_urbano_platform.housing_reservation.interfaces.acl.ReservationsContextFacade;

import java.util.Optional;

@Service
public class ExternalReservationService {

    private final ReservationsContextFacade reservationsContextFacade;

    public ExternalReservationService(ReservationsContextFacade reservationsContextFacade) {
        this.reservationsContextFacade = reservationsContextFacade;
    }

    public Optional<ReservationId> fetchReservationIdByStartDate(String startDate) {
        var reservationId = reservationsContextFacade.fetchReservationIdByStartDate(startDate);
        if (reservationId.equals(0L))
            return Optional.empty();
        return Optional.of(new ReservationId(reservationId));
    }

    public boolean existsReservationByStartDateAndIdIsNot(String startDate, long id) {
        return reservationsContextFacade.existsReservationByStartDateAndIdIsNot(startDate, id);
    }

    public Optional<ReservationId> createReservation(String startDate, String endDate, String street) {
        var reservationId = reservationsContextFacade.createReservation(startDate, endDate, street);
        if (reservationId.equals(0L))
            return Optional.empty();
        return Optional.of(new ReservationId(reservationId));
    }

    public Optional<ReservationId> updateReservation(Long reservationId, String startDate, String endDate, String street) {
        var reservationIdUpdated = reservationsContextFacade.updateReservation(reservationId, startDate, endDate, street);
        if (reservationIdUpdated.equals(0L))
            return Optional.empty();
        return Optional.of(new ReservationId(reservationIdUpdated));
    }

    public void deleteReservation(Long reservationId) {
        reservationsContextFacade.deleteReservation(reservationId);
    }

    public Optional<HouseResource> fetchHouseResourceFromReservationId(House house) {
        var reservationResource = reservationsContextFacade.fetchReservationById(house.getReservationId());
        if (reservationResource.isEmpty())
            return Optional.empty();

        var houseResource = new HouseResource(house.getHouseCode().houseCode(), reservationResource.get().startDate(),
                reservationResource.get().endDate(), reservationResource.get().street());
        return Optional.of(houseResource);
    }
}
