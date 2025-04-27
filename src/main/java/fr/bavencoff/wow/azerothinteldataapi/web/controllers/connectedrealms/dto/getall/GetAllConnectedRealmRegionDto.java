package fr.bavencoff.wow.azerothinteldataapi.web.controllers.connectedrealms.dto.getall;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class GetAllConnectedRealmRegionDto {
    private Short id;
    private String tag;
    private String name;
}
