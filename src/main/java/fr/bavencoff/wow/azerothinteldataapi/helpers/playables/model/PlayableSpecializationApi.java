package fr.bavencoff.wow.azerothinteldataapi.helpers.playables.model;

import fr.bavencoff.wow.azerothinteldataapi.common.model.GenericTypeName;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PlayableSpecializationApi {
    private Integer id;
    private String name;
    private PlayableClassApi playableClass;
    private GenericTypeName role;
    private GenericTypeName primaryStatType;
}
