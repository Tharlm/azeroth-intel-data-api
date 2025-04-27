package fr.bavencoff.wow.azerothinteldataapi.web.controllers.connectedrealms.dto.update;

import fr.bavencoff.wow.azerothinteldataapi.common.model.GenericTypeName;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class PutConnectedRealmRequestRealmInfoDto {
    @Min(0)
    @Max(Integer.MAX_VALUE)
    @NotNull
    private Integer id;
    @Size(max = 50)
    private String name;
    @Size(max = 30)
    private String category;
    @Size(max = 10)
    private String locale;
    @Size(max = 25)
    private String timezone;
    @Size(max = 50)
    @NotNull
    private String slug;
    private boolean isTournament;
    @NotNull
    private GenericTypeName type;
}
