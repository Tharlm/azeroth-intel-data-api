package fr.bavencoff.wow.azerothinteldataapi.web.controllers.connectedrealms.dto.get;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class GetConnectedRealmResponseDto {
    private Integer id;
    private boolean queue;
    private String population;
    private String status;
    private List<GetConnectedRealmRealmDto> realms;
    private GetConnectedRealmRegionDto region;
}
