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

import java.util.Comparator;
import java.util.List;
import java.util.Set;

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
     * @param regions Set of regions to filter by
     * @return Liste des CRs.
     */
    public GetAllConnectedRealmResponseDto findAll(final Set<GlobalRegion> regions) {
        final List<GetAllConnectedRealmResponseDto.GetAllConnectedRealmResultDto> results;

        if (regions == null || regions.isEmpty()) {
            // If no regions specified, get all connected realms
            results = mapper.apisToDtos(this.realmServiceHelper.getConnectedRealms());
        } else {
            results = mapper.apisToDtos(this.realmServiceHelper.findConnectedRealmsByRegions(regions));
        }

        results.stream()
                .sorted(Comparator.comparing(GetAllConnectedRealmResponseDto.GetAllConnectedRealmResultDto::getId))
                .forEach(result -> result.getRealms().sort(
                        Comparator.comparing(GetAllConnectedRealmResponseDto.GetAllConnectedRealmRealmDto::getName)
                ));

        var dto = new GetAllConnectedRealmResponseDto();
        dto.setResults(results);
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
                requestDto.getRegion(),
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
