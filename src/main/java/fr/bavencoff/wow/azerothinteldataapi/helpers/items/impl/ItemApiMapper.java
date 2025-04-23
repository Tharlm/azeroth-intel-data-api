package fr.bavencoff.wow.azerothinteldataapi.helpers.items.impl;

import fr.bavencoff.wow.azerothinteldataapi.db.postaze.items.dao.ItemClass;
import fr.bavencoff.wow.azerothinteldataapi.db.postaze.items.dao.ItemDao;
import fr.bavencoff.wow.azerothinteldataapi.db.postaze.items.dao.ItemSubclass;
import fr.bavencoff.wow.azerothinteldataapi.helpers.items.model.ItemApi;
import fr.bavencoff.wow.azerothinteldataapi.helpers.items.model.ItemClassApi;
import fr.bavencoff.wow.azerothinteldataapi.helpers.items.model.ItemSubclassApi;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING
)
public interface ItemApiMapper {

    List<ItemClassApi> listItemClassApi(List<ItemClass> itemClasses);

    ItemClassApi fromEntity(ItemClass itemClass);

    @Mapping(target = "name", source = "displayName")
    @Mapping(target = "id", source = "id.id")
    ItemSubclassApi fromEntity(ItemSubclass itemSubclass);

    ItemApi fromEntity(ItemDao itemDao);
}
