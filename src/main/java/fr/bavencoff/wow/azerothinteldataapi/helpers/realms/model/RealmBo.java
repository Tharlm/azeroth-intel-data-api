package fr.bavencoff.wow.azerothinteldataapi.helpers.realms.model;

import fr.bavencoff.wow.azerothinteldataapi.db.postaze.realms.impl.RealmHelper;
import fr.bavencoff.wow.azerothinteldataapi.helpers.parameters.model.ParameterBo;
import fr.bavencoff.wow.azerothinteldataapi.helpers.regions.model.RegionBo;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RealmBo {
    private Integer id;
    @NotNull
    private RegionBo region;
    @Size(max = RealmHelper.REALM_NAME_MAX_LENGTH)
    private String name;
    @Size(max = RealmHelper.REALM_CATEGORY_MAX_LENGTH)
    private String category;
    @Size(max = RealmHelper.REALM_LOCALE_MAX_LENGTH)
    private String locale;
    @Size(max = RealmHelper.REALM_TIMEZONE_MAX_LENGTH)
    private String timezone;
    @Size(max = RealmHelper.REALM_SLUG_MAX_LENGTH)
    private String slug;
    private boolean isTournament;
    private ParameterBo type;
    private ConnectedRealmBo connectedRealm;
}
