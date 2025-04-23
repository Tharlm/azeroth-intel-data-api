package fr.bavencoff.wow.azerothinteldataapi.helpers.regions.impl;

import fr.bavencoff.wow.azerothinteldataapi.db.postaze.region.dao.RegionDao;
import fr.bavencoff.wow.azerothinteldataapi.helpers.regions.model.RegionApi;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING
)
public interface RegionApiMapper {

    RegionApi daoToApi(RegionDao dao);

    List<RegionApi> daosToApis(List<RegionDao> daos);
}
