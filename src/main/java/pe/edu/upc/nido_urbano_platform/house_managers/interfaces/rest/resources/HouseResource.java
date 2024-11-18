package pe.edu.upc.nido_urbano_platform.house_managers.interfaces.rest.resources;

import java.util.Date;

public record HouseResource(Long id,
                            Long userPropertyID,
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
                            String termsConditions
) {
}
