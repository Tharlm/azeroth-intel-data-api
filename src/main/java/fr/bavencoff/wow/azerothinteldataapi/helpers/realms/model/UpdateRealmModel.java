package fr.bavencoff.wow.azerothinteldataapi.helpers.realms.model;

import fr.bavencoff.wow.azerothinteldataapi.common.model.GenericTypeName;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UpdateRealmModel {
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
