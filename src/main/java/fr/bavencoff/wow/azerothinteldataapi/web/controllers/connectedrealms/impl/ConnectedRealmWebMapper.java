package fr.bavencoff.wow.azerothinteldataapi.web.controllers.connectedrealms.impl;

import fr.bavencoff.wow.azerothinteldataapi.common.mappers.AzerothMapperParent;
import fr.bavencoff.wow.azerothinteldataapi.common.mappers.GeneralBusinessObjetMapper;
import fr.bavencoff.wow.azerothinteldataapi.helpers.realms.model.ConnectedRealmBo;
import fr.bavencoff.wow.azerothinteldataapi.helpers.realms.model.RealmBo;
import fr.bavencoff.wow.azerothinteldataapi.helpers.realms.model.UpdateCrModel;
import fr.bavencoff.wow.azerothinteldataapi.web.controllers.connectedrealms.dto.get.GetConnectedRealmRealmDto;
import fr.bavencoff.wow.azerothinteldataapi.web.controllers.connectedrealms.dto.get.GetConnectedRealmResponseDto;
import fr.bavencoff.wow.azerothinteldataapi.web.controllers.connectedrealms.dto.getall.GetAllConnectedRealmResponseDto;
import fr.bavencoff.wow.azerothinteldataapi.web.controllers.connectedrealms.dto.post.PostConnectedRealmRequestDto;
import fr.bavencoff.wow.azerothinteldataapi.web.controllers.connectedrealms.dto.post.PostConnectedRealmRequestRealmInfoDto;
import fr.bavencoff.wow.azerothinteldataapi.web.controllers.connectedrealms.dto.update.PutConnectedRealmRequestDto;
import fr.bavencoff.wow.azerothinteldataapi.web.controllers.connectedrealms.dto.update.PutConnectedRealmRequestRealmInfoDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(config = AzerothMapperParent.class, uses = {
        GeneralBusinessObjetMapper.class
})
public interface ConnectedRealmWebMapper {

    List<GetAllConnectedRealmResponseDto.GetAllConnectedRealmResultDto> apisToDtos(
            List<ConnectedRealmBo> realmDaos
    );

    List<GetAllConnectedRealmResponseDto.GetAllConnectedRealmRealmDto> realmsApisToDtos(
            List<RealmBo> realms
    );

    UpdateCrModel dtoToUpdateCrModel(PostConnectedRealmRequestDto request);

    @Mapping(target = "infos", source = "dto")
    UpdateCrModel.RealmInfoModel dtoToRealmInfoModel(PostConnectedRealmRequestRealmInfoDto dto);

    UpdateCrModel dtoToUpdateCrModel(PutConnectedRealmRequestDto request);

    @Mapping(target = "infos", source = "dto")
    UpdateCrModel.RealmInfoModel dtoToRealmInfoModel(PutConnectedRealmRequestRealmInfoDto dto);

    GetConnectedRealmResponseDto boToGetConnectedRealmResponseDto(ConnectedRealmBo bo);

    GetConnectedRealmRealmDto boToGetConnectedRealmResponseDto(RealmBo bo);

}
