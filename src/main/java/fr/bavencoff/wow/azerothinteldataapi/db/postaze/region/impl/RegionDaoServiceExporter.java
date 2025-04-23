package fr.bavencoff.wow.azerothinteldataapi.db.postaze.region.impl;

import fr.bavencoff.wow.azerothinteldataapi.db.postaze.region.dao.RegionDao;
import fr.bavencoff.wow.azerothinteldataapi.web.controllers.regions.exceptions.RegionNotFoundResponseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RegionDaoServiceExporter {
    private final RegionDaoRepository repository;

    @Autowired
    public RegionDaoServiceExporter(
            final RegionDaoRepository repository
    ) {
        this.repository = repository;
    }

    @Cacheable("findRegionDaoById")
    public RegionDao findById(Short id) {
        Optional<RegionDao> regionDao = this.findOptionalById(id);
        if (regionDao.isEmpty()) {
            throw new RegionNotFoundResponseException(id);
        }
        return regionDao.get();
    }

    public Optional<RegionDao> findOptionalById(Short id) {
        return repository.findById(id);
    }

    public List<RegionDao> findAll() {
        return this.repository.findAll();
    }
}
