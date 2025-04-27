package fr.bavencoff.wow.azerothinteldataapi.web.controllers.connectedrealms.dto.post;

import fr.bavencoff.wow.azerothinteldataapi.common.model.GenericTypeName;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class PostConnectedRealmRequestDto {

    @Min(0)
    @Max(Integer.MAX_VALUE)
    @NotNull
    private Integer id;
    @Min(0)
    @Max(Short.MAX_VALUE)
    @NotNull
    private Short idRegion;
    private boolean queue;
    @NotNull
    private GenericTypeName population;
    @NotNull
    private GenericTypeName status;
    private List<@Valid PostConnectedRealmRequestRealmInfoDto> realms = new ArrayList<>();
}
