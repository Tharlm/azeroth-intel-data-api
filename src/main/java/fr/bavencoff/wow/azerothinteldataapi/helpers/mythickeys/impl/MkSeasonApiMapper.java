package fr.bavencoff.wow.azerothinteldataapi.helpers.mythickeys.impl;


import fr.bavencoff.wow.azerothinteldataapi.common.mappers.GenericMapper;
import fr.bavencoff.wow.azerothinteldataapi.db.postaze.mkseason.dao.MkSeason;
import fr.bavencoff.wow.azerothinteldataapi.helpers.mythickeys.model.MkSeasonApi;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        uses = {
                MkPeriodApiMapper.class
        }
)
public interface MkSeasonApiMapper extends GenericMapper<MkSeason, MkSeasonApi> {


    @Override
    @Mapping(target = "periodInformations", source = "periods")
    MkSeasonApi daoToApi(MkSeason dao);
}
