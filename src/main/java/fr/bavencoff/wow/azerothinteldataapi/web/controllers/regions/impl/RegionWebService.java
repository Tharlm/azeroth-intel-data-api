package fr.bavencoff.wow.azerothinteldataapi.web.controllers.regions.impl;

import fr.bavencoff.wow.azerothinteldataapi.helpers.regions.impl.RegionApiService;
import fr.bavencoff.wow.azerothinteldataapi.web.controllers.regions.dto.GetRegionResponseDto;
import fr.bavencoff.wow.azerothinteldataapi.web.controllers.regions.dto.GetRegionsResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class RegionWebService {

    private final RegionApiService serviceExporter;
    private final RegionWebMapper mapper;

    @Autowired
    public RegionWebService(
            final RegionApiService serviceExporter,
            final RegionWebMapper mapper
    ) {
        this.serviceExporter = serviceExporter;
        this.mapper = mapper;
    }

    public GetRegionResponseDto getRegion(
            Short id
    ) {
        log.info("Getting region with id {}", id);
        return mapper.apiToDto(this.serviceExporter.getRegion(id));
    }

    public GetRegionsResponseDto getRegions() {
        return mapper.apisToDtos(this.serviceExporter.getRegions());
    }
}
