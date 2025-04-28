package fr.bavencoff.wow.azerothinteldataapi.helpers.regions.model;

import fr.bavencoff.wow.azerothinteldataapi.common.enums.GlobalRegion;
import fr.bavencoff.wow.azerothinteldataapi.helpers.regions.impl.RegionHelper;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public final class RegionBo {
    @Min(0)
    @Max(Short.MAX_VALUE)
    @NotNull
    private Integer id;
    @NotBlank
    @Size(max = RegionHelper.REGION_NAME_MAX_LENGTH)
    private String name;
    private GlobalRegion tag;
}
