package fr.bavencoff.wow.azerothinteldataapi.web.controllers.realms.dto.get;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class GetRealmConnectedRealmDto {
    private Integer id;
    private boolean queue;
    private String population;
    private String status;
}
