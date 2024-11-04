package pe.edu.upc.nido_urbano_platform.house.domain.model.commands;

import java.util.Date;

public record UpdateHouseCommand(
        Long houseId,
        Long userPropertyId,
        String houseName,
        String address,
        String houseType,
        String houseModal,
        Long price,
        int size,
        String description,
        Date publicationDate,
        int startsCalification,
        String statusLandlord,
        String photo,
        String video
        ) {
}
