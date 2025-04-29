package fr.bavencoff.wow.azerothinteldataapi.helpers.realms.model;

import fr.bavencoff.wow.azerothinteldataapi.helpers.parameters.model.ParameterBo;
import fr.bavencoff.wow.azerothinteldataapi.helpers.regions.model.RegionBo;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ConnectedRealmBo {
    private Integer id;
    private boolean queue;
    @NotNull
    private ParameterBo population;
    @NotNull
    private ParameterBo status;
    @NotNull
    private RegionBo region;
    private List<RealmBo> realms = new ArrayList<>();
}


