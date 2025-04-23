package fr.bavencoff.wow.azerothinteldataapi.helpers.playables.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class PlayableClassApi {
    private Integer id;
    private String name;
    private Set<PlayableRaceApi> races = new HashSet<>();
    private Set<PlayableSpecializationApi> spes = new HashSet<>();
}
