package fr.bavencoff.wow.azerothinteldataapi.web.controllers.connectedrealms.dto.get;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class GetConnectedRealmRealmDto {
    private Integer id;
    private String name;
    private String slug;
}
