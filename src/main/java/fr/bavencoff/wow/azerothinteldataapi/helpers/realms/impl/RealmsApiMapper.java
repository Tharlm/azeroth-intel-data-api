package fr.bavencoff.wow.azerothinteldataapi.helpers.realms.impl;

import fr.bavencoff.wow.azerothinteldataapi.common.mappers.GenericMapper;
import fr.bavencoff.wow.azerothinteldataapi.helpers.parameters.impl.ParameterApiMapper;
import fr.bavencoff.wow.azerothinteldataapi.db.postaze.realms.dao.RealmDao;
import fr.bavencoff.wow.azerothinteldataapi.helpers.realms.model.RealmApi;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        uses = {
                ParameterApiMapper.class
        }
)
public interface RealmsApiMapper extends GenericMapper<RealmDao, RealmApi> {
}
