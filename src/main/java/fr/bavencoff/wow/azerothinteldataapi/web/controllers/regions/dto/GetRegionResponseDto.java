package fr.bavencoff.wow.azerothinteldataapi.web.controllers.regions.dto;

import fr.bavencoff.wow.azerothinteldataapi.common.enums.GlobalRegion;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class GetRegionResponseDto {
    private Short id;
    private String name;
    private GlobalRegion tag;
}
