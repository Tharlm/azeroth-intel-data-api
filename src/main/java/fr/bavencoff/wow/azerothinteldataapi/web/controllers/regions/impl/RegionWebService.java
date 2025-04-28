package fr.bavencoff.wow.azerothinteldataapi.web.controllers.regions.impl;

import fr.bavencoff.wow.azerothinteldataapi.helpers.regions.impl.RegionServiceHelper;
import fr.bavencoff.wow.azerothinteldataapi.web.controllers.regions.dto.GetRegionResponseDto;
import fr.bavencoff.wow.azerothinteldataapi.web.controllers.regions.dto.GetRegionsResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service class that handles web-related operations for regions.
 * <p>
 * This service acts as an intermediary between the controller layer and the API service layer.
 * It's responsible for:
 * 1. Receiving requests from the controller
 * 2. Delegating data retrieval to the API service
 * 3. Converting API models to DTOs using the mapper
 * 4. Returning the appropriate response DTOs to the controller
 * <p>
 * The service logs all operations for debugging and monitoring purposes.
 */
@Slf4j
@Service
public class RegionWebService {

    private final RegionServiceHelper serviceExporter;
    private final RegionWebMapper mapper;

    /**
     * Constructs a new RegionWebService with the required dependencies.
     *
     * @param serviceExporter The API service that provides access to region data
     * @param mapper          The mapper that converts between API models and DTOs
     */
    @Autowired
    public RegionWebService(
            final RegionServiceHelper serviceExporter,
            final RegionWebMapper mapper
    ) {
        this.serviceExporter = serviceExporter;
        this.mapper = mapper;
    }

    /**
     * Retrieves a specific region by its ID.
     *
     * @param id The unique identifier of the region to retrieve
     * @return A DTO containing the region's information
     * @throws fr.bavencoff.wow.azerothinteldataapi.web.controllers.regions.exceptions.RegionNotFoundResponseException if the region is not found
     */
    public GetRegionResponseDto getRegion(
            Short id
    ) {
        log.debug("Getting region with id {}", id);
        return mapper.apiToDto(this.serviceExporter.getRegion(id));
    }

    /**
     * Retrieves all available regions.
     *
     * @return A DTO containing a list of all regions
     */
    public GetRegionsResponseDto getRegions() {
        log.debug("Retrieve all regions");
        return mapper.apisToDtos(this.serviceExporter.getRegions());
    }
}
