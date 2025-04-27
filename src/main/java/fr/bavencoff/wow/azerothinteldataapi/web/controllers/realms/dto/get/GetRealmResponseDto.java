package fr.bavencoff.wow.azerothinteldataapi.web.controllers.realms.dto.get;

import fr.bavencoff.wow.azerothinteldataapi.common.enums.GlobalRegion;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class GetRealmResponseDto {

    private Integer id;
    private GlobalRegion region;
    private String name;
    private String category;
    private String locale;
    private String timezone;
    private String slug;
    private boolean tournament;
    private String type;
    private GetRealmConnectedRealmDto connectedRealm;
}
