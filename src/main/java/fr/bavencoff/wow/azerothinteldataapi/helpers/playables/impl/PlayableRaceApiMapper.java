package fr.bavencoff.wow.azerothinteldataapi.helpers.playables.impl;

import fr.bavencoff.wow.azerothinteldataapi.db.postaze.playableclass.dao.PlayableClassDao;
import fr.bavencoff.wow.azerothinteldataapi.db.postaze.playablerace.dao.PlayableRaceDao;
import fr.bavencoff.wow.azerothinteldataapi.db.postaze.playablespe.dao.PlayableSpecializationDao;
import fr.bavencoff.wow.azerothinteldataapi.helpers.playables.model.PlayableClassApi;
import fr.bavencoff.wow.azerothinteldataapi.helpers.playables.model.PlayableRaceApi;
import fr.bavencoff.wow.azerothinteldataapi.helpers.playables.model.PlayableSpecializationApi;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.Named;

import java.util.List;


@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING
)
public interface PlayableRaceApiMapper {

    @Mapping(target = "classes", qualifiedByName = "withoutRaces")
    PlayableRaceApi daoToApi(PlayableRaceDao dao);

    @Named("withoutClasses")
    @Mapping(target = "classes", ignore = true)
    PlayableRaceApi daoToApiWithoutClasses(PlayableRaceDao dao);

    List<PlayableRaceApi> daosToApisPr(List<PlayableRaceDao> daos);

    @Mapping(target = "races", qualifiedByName = "withoutClasses")
    @Mapping(target = "spes", source = "specializations")
    PlayableClassApi daoToApi(PlayableClassDao dao);

    @Named("withoutRaces")
    @Mapping(target = "races", ignore = true)
    @Mapping(target = "spes", source = "specializations")
    PlayableClassApi daoToApiWithoutRaces(PlayableClassDao dao);

    List<PlayableClassApi> daosToApisPc(List<PlayableClassDao> daos);

    @Mapping(target = "primaryStatType", source = "primaryStat")
    @Mapping(target = "playableClass", source = "classDao")
    @Mapping(target = "playableClass.spes", ignore = true)
    @Mapping(target = "playableClass.races", ignore = true)
    PlayableSpecializationApi daoToApi(PlayableSpecializationDao dao);

    List<PlayableSpecializationApi> daosToApisPs(List<PlayableSpecializationDao> daos);

}
