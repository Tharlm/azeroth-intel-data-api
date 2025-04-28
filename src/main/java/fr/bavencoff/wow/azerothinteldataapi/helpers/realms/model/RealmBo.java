package fr.bavencoff.wow.azerothinteldataapi.helpers.realms.model;

import fr.bavencoff.wow.azerothinteldataapi.helpers.parameters.model.ParameterBo;
import fr.bavencoff.wow.azerothinteldataapi.helpers.regions.model.RegionBo;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RealmBo {
    private Integer id;
    private RegionBo region;
    private String name;
    private String category;
    private String locale;
    private String timezone;
    private String slug;
    private boolean isTournament;
    private ParameterBo type;
    private ConnectedRealmBo connectedRealm;
}
