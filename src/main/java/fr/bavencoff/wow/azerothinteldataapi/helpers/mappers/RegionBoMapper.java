package fr.bavencoff.wow.azerothinteldataapi.helpers.mappers;

import fr.bavencoff.wow.azerothinteldataapi.common.mappers.AzerothMapperParent;
import fr.bavencoff.wow.azerothinteldataapi.db.postaze.region.dao.RegionDao;
import fr.bavencoff.wow.azerothinteldataapi.helpers.regions.model.RegionBo;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * Mapper interface for converting between DAO entities and API models in the region API layer.
 * <p>
 * This mapper is responsible for:
 * 1. Converting RegionDao objects to RegionApi objects
 * 2. Converting lists of RegionDao objects to lists of RegionApi objects
 * <p>
 * It uses MapStruct for automatic implementation of the mapping methods based on
 * field names and types. The mapper inherits common mapping configurations from
 * AzerothMapperParent.
 */
@Mapper(
        config = AzerothMapperParent.class
)
public interface RegionBoMapper {

    /**
     * Converts a single RegionDao object to a RegionApi.
     *
     * @param dao The RegionDao object to convert
     * @return The corresponding RegionApi
     */
    RegionBo daoToApi(RegionDao dao);

    /**
     * Converts a list of RegionDao objects to a list of RegionApi objects.
     *
     * @param daos The list of RegionDao objects to convert
     * @return A list of corresponding RegionApi objects
     */
    List<RegionBo> daosToApis(List<RegionDao> daos);
}
