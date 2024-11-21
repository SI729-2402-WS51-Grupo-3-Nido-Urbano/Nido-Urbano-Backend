package pe.edu.upc.nido_urbano_platform.house_managers.interfaces.rest.transform;

import pe.edu.upc.nido_urbano_platform.house_managers.domain.model.aggregates.House;
import pe.edu.upc.nido_urbano_platform.house_managers.interfaces.rest.resources.HouseResource;

public class HouseResourceFromEntityAssembler {
    public static HouseResource toResourceFromEntity(House entity) {
        return new HouseResource(
                entity.getId(),
                entity.getUserPropertyId().getValue(), // Convert UserPropertyId to Long
                entity.getHouseName(),
                entity.getAddress().getValue(), // Convert AddressHouse to String
                entity.getHouseType().toString(), // Convert HouseType to String
                entity.getHouseModal().toString(), // Convert HouseModal to String
                entity.getPrice(),
                entity.getSize(),
                entity.getDescription(),
                entity.getPublicationDate(),
                entity.getStartsCalification(),
                entity.getStatusLandlord().toString(), // Convert StatusLandlord to String
                entity.getPhoto(),
                entity.getVideo(),
                entity.getTermsConditions()
        );
    }
}
