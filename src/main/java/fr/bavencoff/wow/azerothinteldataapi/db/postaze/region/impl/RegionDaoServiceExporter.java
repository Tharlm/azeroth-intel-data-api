package fr.bavencoff.wow.azerothinteldataapi.db.postaze.region.impl;

import fr.bavencoff.wow.azerothinteldataapi.common.enums.GlobalRegion;
import fr.bavencoff.wow.azerothinteldataapi.db.postaze.region.dao.RegionDao;
import fr.bavencoff.wow.azerothinteldataapi.web.controllers.regions.exceptions.RegionNotFoundResponseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service class that provides access to region data from the database.
 * <p>
 * This service is responsible for:
 * 1. Retrieving region data from the database through RegionDaoRepository
 * 2. Caching frequently accessed data to improve performance
 * 3. Handling not-found scenarios by throwing appropriate exceptions
 * 4. Providing a clean API for accessing region data to higher layers
 * <p>
 * It acts as the primary interface between the database and the application's
 * business logic for region-related operations.
 */
@Service
public class RegionDaoServiceExporter {
    private final RegionDaoRepository repository;

    /**
     * Constructs a new RegionDaoServiceExporter with the required dependencies.
     *
     * @param repository The repository that provides direct access to the region database table
     */
    @Autowired
    public RegionDaoServiceExporter(
            final RegionDaoRepository repository
    ) {
        this.repository = repository;
    }

    /**
     * Retrieves a specific region by its ID.
     * <p>
     * This method is cached to improve performance for frequently accessed regions.
     * If the region is not found, a RegionNotFoundResponseException is thrown.
     *
     * @param id The unique identifier of the region to retrieve
     * @return The RegionDao object containing the region's information
     * @throws RegionNotFoundResponseException if the region with the specified ID is not found
     */
    @Cacheable("findRegionDaoById")
    public RegionDao findById(Short id) {
        Optional<RegionDao> regionDao = this.findOptionalById(id);
        if (regionDao.isEmpty()) {
            throw new RegionNotFoundResponseException(id);
        }
        return regionDao.get();
    }

    /**
     * Retrieves an Optional containing a region by its ID.
     * <p>
     * This method provides a way to check for existence without throwing exceptions.
     *
     * @param id The unique identifier of the region to retrieve
     * @return An Optional containing the RegionDao if found, or empty if not found
     */
    public Optional<RegionDao> findOptionalById(Short id) {
        return repository.findById(id);
    }

    /**
     * Retrieves an Region by its tag
     *
     * @param tag Tag of the region (functional identifier)
     * @return RegionDao corresponding to tag
     */
    @Cacheable("findRegionDaoByTag")
    public RegionDao findByTag(GlobalRegion tag) {
        final List<RegionDao> regionDaos = this.repository.findByTag(tag);

        if (regionDaos.isEmpty()) {
            throw new RegionNotFoundResponseException(tag);
        }
        return regionDaos.getFirst();
    }

    /**
     * Retrieves all available regions.
     *
     * @return A list of RegionDao objects containing information about all regions
     */
    public List<RegionDao> findAll() {
        return this.repository.findAll();
    }
}
