package fr.bavencoff.wow.azerothinteldataapi.common.mappers;

import fr.bavencoff.wow.azerothinteldataapi.common.enums.GlobalRegion;
import fr.bavencoff.wow.azerothinteldataapi.helpers.parameters.model.ParameterBo;
import fr.bavencoff.wow.azerothinteldataapi.helpers.regions.model.RegionBo;
import org.mapstruct.Mapper;

@Mapper(
        config = AzerothMapperParent.class
)
public interface GeneralBusinessObjetMapper {

    default String parameterApiToString(ParameterBo api) {
        if (api == null) {
            return null;
        }
        return api.getType();
    }

    default GlobalRegion regionApiToGlobalRegion(RegionBo regionBo) {
        if (regionBo == null) {
            return null;
        }
        return regionBo.getTag();
    }

}
