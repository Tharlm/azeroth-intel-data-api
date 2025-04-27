package fr.bavencoff.wow.azerothinteldataapi.common.mappers;

import fr.bavencoff.wow.azerothinteldataapi.common.enums.GlobalRegion;
import fr.bavencoff.wow.azerothinteldataapi.helpers.parameters.model.ParameterApi;
import fr.bavencoff.wow.azerothinteldataapi.helpers.regions.model.RegionApi;
import org.mapstruct.Mapper;

@Mapper(
        config = AzerothMapperParent.class
)
public interface ParameterMapper {

    default String parameterApiToString(ParameterApi api) {
        if (api == null) {
            return null;
        }
        return api.getType();
    }

    default GlobalRegion regionApiToGlobalRegion(RegionApi regionApi) {
        if (regionApi == null) {
            return null;
        }
        return regionApi.getTag();
    }

}
