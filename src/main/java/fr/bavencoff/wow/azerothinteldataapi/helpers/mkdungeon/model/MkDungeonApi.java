package fr.bavencoff.wow.azerothinteldataapi.helpers.mkdungeon.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MkDungeonApi {
    private Integer id;
    private String name;
    private boolean tracked;
}
