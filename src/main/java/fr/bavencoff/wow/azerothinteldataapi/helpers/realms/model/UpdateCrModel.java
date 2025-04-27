package fr.bavencoff.wow.azerothinteldataapi.helpers.realms.model;

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
public class UpdateCrModel {
    private boolean queue;
    @NotNull
    @Valid
    private GenericTypeName population;
    @NotNull
    @Valid
    private GenericTypeName status;
    private List<@Valid RealmInfoModel> realms = new ArrayList<>();

    @Getter
    @Setter
    @NoArgsConstructor
    public static class RealmInfoModel {
        @Min(0)
        @Max(Integer.MAX_VALUE)
        @NotNull
        private Integer id;
        @NotNull
        @Valid
        private UpdateRealmModel infos;
    }
}
