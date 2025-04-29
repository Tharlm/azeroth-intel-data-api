package fr.bavencoff.wow.azerothinteldataapi.web.controllers.realms.impl;

import fr.bavencoff.wow.azerothinteldataapi.web.controllers.realms.dto.get.GetRealmResponseDto;
import fr.bavencoff.wow.azerothinteldataapi.web.controllers.realms.dto.getall.GetAllRealmResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller class for managing realms.
 * This controller provides endpoints to retrieve information about World of Warcraft realms.
 * All endpoints return data in JSON format and follow RESTful principles.
 */
@RestController
@RequestMapping("/realms")
@Tag(name = "Realms", description = "Information about World of Warcraft realms")
@Validated
public class RealmsWebController {

    private final RealmsWebServiceExporter service;

    @Autowired
    public RealmsWebController(RealmsWebServiceExporter service) {
        this.service = service;
    }

    /**
     * Get a specific realm by its ID.
     *
     * @param id The unique identifier of the realm
     * @return Detailed information about the requested realm
     */
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(
            summary = "Get a realm by ID",
            description = "Retrieves detailed information about a specific World of Warcraft realm by its unique identifier"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Realm found",
                    content = @Content(schema = @Schema(implementation = GetRealmResponseDto.class))
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Realm not found",
                    content = @Content(schema = @Schema(implementation = ProblemDetail.class))
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid ID supplied",
                    content = @Content(schema = @Schema(implementation = ProblemDetail.class))
            )
    })
    public ResponseEntity<GetRealmResponseDto> getRealm(
            @Parameter(description = "Unique identifier of the realm", example = "1")
            @Max(value = Integer.MAX_VALUE, message = "Realm ID must be valid")
            @Min(value = 0, message = "Realm ID must be greater than or equal to 0")
            @NotNull(message = "Realm ID cannot be null")
            @PathVariable(name = "id") Integer id
    ) {
        return ResponseEntity.ok(this.service.get(id));
    }

    /**
     * Get all available realms.
     *
     * @return A list of all World of Warcraft realms
     */
    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(
            summary = "Get all realms",
            description = "Retrieves a list of all available World of Warcraft realms"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Successful operation",
                    content = @Content(schema = @Schema(implementation = GetAllRealmResponseDto.class))
            )
    })
    public ResponseEntity<GetAllRealmResponseDto> getRealms() {
        return ResponseEntity.ok(this.service.findAll());
    }

}
