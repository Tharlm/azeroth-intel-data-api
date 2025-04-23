package fr.bavencoff.wow.azerothinteldataapi.web.controllers.regions.impl;

import fr.bavencoff.wow.azerothinteldataapi.web.controllers.regions.dto.GetRegionResponseDto;
import fr.bavencoff.wow.azerothinteldataapi.web.controllers.regions.dto.GetRegionsResponseDto;
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
 * Controller class for managing regions.
 * This controller provides endpoints to retrieve information about World of Warcraft regions.
 * All endpoints return data in JSON format and follow RESTful principles.
 */
@RestController
@RequestMapping("regions")
@Tag(name = "Regions", description = "Information sur les régions")
@Validated
public class RegionWebController {

    private final RegionWebService service;

    @Autowired
    public RegionWebController(RegionWebService service) {
        this.service = service;
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(
        summary = "Get a region by ID",
        description = "Retrieves detailed information about a specific World of Warcraft region by its unique identifier"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200", 
            description = "Region found",
            content = @Content(schema = @Schema(implementation = GetRegionResponseDto.class))
        ),
        @ApiResponse(
            responseCode = "404", 
            description = "Region not found",
            content = @Content(schema = @Schema(implementation = ProblemDetail.class))
        ),
        @ApiResponse(
            responseCode = "400", 
            description = "Invalid ID supplied",
            content = @Content(schema = @Schema(implementation = ProblemDetail.class))
        )
    })
    public ResponseEntity<GetRegionResponseDto> getRegion(
            @Parameter(description = "Unique identifier of the region", example = "1")
            @Max(value = Short.MAX_VALUE, message = "Region ID must be less than or equal to 32767") 
            @Min(value = 0, message = "Region ID must be greater than or equal to 0") 
            @NotNull(message = "Region ID cannot be null")
            @PathVariable Short id
    ) {
        return ResponseEntity.ok(this.service.getRegion(id));
    }

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(
        summary = "Get all regions",
        description = "Retrieves a list of all available World of Warcraft regions"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200", 
            description = "Successful operation",
            content = @Content(schema = @Schema(implementation = GetRegionsResponseDto.class))
        )
    })
    public ResponseEntity<GetRegionsResponseDto> getRegions() {
        return ResponseEntity.ok(this.service.getRegions());
    }
}
