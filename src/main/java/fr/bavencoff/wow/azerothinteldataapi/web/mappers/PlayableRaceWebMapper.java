package fr.bavencoff.wow.azerothinteldataapi.web.mappers;

import fr.bavencoff.wow.azerothinteldataapi.helpers.playables.model.PlayableClassApi;
import fr.bavencoff.wow.azerothinteldataapi.helpers.playables.model.PlayableRaceApi;
import fr.bavencoff.wow.azerothinteldataapi.web.controllers.playablesraces.dto.post.PostPlayableRaceRequestDto;
import fr.bavencoff.wow.azerothinteldataapi.web.controllers.playablesraces.dto.post.PostPlayableRaceResponseDto;
import fr.bavencoff.wow.azerothinteldataapi.web.controllers.playablesraces.dto.put.PutPlayableRaceRequestDto;
import fr.bavencoff.wow.azerothinteldataapi.web.controllers.playablesraces.dto.put.PutPlayableRaceResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface PlayableRaceWebMapper {

    PlayableRaceApi mapPostRequestToInformation(
            PostPlayableRaceRequestDto dto
    );

    PlayableClassApi mapPostRequestClassesToInformation(
            PostPlayableRaceRequestDto.PostPlayableRaceClassesRequest dto
    );

    PostPlayableRaceResponseDto mapDaoToPostResponseDto(
            PlayableRaceApi dao
    );

    PlayableRaceApi mapPutRequestToInformation(
            PutPlayableRaceRequestDto dto
    );

    //    @Mapping(source = "name", target = "info.name")
    PlayableRaceApi mapPutRequestClassesToInformation(
            PutPlayableRaceRequestDto.PutPlayableRaceClassRequestDto dto
    );

    PutPlayableRaceResponseDto mapDaoToPutResponseDto(
            PlayableRaceApi dao
    );

}
