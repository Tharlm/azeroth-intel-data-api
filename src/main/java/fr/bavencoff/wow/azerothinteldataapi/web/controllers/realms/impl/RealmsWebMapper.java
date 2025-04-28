package fr.bavencoff.wow.azerothinteldataapi.web.controllers.realms.impl;

import fr.bavencoff.wow.azerothinteldataapi.common.mappers.AzerothMapperParent;
import fr.bavencoff.wow.azerothinteldataapi.common.mappers.GeneralBusinessObjetMapper;
import fr.bavencoff.wow.azerothinteldataapi.helpers.realms.model.RealmBo;
import fr.bavencoff.wow.azerothinteldataapi.web.controllers.realms.dto.get.GetRealmResponseDto;
import fr.bavencoff.wow.azerothinteldataapi.web.controllers.realms.dto.getall.RealmResultDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(config = AzerothMapperParent.class, uses = {
        GeneralBusinessObjetMapper.class
})
public interface RealmsWebMapper {

    GetRealmResponseDto castViewToGetRealmResponse(RealmBo view);

    List<RealmResultDto> apisToDtos(List<RealmBo> apis);

}
