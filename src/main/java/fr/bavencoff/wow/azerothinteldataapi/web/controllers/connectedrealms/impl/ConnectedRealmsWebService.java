package fr.bavencoff.wow.azerothinteldataapi.web.controllers.connectedrealms.impl;

import fr.bavencoff.wow.azerothinteldataapi.common.enums.GlobalRegion;
import fr.bavencoff.wow.azerothinteldataapi.helpers.realms.impl.RealmServiceHelper;
import fr.bavencoff.wow.azerothinteldataapi.web.controllers.connectedrealms.dto.get.GetConnectedRealmResponseDto;
import fr.bavencoff.wow.azerothinteldataapi.web.controllers.connectedrealms.dto.getall.GetAllConnectedRealmResponseDto;
import fr.bavencoff.wow.azerothinteldataapi.web.controllers.connectedrealms.dto.post.PostConnectedRealmRequestDto;
import fr.bavencoff.wow.azerothinteldataapi.web.controllers.connectedrealms.dto.post.PostConnectedRealmResponseDto;
import fr.bavencoff.wow.azerothinteldataapi.web.controllers.connectedrealms.dto.update.PutConnectedRealmRequestDto;
import fr.bavencoff.wow.azerothinteldataapi.web.controllers.connectedrealms.dto.update.PutConnectedRealmResponseDto;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConnectedRealmsWebService {

    private final RealmServiceHelper realmServiceHelper;
    private final ConnectedRealmWebMapper mapper;

    @Autowired
    public ConnectedRealmsWebService(
            final RealmServiceHelper realmServiceHelper,
            final ConnectedRealmWebMapper mapper
    ) {
        this.realmServiceHelper = realmServiceHelper;
        this.mapper = mapper;
    }

    /**
     * Renvoie la liste des CRs.
     *
     * @return Lite des CRs.
     */
    public GetAllConnectedRealmResponseDto findAll() {
        var dto = new GetAllConnectedRealmResponseDto();
        dto.setResults(mapper.apisToDtos(this.realmServiceHelper.getConnectedRealms()));
        return dto;
    }

    public GetConnectedRealmResponseDto getConnectedRealm(
            final int id,
            final GlobalRegion region
    ) {
        return this.mapper.boToGetConnectedRealmResponseDto(this.realmServiceHelper.getConnectedRealm(id));
    }

    public PostConnectedRealmResponseDto post(final @Valid PostConnectedRealmRequestDto requestDto) {
        final PostConnectedRealmResponseDto dto = new PostConnectedRealmResponseDto();
        dto.setId(realmServiceHelper.createNewConnectedRealm(
                requestDto.getId(),
                requestDto.getTagRegion(),
                this.mapper.dtoToUpdateCrModel(requestDto)
        ).getId());
        return dto;
    }

    public PutConnectedRealmResponseDto put(
            final int id,
            final GlobalRegion region,
            final @Valid PutConnectedRealmRequestDto requestDto
    ) {
        final PutConnectedRealmResponseDto dto = new PutConnectedRealmResponseDto();
        dto.setId(realmServiceHelper.createOrUpdateConnectedRealm(
                id,
                region,
                this.mapper.dtoToUpdateCrModel(requestDto)
        ).getId());
        return dto;
    }
}
