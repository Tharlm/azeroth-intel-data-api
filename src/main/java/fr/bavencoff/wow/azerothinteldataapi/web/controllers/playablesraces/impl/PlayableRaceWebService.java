package fr.bavencoff.wow.azerothinteldataapi.web.controllers.playablesraces.impl;

import fr.bavencoff.wow.azerothinteldataapi.helpers.playables.impl.PlayableRaceApiService;
import fr.bavencoff.wow.azerothinteldataapi.helpers.playables.model.PlayableRaceApi;
import fr.bavencoff.wow.azerothinteldataapi.web.controllers.playablesraces.dto.post.PostPlayableRaceRequestDto;
import fr.bavencoff.wow.azerothinteldataapi.web.controllers.playablesraces.dto.post.PostPlayableRaceResponseDto;
import fr.bavencoff.wow.azerothinteldataapi.web.controllers.playablesraces.dto.put.PutPlayableRaceRequestDto;
import fr.bavencoff.wow.azerothinteldataapi.web.controllers.playablesraces.dto.put.PutPlayableRaceResponseDto;
import fr.bavencoff.wow.azerothinteldataapi.web.mappers.PlayableRaceWebMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlayableRaceWebService {

    private final PlayableRaceApiService apiService;
    private final PlayableRaceWebMapper mapper;

    @Autowired
    public PlayableRaceWebService(
            final PlayableRaceApiService apiService,
            final PlayableRaceWebMapper mapper
    ) {
        this.apiService = apiService;
        this.mapper = mapper;
    }

    public PostPlayableRaceResponseDto postPlayableRace(
            PostPlayableRaceRequestDto request
    ) {
        PlayableRaceApi information = this.mapper.mapPostRequestToInformation(request);
        PlayableRaceApi newPlayableRace = this.apiService.createNewPlayableRace(
                request.getId(),
                information
        );
        return this.mapper.mapDaoToPostResponseDto(newPlayableRace);
    }

    public PutPlayableRaceResponseDto putPlayableRace(
            Integer id,
            PutPlayableRaceRequestDto request
    ) {
        PlayableRaceApi information = this.mapper.mapPutRequestToInformation(request);
        PlayableRaceApi playableRaceDao = this.apiService.createOrUpdatePlayableRace(id, information);
        return this.mapper.mapDaoToPutResponseDto(playableRaceDao);
    }
}
