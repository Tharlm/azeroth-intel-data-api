package fr.bavencoff.wow.azerothinteldataapi.web.controllers.connectedrealms.dto.update;

import fr.bavencoff.wow.azerothinteldataapi.common.model.GenericTypeName;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PutConnectedRealmRequestDto {

    private boolean queue;
    @NotNull
    private GenericTypeName population;
    @NotNull
    private GenericTypeName status;
    private List<@Valid PutConnectedRealmRequestRealmInfoDto> realms = new ArrayList<>();
}
