package fr.bavencoff.wow.azerothinteldataapi.helpers.regions.impl;

import fr.bavencoff.wow.azerothinteldataapi.db.postaze.region.impl.RegionDaoServiceExporter;
import fr.bavencoff.wow.azerothinteldataapi.helpers.regions.model.RegionApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Implementation of the RegionApiService interface.
 * 
 * This service is responsible for:
 * 1. Retrieving region data from the data access layer through RegionDaoServiceExporter
 * 2. Converting DAO entities to API models using RegionApiMapper
 * 3. Providing the API models to the web layer
 * 
 * It acts as a bridge between the data access layer and the web layer,
 * ensuring proper separation of concerns and data transformation.
 */
@Service
public class RegionApiServiceImpl implements RegionApiService {

    private final RegionDaoServiceExporter daoServiceExporter;
    private final RegionApiMapper mapper;

    /**
     * Constructs a new RegionApiServiceImpl with the required dependencies.
     *
     * @param daoServiceExporter The service that provides access to region data from the database
     * @param mapper The mapper that converts between DAO entities and API models
     */
    @Autowired
    public RegionApiServiceImpl(
            final RegionDaoServiceExporter daoServiceExporter,
            final RegionApiMapper mapper
    ) {
        this.daoServiceExporter = daoServiceExporter;
        this.mapper = mapper;
    }

    /**
     * {@inheritDoc}
     * 
     * This implementation retrieves the region from the database using the DAO service exporter
     * and converts it to an API model using the mapper.
     * 
     * @throws fr.bavencoff.wow.azerothinteldataapi.web.controllers.regions.exceptions.RegionNotFoundResponseException 
     *         if the region with the specified ID is not found in the database
     */
    @Override
    public RegionApi getRegion(Short regionId) {
        return this.mapper.daoToApi(this.daoServiceExporter.findById(regionId));
    }

    /**
     * {@inheritDoc}
     * 
     * This implementation retrieves all regions from the database using the DAO service exporter
     * and converts them to API models using the mapper.
     */
    @Override
    public List<RegionApi> getRegions() {
        return this.mapper.daosToApis(this.daoServiceExporter.findAll());
    }
}
