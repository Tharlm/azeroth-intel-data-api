package fr.bavencoff.wow.azerothinteldataapi.helpers.realms.model;

import fr.bavencoff.wow.azerothinteldataapi.helpers.parameters.model.ParameterBo;
import fr.bavencoff.wow.azerothinteldataapi.helpers.regions.model.RegionBo;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ConnectedRealmBo {
    private Integer id;
    private boolean queue;
    private ParameterBo population;
    private ParameterBo status;
    private RegionBo region;
    private List<RealmBo> realms;
}


