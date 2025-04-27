package fr.bavencoff.wow.azerothinteldataapi.web.controllers.connectedrealms.impl;

import fr.bavencoff.wow.azerothinteldataapi.common.mappers.AzerothMapperParent;
import fr.bavencoff.wow.azerothinteldataapi.common.mappers.ParameterMapper;
import fr.bavencoff.wow.azerothinteldataapi.helpers.realms.model.ConnectedRealmBo;
import fr.bavencoff.wow.azerothinteldataapi.helpers.realms.model.RealmBo;
import fr.bavencoff.wow.azerothinteldataapi.helpers.realms.model.UpdateCrModel;
import fr.bavencoff.wow.azerothinteldataapi.web.controllers.connectedrealms.dto.getall.GetAllConnectedRealmResponseDto;
import fr.bavencoff.wow.azerothinteldataapi.web.controllers.connectedrealms.dto.post.PostConnectedRealmRequestDto;
import fr.bavencoff.wow.azerothinteldataapi.web.controllers.connectedrealms.dto.post.PostConnectedRealmRequestRealmInfoDto;
import fr.bavencoff.wow.azerothinteldataapi.web.controllers.connectedrealms.dto.update.PutConnectedRealmRequestDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(config = AzerothMapperParent.class, uses = {
        ParameterMapper.class
})
public interface ConnectedRealmWebMapper {

    List<GetAllConnectedRealmResponseDto.GetAllConnectedRealmResultDto> apisToDtos(
            List<ConnectedRealmBo> realmDaos
    );

    List<GetAllConnectedRealmResponseDto.GetAllConnectedRealmRealmDto> realmsApisToDtos(
            List<RealmBo> realms
    );

    UpdateCrModel dtoToUpdateCrModel(PostConnectedRealmRequestDto request);

    @Mapping(target = "infos", source = ".")
    UpdateCrModel.RealmInfoModel dtoToRealmInfoModel(PostConnectedRealmRequestRealmInfoDto dto);

    UpdateCrModel dtoToUpdateCrModel(PutConnectedRealmRequestDto request);

    @Mapping(target = "infos", source = ".")
    UpdateCrModel.RealmInfoModel dtoToRealmInfoModel(PutConnectedRealmRequestDto dto);

}
