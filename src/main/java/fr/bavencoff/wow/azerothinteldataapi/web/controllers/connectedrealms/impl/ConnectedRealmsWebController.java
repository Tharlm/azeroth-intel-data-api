package fr.bavencoff.wow.azerothinteldataapi.web.controllers.connectedrealms.impl;

import fr.bavencoff.wow.azerothinteldataapi.web.controllers.connectedrealms.dto.getall.GetAllConnectedRealmResponseDto;
import fr.bavencoff.wow.azerothinteldataapi.web.controllers.connectedrealms.dto.post.PostConnectedRealmRequestDto;
import fr.bavencoff.wow.azerothinteldataapi.web.controllers.connectedrealms.dto.post.PostConnectedRealmResponseDto;
import fr.bavencoff.wow.azerothinteldataapi.web.controllers.connectedrealms.dto.update.PutConnectedRealmRequestDto;
import fr.bavencoff.wow.azerothinteldataapi.web.controllers.connectedrealms.dto.update.PutConnectedRealmResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/connectedrealms")
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
     * @return A DTO containing a list of all connected realms.
     */
    @Operation(summary = "Fetch all connected realms", description = "Retrieve all connected realms stored in the system.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved connected realms"),
            @ApiResponse(responseCode = "500", description = "Internal server error while retrieving connected realms")
    })

    @GetMapping(path = "")
    public GetAllConnectedRealmResponseDto findAll() {
        return this.connectedRealmsService.findAll();
    }

    /**
     * Create a new connected realm record.
     *
     * @param request The DTO containing connected realm data.
     * @return A DTO containing details of the newly created connected realm.
     */
    @Operation(summary = "Create a connected realm", description = "Add a new connected realm to the system.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Connected realm created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input provided for creating a connected realm"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })

    @PostMapping(path = "")
    public PostConnectedRealmResponseDto postConnectedRealm(
            @Valid @RequestBody final PostConnectedRealmRequestDto request
    ) {
        return this.connectedRealmsService.post(request);
    }

    /**
     * Update an existing connected realm record.
     *
     * @param id                          The ID of the connected realm to update.
     * @param idRegion                    The region ID to associate with the connected realm.
     * @param putConnectedRealmRequestDto DTO containing the updated connected realm data.
     * @return A DTO containing details of the updated connected realm.
     */
    @Operation(summary = "Update a connected realm", description = "Modify the details of an existing connected realm.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully updated the connected realm"),
            @ApiResponse(responseCode = "400", description = "Invalid input provided for updating a connected realm"),
            @ApiResponse(responseCode = "404", description = "Connected realm not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })

    @PutMapping(path = "{id}/region/{idRegion}")
    public PutConnectedRealmResponseDto updateConnectedRealm(
            @PathVariable("id") @Min(0) @Max(Integer.MAX_VALUE) @NotNull final Integer id,
            @PathVariable("idRegion") @Min(0) @Max(Short.MAX_VALUE) @NotNull final Short idRegion,
            @Valid @RequestBody final PutConnectedRealmRequestDto putConnectedRealmRequestDto
    ) {
        return this.connectedRealmsService.put(id, idRegion, putConnectedRealmRequestDto);
    }
}
