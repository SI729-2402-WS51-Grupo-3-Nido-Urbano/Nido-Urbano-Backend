package pe.edu.upc.nido_urbano_platform.housing_management.interfaces.rest;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.nido_urbano_platform.housing_management.application.internal.outboundservices.acl.ExternalReservationService;
import pe.edu.upc.nido_urbano_platform.housing_management.domain.model.commands.DeleteHouseCommand;
import pe.edu.upc.nido_urbano_platform.housing_management.domain.model.queries.GetAllHousesQuery;
import pe.edu.upc.nido_urbano_platform.housing_management.domain.model.queries.GetHouseByHouseCodeQuery;
import pe.edu.upc.nido_urbano_platform.housing_management.domain.model.valueobjects.HouseCode;
import pe.edu.upc.nido_urbano_platform.housing_management.domain.services.HouseCommandService;
import pe.edu.upc.nido_urbano_platform.housing_management.domain.services.HouseQueryService;
import pe.edu.upc.nido_urbano_platform.housing_management.interfaces.rest.resources.CreateHouseResource;
import pe.edu.upc.nido_urbano_platform.housing_management.interfaces.rest.resources.HouseResource;
import pe.edu.upc.nido_urbano_platform.housing_management.interfaces.rest.transform.CreateHouseCommandFromResourceAssembler;
import pe.edu.upc.nido_urbano_platform.housing_management.interfaces.rest.transform.UpdateHouseCommandFromResourceAssembler;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", methods = { RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT, RequestMethod.DELETE })
@RestController
@RequestMapping(value = "/api/v1/houses", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Houses", description = "House Management Endpoints")
public class HousesController {

    private final HouseCommandService houseCommandService;
    private final HouseQueryService houseQueryService;
    private final ExternalReservationService externalReservationService;

    public HousesController(HouseCommandService houseCommandService, HouseQueryService houseQueryService,
                              ExternalReservationService externalReservationService) {
        this.houseCommandService = houseCommandService;
        this.houseQueryService = houseQueryService;
        this.externalReservationService = externalReservationService;
    }

    @PostMapping
    public ResponseEntity<HouseResource> createHouse(@RequestBody CreateHouseResource resource) {
        var createHouseCommand = CreateHouseCommandFromResourceAssembler.toCommandFromResource(resource);
        var houseCode = this.houseCommandService.handle(createHouseCommand);

        if (houseCode.houseCode().isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        var getHouseByHouseCodeQuery = new GetHouseByHouseCodeQuery(houseCode);
        var house = this.houseQueryService.handle(getHouseByHouseCodeQuery);

        if (house.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        var houseResource = this.externalReservationService.fetchHouseResourceFromReservationId(house.get()).get();
        return new ResponseEntity<>(houseResource, HttpStatus.CREATED);

    }

    @GetMapping
    public ResponseEntity<List<HouseResource>> getAllHouses() {
        var getAllHousesQuery = new GetAllHousesQuery();
        var houses = this.houseQueryService.handle(getAllHousesQuery);
        var reservationResources = houses.stream()
                .map(this.externalReservationService::fetchHouseResourceFromReservationId)
                .map(Optional::get)
                .collect(Collectors.toList());
        return ResponseEntity.ok(reservationResources);
    }

    @GetMapping("/{houseCode}")
    public ResponseEntity<HouseResource> getHouseById(@PathVariable String houseCode) {
        var getHouseByHouseCodeQuery = new GetHouseByHouseCodeQuery(new HouseCode(houseCode));
        var optionalHouse = this.houseQueryService.handle(getHouseByHouseCodeQuery);
        if (optionalHouse.isEmpty())
            return ResponseEntity.badRequest().build();
        var houseResource = this.externalReservationService.fetchHouseResourceFromReservationId(optionalHouse.get()).get();
        return ResponseEntity.ok(houseResource);
    }

    @PutMapping("/{houseCode}")
    public ResponseEntity<HouseResource> updateHouse(@PathVariable String houseCode, @RequestBody HouseResource resource) {
        var updateReservationCommand = UpdateHouseCommandFromResourceAssembler.toCommandFromResource(houseCode, resource);
        var optionalHouse = this.houseCommandService.handle(updateReservationCommand);

        if (optionalHouse.isEmpty())
            return ResponseEntity.badRequest().build();
        var houseResource = this.externalReservationService.fetchHouseResourceFromReservationId(optionalHouse.get()).get();
        return ResponseEntity.ok(houseResource);
    }

    @DeleteMapping("/{houseCode}")
    public ResponseEntity<?> deleteReservation(@PathVariable String houseCode) {
        var deleteHouseCommand = new DeleteHouseCommand(new HouseCode(houseCode));
        this.houseCommandService.handle(deleteHouseCommand);
        return ResponseEntity.noContent().build();
    }
}