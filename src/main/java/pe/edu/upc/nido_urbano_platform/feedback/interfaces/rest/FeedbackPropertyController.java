package pe.edu.upc.nido_urbano_platform.feedback.interfaces.rest;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.nido_urbano_platform.feedback.domain.model.queries.GetAllFeedbacksByPropertyIdQuery;
import pe.edu.upc.nido_urbano_platform.feedback.domain.model.valueobjects.PropertyId;
import pe.edu.upc.nido_urbano_platform.feedback.domain.services.FeedbackCommandService;
import pe.edu.upc.nido_urbano_platform.feedback.domain.services.FeedbackQueryService;
import pe.edu.upc.nido_urbano_platform.feedback.interfaces.rest.resources.FeedbackResource;
import pe.edu.upc.nido_urbano_platform.feedback.interfaces.rest.transform.FeedbackResourceFromEntityAssembler;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", methods = { RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT, RequestMethod.DELETE })
@RestController
@RequestMapping(value = "/api/v1/properties/{propertyId}/feedbacks", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Feedbacks", description = "Feedback on Management Endpoints")
public class FeedbackPropertyController {
    private final FeedbackQueryService feedbackQueryService;
    private final FeedbackCommandService feedbackCommandService;

    public FeedbackPropertyController(FeedbackQueryService profileQueryService, FeedbackCommandService profileCommandService) {
        this.feedbackQueryService = profileQueryService;
        this.feedbackCommandService = profileCommandService;
    }



    @GetMapping
    public ResponseEntity<List<FeedbackResource>> getAllFeedbackByPropertyId(@PathVariable Long propertyId) {
        var getFeedbackByPropertyIdQuery = new GetAllFeedbacksByPropertyIdQuery(new PropertyId(propertyId));
        var Feedbacks = this.feedbackQueryService.handle(getFeedbackByPropertyIdQuery);
        var feedbackResources = Feedbacks.stream()
                .map(FeedbackResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(feedbackResources);
    }



}
