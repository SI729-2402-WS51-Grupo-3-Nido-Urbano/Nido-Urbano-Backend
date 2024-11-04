package pe.edu.upc.nido_urbano_platform.house.domain.model.commands;

import pe.edu.upc.nido_urbano_platform.house.domain.model.entities.Verification;

import java.util.Date;

public record CreateHouseCommand(
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
        String video,
        Verification verificationId) {

}
