package fr.bavencoff.wow.azerothinteldataapi.helpers.realms.model;

import fr.bavencoff.wow.azerothinteldataapi.helpers.parameters.model.ParameterApi;
import fr.bavencoff.wow.azerothinteldataapi.helpers.regions.model.RegionApi;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RealmApi {
    private Integer id;
    private RegionApi region;
    private String name;
    private String category;
    private String locale;
    private String timezone;
    private String slug;
    private boolean isTournament;
    private ParameterApi type;
    private ConnectedRealmApi connectedRealm;
}
