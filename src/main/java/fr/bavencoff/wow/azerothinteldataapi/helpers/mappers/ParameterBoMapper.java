package fr.bavencoff.wow.azerothinteldataapi.helpers.mappers;

import fr.bavencoff.wow.azerothinteldataapi.common.mappers.AzerothMapperParent;
import fr.bavencoff.wow.azerothinteldataapi.common.mappers.GenericMapper;
import fr.bavencoff.wow.azerothinteldataapi.db.postaze.parameters.dao.ParameterTypeDao;
import fr.bavencoff.wow.azerothinteldataapi.helpers.parameters.model.ParameterBo;
import org.mapstruct.Mapper;

@Mapper(
        config = AzerothMapperParent.class
)
public interface ParameterBoMapper extends GenericMapper<ParameterTypeDao, ParameterBo> {

    default String parameterTypeToString(ParameterTypeDao dao) {
        if (dao == null) {
            return null;
        }
        return dao.getType();
    }
}
