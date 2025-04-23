package fr.bavencoff.wow.azerothinteldataapi.helpers.regions.impl;

import fr.bavencoff.wow.azerothinteldataapi.db.postaze.region.impl.RegionDaoServiceExporter;
import fr.bavencoff.wow.azerothinteldataapi.helpers.regions.model.RegionApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegionApiServiceImpl implements RegionApiService {

    private final RegionDaoServiceExporter daoServiceExporter;
    private final RegionApiMapper mapper;

    @Autowired
    public RegionApiServiceImpl(
            final RegionDaoServiceExporter daoServiceExporter,
            final RegionApiMapper mapper
    ) {
        this.daoServiceExporter = daoServiceExporter;
        this.mapper = mapper;
    }

    @Override
    public RegionApi getRegion(Short regionId) {
        return this.mapper.daoToApi(this.daoServiceExporter.findById(regionId));
    }

    @Override
    public List<RegionApi> getRegions() {
        return this.mapper.daosToApis(this.daoServiceExporter.findAll());
    }
}
