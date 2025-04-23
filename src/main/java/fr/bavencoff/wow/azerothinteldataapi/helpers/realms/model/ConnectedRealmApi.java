package fr.bavencoff.wow.azerothinteldataapi.helpers.realms.model;

import fr.bavencoff.wow.azerothinteldataapi.helpers.parameters.model.ParameterApi;
import fr.bavencoff.wow.azerothinteldataapi.helpers.regions.model.RegionApi;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ConnectedRealmApi {
    private Integer id;
    private boolean queue;
    private ParameterApi population;
    private ParameterApi status;
    private RegionApi region;
    private List<RealmApi> realms;
}


