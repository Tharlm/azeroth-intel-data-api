package fr.bavencoff.wow.azerothinteldataapi.helpers.realms.impl;

import fr.bavencoff.wow.azerothinteldataapi.helpers.parameters.impl.ParameterApiMapper;
import fr.bavencoff.wow.azerothinteldataapi.db.postaze.connectedrealms.dao.ConnectedRealmDao;
import fr.bavencoff.wow.azerothinteldataapi.db.postaze.realms.dao.RealmDao;
import fr.bavencoff.wow.azerothinteldataapi.helpers.realms.model.ConnectedRealmApi;
import fr.bavencoff.wow.azerothinteldataapi.helpers.realms.model.RealmApi;
import fr.bavencoff.wow.azerothinteldataapi.helpers.regions.impl.RegionApiMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.Named;

import java.util.List;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        uses = {
                RegionApiMapper.class,
                ParameterApiMapper.class
        }
)
public interface RealmUtilsApiMapper {
    List<RealmApi> daosToApis(List<RealmDao> daos);

    @Mapping(target = "connectedRealm", qualifiedByName = "withoutRealms")
    RealmApi daoToApi(RealmDao dao);

    @Mapping(target = "connectedRealm", ignore = true)
    @Named("withoutConnectedRealm")
    RealmApi daoToApiWithoutCr(RealmDao dao);


    List<ConnectedRealmApi> crDaosToApis(List<ConnectedRealmDao> daos);
    @Mapping(target = "realms", qualifiedByName = "withoutConnectedRealm")
    ConnectedRealmApi daoToApi(ConnectedRealmDao dao);

    @Mapping(target = "realms", ignore = true)
    @Named("withoutRealms")
    ConnectedRealmApi daoToApiWithoutRealm(ConnectedRealmDao dao);
}
