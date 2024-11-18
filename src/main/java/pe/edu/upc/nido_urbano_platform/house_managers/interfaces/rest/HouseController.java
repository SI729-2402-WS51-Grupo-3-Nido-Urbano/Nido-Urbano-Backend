package pe.edu.upc.nido_urbano_platform.house_managers.interfaces.rest;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.nido_urbano_platform.house_managers.domain.model.commands.DeleteHouseCommand;
import pe.edu.upc.nido_urbano_platform.house_managers.domain.model.queries.GetAllHousesQuery;
import pe.edu.upc.nido_urbano_platform.house_managers.domain.model.queries.GetHouseByHouseModalQuery;
import pe.edu.upc.nido_urbano_platform.house_managers.domain.model.queries.GetHouseByIdQuery;
import pe.edu.upc.nido_urbano_platform.house_managers.domain.model.queries.GetHouseByUserPropertyIdQuery;
import pe.edu.upc.nido_urbano_platform.house_managers.domain.services.HouseCommandService;
import pe.edu.upc.nido_urbano_platform.house_managers.domain.services.HouseQueryService;
import pe.edu.upc.nido_urbano_platform.house_managers.interfaces.rest.resources.CreateHouseResource;
import pe.edu.upc.nido_urbano_platform.house_managers.interfaces.rest.resources.HouseResource;
import pe.edu.upc.nido_urbano_platform.house_managers.interfaces.rest.transform.CreateHouseCommandFromResourceAssembler;
import pe.edu.upc.nido_urbano_platform.house_managers.interfaces.rest.transform.HouseResourceFromEntityAssembler;
import pe.edu.upc.nido_urbano_platform.house_managers.interfaces.rest.transform.UpdateHouseCommandFromResourceAssembler;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*",methods = {RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT,RequestMethod.DELETE})
@RestController
@RequestMapping(value = "/api/v1/houses",produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Houses",description = "House Management Endpoints")
public class HouseController {
    private final HouseQueryService houseQueryService;
    private final HouseCommandService houseCommandService;

    public HouseController(HouseQueryService houseQueryService, HouseCommandService houseCommandService) {
        this.houseQueryService = houseQueryService;
        this.houseCommandService = houseCommandService;
    }

    @PostMapping
    public ResponseEntity<HouseResource> createHouse(@RequestBody CreateHouseResource resource){
        var createHouseCommand = CreateHouseCommandFromResourceAssembler.toCommandFromResource(resource);
        var houseId = this.houseCommandService.handle(createHouseCommand);
        if(houseId.equals(0L)){
            return ResponseEntity.badRequest().build();
        }
        var getHouseByIdQuery = new GetHouseByIdQuery(houseId);
        var optionalHouse = this.houseQueryService.handle(getHouseByIdQuery);
        var houseResource = HouseResourceFromEntityAssembler.toResourceFromEntity(optionalHouse.get());
        return new ResponseEntity<>(houseResource, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<HouseResource>> getAllHouses(){
        var getAllHousesQuery = new GetAllHousesQuery();
        var houses = this.houseQueryService.handle(getAllHousesQuery);
        var houseResources = houses.stream()
                .map(HouseResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(houseResources);
    }

    @GetMapping("/{id}")
    public ResponseEntity<HouseResource> getHouseById(@PathVariable Long id){
        var getHouseByIdQuery = new GetHouseByIdQuery(id);
        var optionalHouse = this.houseQueryService.handle(getHouseByIdQuery);
        if(optionalHouse.isEmpty()){
            return ResponseEntity.badRequest().build();
        }
        var houseResource = HouseResourceFromEntityAssembler.toResourceFromEntity(optionalHouse.get());
        return ResponseEntity.ok(houseResource);
    }

    @GetMapping("/userPropertyID/{user_property_id}")
    public ResponseEntity<List<HouseResource>> getHouseByUserPropertyId(@PathVariable Long user_property_id) {
        var getHouseByUserPropertyIdQuery = new GetHouseByUserPropertyIdQuery(user_property_id);
        var houses = this.houseQueryService.handle(getHouseByUserPropertyIdQuery);
        if (houses.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        var houseResources = houses.stream()
                .map(HouseResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(houseResources);
    }

    @GetMapping("/houseModal/{house_modal}")
    public ResponseEntity<List<HouseResource>> getHouseByHouseModal(@PathVariable String house_modal) {
        var getHouseByHouseModalQuery = new GetHouseByHouseModalQuery(house_modal);
        var houses = this.houseQueryService.handle(getHouseByHouseModalQuery);
        if (houses.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        var houseResources = houses.stream()
                .map(HouseResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(houseResources);
    }

    @PutMapping("/{id}")
    public ResponseEntity<HouseResource> updateHouse(@PathVariable Long id, @RequestBody HouseResource resource ) {
        var updateHouseCommand = UpdateHouseCommandFromResourceAssembler.toCommandFromResource(id, resource);
        var optionalHouse = this.houseCommandService.handle(updateHouseCommand);
        if (optionalHouse.isEmpty())
            return ResponseEntity.badRequest().build();
        var houseResource = HouseResourceFromEntityAssembler.toResourceFromEntity(optionalHouse.get());
        return ResponseEntity.ok(houseResource);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteHouse(@PathVariable Long id){
        var deleteHouseCommand = new DeleteHouseCommand(id);
        this.houseCommandService.handle(deleteHouseCommand);
        return ResponseEntity.noContent().build();
    }



}
