package fr.bavencoff.wow.azerothinteldataapi.helpers.regions.impl;

import fr.bavencoff.wow.azerothinteldataapi.helpers.regions.model.RegionApi;

import java.util.List;

public interface RegionApiService {
    RegionApi getRegion(Short regionId);
    List<RegionApi> getRegions();
}
