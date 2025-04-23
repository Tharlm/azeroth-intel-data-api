package fr.bavencoff.wow.azerothinteldataapi.helpers.parameters.impl;

import fr.bavencoff.wow.azerothinteldataapi.common.mappers.GenericMapper;
import fr.bavencoff.wow.azerothinteldataapi.db.postaze.parameters.dao.ParameterTypeDao;
import fr.bavencoff.wow.azerothinteldataapi.helpers.parameters.model.ParameterApi;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING
)
public interface ParameterApiMapper extends GenericMapper<ParameterTypeDao, ParameterApi> {

    default String parameterTypeToString(ParameterTypeDao dao) {
        if (dao == null) {
            return null;
        }
        return dao.getType();
    }
}
