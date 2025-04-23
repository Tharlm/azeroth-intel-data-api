package fr.bavencoff.wow.azerothinteldataapi.web.controllers.regions.impl;

import fr.bavencoff.wow.azerothinteldataapi.web.controllers.regions.dto.GetRegionResponseDto;
import fr.bavencoff.wow.azerothinteldataapi.web.controllers.regions.dto.GetRegionsResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller class for managing regions.
 */
@RestController
@RequestMapping("regions")
@Tag(name = "Regions", description = "Information sur les régions")
public class RegionWebController {

    private final RegionWebService service;

    @Autowired
    public RegionWebController(RegionWebService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    @Operation(description = "Get a region")
    public GetRegionResponseDto getRegion(
            @Parameter(description = "id of the region")
            @Max(Short.MAX_VALUE) @Min(0)
            @PathVariable Short id
    ) {
        return this.service.getRegion(id);
    }

    @GetMapping("")
    @Operation(description = "Get all regions")
    public GetRegionsResponseDto getRegions() {
        return this.service.getRegions();
    }
}
