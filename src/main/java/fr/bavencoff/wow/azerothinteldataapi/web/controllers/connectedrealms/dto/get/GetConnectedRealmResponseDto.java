package fr.bavencoff.wow.azerothinteldataapi.web.controllers.connectedrealms.dto.get;

import fr.bavencoff.wow.azerothinteldataapi.common.enums.GlobalRegion;
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
    private GlobalRegion region;
}
