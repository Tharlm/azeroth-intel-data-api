package fr.bavencoff.wow.azerothinteldataapi.web.controllers.connectedrealms.dto.get;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class GetConnectedRealmRegionDto {
    private Integer id;
    private String tag;
    private String name;
}
