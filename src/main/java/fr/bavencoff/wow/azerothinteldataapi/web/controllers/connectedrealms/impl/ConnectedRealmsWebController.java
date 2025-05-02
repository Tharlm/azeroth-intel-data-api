package fr.bavencoff.wow.azerothinteldataapi.web.controllers.connectedrealms.impl;

import fr.bavencoff.wow.azerothinteldataapi.common.enums.GlobalRegion;
import fr.bavencoff.wow.azerothinteldataapi.web.controllers.connectedrealms.dto.get.GetConnectedRealmResponseDto;
import fr.bavencoff.wow.azerothinteldataapi.web.controllers.connectedrealms.dto.getall.GetAllConnectedRealmResponseDto;
import fr.bavencoff.wow.azerothinteldataapi.web.controllers.connectedrealms.dto.post.PostConnectedRealmRequestDto;
import fr.bavencoff.wow.azerothinteldataapi.web.controllers.connectedrealms.dto.post.PostConnectedRealmResponseDto;
import fr.bavencoff.wow.azerothinteldataapi.web.controllers.connectedrealms.dto.update.PutConnectedRealmRequestDto;
import fr.bavencoff.wow.azerothinteldataapi.web.controllers.connectedrealms.dto.update.PutConnectedRealmResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller class for managing connected realms.
 * This controller provides endpoints to retrieve, create, and update information about World of Warcraft connected realms.
 * All endpoints return data in JSON format and follow RESTful principles.
 */
@RestController
@RequestMapping("/connectedrealms")
@Tag(name = "Connected Realms", description = "Information about World of Warcraft connected realms")
@Validated
public class ConnectedRealmsWebController {

    private final ConnectedRealmsWebService connectedRealmsService;

    @Autowired
    public ConnectedRealmsWebController(
            final ConnectedRealmsWebService connectedRealmsService
    ) {
        this.connectedRealmsService = connectedRealmsService;
    }

    /**
     * Fetch all connected realms.
     *
     * @param region Optional parameter to filter connected realms by global region
     * @return A DTO containing a list of all connected realms.
     */
    @Operation(summary = "Fetch all connected realms", description = "Retrieve all connected realms stored in the system. Optionally filter by global region.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Successfully retrieved connected realms",
                    content = @Content(schema = @Schema(implementation = GetAllConnectedRealmResponseDto.class))
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal server error while retrieving connected realms",
                    content = @Content(schema = @Schema(implementation = ProblemDetail.class))
            )
    })
    @GetMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GetAllConnectedRealmResponseDto> findAll(
            @Parameter(description = "Optional global region to filter connected realms")
            @RequestParam(required = false) GlobalRegion region
    ) {
        return ResponseEntity.ok(this.connectedRealmsService.findAll(region));
    }

    /**
     * Create a new connected realm record.
     *
     * @param request The DTO containing connected realm data.
     * @return A DTO containing details of the newly created connected realm.
     */
    @Operation(summary = "Create a connected realm", description = "Add a new connected realm to the system.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Connected realm created successfully",
                    content = @Content(schema = @Schema(implementation = PostConnectedRealmResponseDto.class))
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid input provided for creating a connected realm",
                    content = @Content(schema = @Schema(implementation = ProblemDetail.class))
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal server error",
                    content = @Content(schema = @Schema(implementation = ProblemDetail.class))
            )
    })
    @PostMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PostConnectedRealmResponseDto> postConnectedRealm(
            @Parameter(description = "Connected realm data to create", required = true)
            @Valid @RequestBody final PostConnectedRealmRequestDto request
    ) {
        return ResponseEntity.status(201).body(this.connectedRealmsService.post(request));
    }

    /**
     * Update an existing connected realm record.
     *
     * @param id                          The ID of the connected realm to update.
     * @param region                      The region ID to associate with the connected realm.
     * @param putConnectedRealmRequestDto DTO containing the updated connected realm data.
     * @return A DTO containing details of the updated connected realm.
     */
    @Operation(summary = "Update a connected realm", description = "Modify the details of an existing connected realm.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Successfully updated the connected realm",
                    content = @Content(schema = @Schema(implementation = PutConnectedRealmResponseDto.class))
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid input provided for updating a connected realm",
                    content = @Content(schema = @Schema(implementation = ProblemDetail.class))
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Connected realm not found",
                    content = @Content(schema = @Schema(implementation = ProblemDetail.class))
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal server error",
                    content = @Content(schema = @Schema(implementation = ProblemDetail.class))
            )
    })
    @PutMapping(path = "{id}/region/{region}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PutConnectedRealmResponseDto> updateConnectedRealm(
            @Parameter(description = "Unique identifier of the connected realm", example = "1")
            @PathVariable("id") @Min(value = 0, message = "Connected realm ID must be greater than or equal to 0")
            @Max(value = Integer.MAX_VALUE, message = "Connected realm ID must be valid")
            @NotNull(message = "Connected realm ID cannot be null") final Integer id,

            @Parameter(description = "Region of the connected realm")
            @PathVariable("region") @NotNull GlobalRegion region,

            @Parameter(description = "Updated connected realm data", required = true)
            @Valid @RequestBody final PutConnectedRealmRequestDto putConnectedRealmRequestDto
    ) {
        return ResponseEntity.ok(this.connectedRealmsService.put(id, region, putConnectedRealmRequestDto));
    }

    @Operation(summary = "Get a connected realm", description = "Retrieve details of a specific connected realm by ID and region.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Successfully retrieved the connected realm",
                    content = @Content(schema = @Schema(implementation = GetConnectedRealmResponseDto.class))
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Connected realm not found",
                    content = @Content(schema = @Schema(implementation = ProblemDetail.class))
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid input provided",
                    content = @Content(schema = @Schema(implementation = ProblemDetail.class))
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal server error",
                    content = @Content(schema = @Schema(implementation = ProblemDetail.class))
            )
    })
    @GetMapping(path = "{id}/region/{region}", produces = MediaType.APPLICATION_JSON_VALUE)
    public GetConnectedRealmResponseDto getConnectedRealm(
            @Parameter(description = "Unique identifier of the connected realm", example = "1")
            @PathVariable("id") @Min(value = 0, message = "Connected realm ID must be greater than or equal to 0")
            @Max(value = Integer.MAX_VALUE, message = "Connected realm ID must be valid")
            @NotNull(message = "Connected realm ID cannot be null") final Integer id,
            @Parameter(description = "Region of the connected realm")
            @PathVariable("region") @NotNull GlobalRegion region
    ) {
        return this.connectedRealmsService.getConnectedRealm(id, region);
    }
}
