package fr.bavencoff.wow.azerothinteldataapi.helpers.realms.mappers;

import fr.bavencoff.wow.azerothinteldataapi.common.mappers.AzerothMapperParent;
import fr.bavencoff.wow.azerothinteldataapi.db.postaze.connectedrealms.dao.ConnectedRealmDao;
import fr.bavencoff.wow.azerothinteldataapi.db.postaze.realms.dao.RealmDao;
import fr.bavencoff.wow.azerothinteldataapi.helpers.parameters.impl.ParameterApiMapper;
import fr.bavencoff.wow.azerothinteldataapi.helpers.realms.model.ConnectedRealmBo;
import fr.bavencoff.wow.azerothinteldataapi.helpers.realms.model.RealmBo;
import fr.bavencoff.wow.azerothinteldataapi.helpers.regions.impl.RegionApiMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(
        config = AzerothMapperParent.class,
        uses = {
                RegionApiMapper.class,
                ParameterApiMapper.class
        }
)
public interface RealmBoMapper {
    List<RealmBo> daosToApis(List<RealmDao> daos);

    @Mapping(target = "connectedRealm", qualifiedByName = "withoutRealms")
    RealmBo daoToApi(RealmDao dao);

    @Mapping(target = "connectedRealm", ignore = true)
    @Named("withoutConnectedRealm")
    RealmBo daoToApiWithoutCr(RealmDao dao);


    List<ConnectedRealmBo> crDaosToApis(List<ConnectedRealmDao> daos);
    @Mapping(target = "realms", qualifiedByName = "withoutConnectedRealm")
    ConnectedRealmBo daoToApi(ConnectedRealmDao dao);

    @Mapping(target = "realms", ignore = true)
    @Named("withoutRealms")
    ConnectedRealmBo daoToApiWithoutRealm(ConnectedRealmDao dao);
}
