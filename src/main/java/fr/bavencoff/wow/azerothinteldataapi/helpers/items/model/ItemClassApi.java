package fr.bavencoff.wow.azerothinteldataapi.helpers.items.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class ItemClassApi {
    private Short id;
    private String name;
    private Set<ItemSubclassApi> itemSubclasses = new HashSet<>();
}
