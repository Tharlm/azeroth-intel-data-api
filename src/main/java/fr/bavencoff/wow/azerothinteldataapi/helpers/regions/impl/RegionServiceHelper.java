package fr.bavencoff.wow.azerothinteldataapi.helpers.regions.impl;

import fr.bavencoff.wow.azerothinteldataapi.helpers.regions.model.RegionBo;
import fr.bavencoff.wow.azerothinteldataapi.web.controllers.regions.exceptions.RegionNotFoundResponseException;

import java.util.List;

/**
 * Service interface for accessing region data at the API layer.
 * <p>
 * This interface defines methods for retrieving region information from the data access layer
 * and provides it to the web layer in the form of RegionApi objects. It acts as an intermediary
 * between the web services and the data access layer.
 */
public interface RegionServiceHelper {

    /**
     * Retrieves a specific region by its ID.
     *
     * @param regionId The unique identifier of the region to retrieve
     * @return The RegionApi object containing the region's information
     * @throws RegionNotFoundResponseException if the region with the specified ID is not found
     */
    RegionBo getRegion(Short regionId);

    /**
     * Retrieves all available regions.
     *
     * @return A list of RegionApi objects containing information about all regions
     */
    List<RegionBo> getRegions();
}
