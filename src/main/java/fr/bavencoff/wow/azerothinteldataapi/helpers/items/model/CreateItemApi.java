package fr.bavencoff.wow.azerothinteldataapi.helpers.items.model;

import fr.bavencoff.wow.azerothinteldataapi.common.model.GenericTypeName;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CreateItemApi {
    private Long id;
    private String name;
    private Short idItemClass;
    private Short idItemSubclass;
    private GenericTypeName quality;
    private GenericTypeName inventory;
    private Short level;
    private Short requiredLevel;
    private Integer purchasePrice;
    private Integer sellPrice;
    private Short maxCount;
    private Short purchaseQuantity;
    private Boolean equippable;
    private Boolean stackable;
}
