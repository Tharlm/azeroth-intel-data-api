package fr.bavencoff.wow.azerothinteldataapi.helpers.mythickeys.impl;

import fr.bavencoff.wow.azerothinteldataapi.common.mappers.GenericMapper;
import fr.bavencoff.wow.azerothinteldataapi.db.postaze.mkperiod.dao.MkPeriod;
import fr.bavencoff.wow.azerothinteldataapi.helpers.mythickeys.model.MkPeriodApi;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING
)
public interface MkPeriodApiMapper extends GenericMapper<MkPeriod, MkPeriodApi> {
}
