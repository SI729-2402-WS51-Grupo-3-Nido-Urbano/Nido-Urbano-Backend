package pe.edu.upc.nido_urbano_platform.feedback.interfaces.rest;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.nido_urbano_platform.feedback.domain.model.commands.DeleteFeedbackCommand;
import pe.edu.upc.nido_urbano_platform.feedback.domain.model.queries.GetAllFeedbacksByHouseNameQuery;
import pe.edu.upc.nido_urbano_platform.feedback.domain.model.queries.GetFeedbackByIdQuery;
import pe.edu.upc.nido_urbano_platform.feedback.domain.services.FeedbackCommandService;
import pe.edu.upc.nido_urbano_platform.feedback.domain.services.FeedbackQueryService;
import pe.edu.upc.nido_urbano_platform.feedback.interfaces.rest.resources.CreateFeedbackResource;
import pe.edu.upc.nido_urbano_platform.feedback.interfaces.rest.resources.FeedbackResource;
import pe.edu.upc.nido_urbano_platform.feedback.interfaces.rest.transform.CreateFeedbackCommandFromResourceAssembler;
import pe.edu.upc.nido_urbano_platform.feedback.interfaces.rest.transform.FeedbackResourceFromEntityAssembler;
import pe.edu.upc.nido_urbano_platform.feedback.interfaces.rest.transform.UpdateFeedbackCommandFromResourceAssembler;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", methods = { RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT, RequestMethod.DELETE })
@RestController
@RequestMapping(value = "/api/v1/feedbacks", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Feedbacks", description = "Feedback Management Endpoints")
public class FeedbackController {
    private final FeedbackQueryService feedbackQueryService;
    private final FeedbackCommandService feedbackCommandService;

    public FeedbackController(FeedbackQueryService profileQueryService, FeedbackCommandService profileCommandService) {
        this.feedbackQueryService = profileQueryService;
        this.feedbackCommandService = profileCommandService;
    }

    @PostMapping
    public ResponseEntity<FeedbackResource> createFeedback(@RequestBody CreateFeedbackResource resource) {

        var createFeedbackCommand = CreateFeedbackCommandFromResourceAssembler
                .toCommandFromResource(resource);
        var feedbackId = this.feedbackCommandService.handle(createFeedbackCommand);

        if (feedbackId.equals(0L)) {
            return ResponseEntity.badRequest().build();
        }

        var getFeedbackByIdQuery = new GetFeedbackByIdQuery(feedbackId);
        var optionalFeedback = this.feedbackQueryService.handle(getFeedbackByIdQuery);

        var profileResource = FeedbackResourceFromEntityAssembler.toResourceFromEntity(optionalFeedback.get());
        return new ResponseEntity<>(profileResource, HttpStatus.CREATED);
    }


    @GetMapping("/{houseName}")
    public ResponseEntity<List<FeedbackResource>> getAllFeedbackByHouseName(@PathVariable String houseName) {
        var getFeedbackByHouseNameQuery = new GetAllFeedbacksByHouseNameQuery(houseName);
        var Feedbacks = this.feedbackQueryService.handle(getFeedbackByHouseNameQuery);
        var feedbackResources = Feedbacks.stream()
                .map(FeedbackResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(feedbackResources);
    }


    @PutMapping("/{feedbackId}")
    public ResponseEntity<FeedbackResource> updateFeedback(@PathVariable Long feedbackId, @RequestBody FeedbackResource resource) {
        var updateFeedbackCommand = UpdateFeedbackCommandFromResourceAssembler.toCommandFromResource(feedbackId, resource);
        var optionalFeedback = this.feedbackCommandService.handle(updateFeedbackCommand);

        if (optionalFeedback.isEmpty())
            return ResponseEntity.badRequest().build();
        var feedbackResource = FeedbackResourceFromEntityAssembler.toResourceFromEntity(optionalFeedback.get());
        return ResponseEntity.ok(feedbackResource);
    }

    @DeleteMapping("/{feedbackId}")
    public ResponseEntity<?> deleteFeedback(@PathVariable Long feedbackId) {
        var deleteFeedbackCommand = new DeleteFeedbackCommand(feedbackId);
        this.feedbackCommandService.handle(deleteFeedbackCommand);
        return ResponseEntity.noContent().build();
    }


}
