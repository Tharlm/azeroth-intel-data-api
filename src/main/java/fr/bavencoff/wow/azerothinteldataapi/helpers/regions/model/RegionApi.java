package fr.bavencoff.wow.azerothinteldataapi.helpers.regions.model;

import fr.bavencoff.wow.azerothinteldataapi.common.enums.GlobalRegion;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public final class RegionApi {
    private Integer id;
    private String name;
    private GlobalRegion tag;
}
