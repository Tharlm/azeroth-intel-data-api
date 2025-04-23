package fr.bavencoff.wow.azerothinteldataapi.helpers.playables.model;

import fr.bavencoff.wow.azerothinteldataapi.helpers.parameters.model.ParameterApi;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class PlayableRaceApi {
    private Integer id;
    private String name;
    private boolean selectable;
    private boolean alliedRace;
    private ParameterApi faction;
    private Set<PlayableClassApi> classes = new HashSet<>();
}
