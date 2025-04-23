package fr.bavencoff.wow.azerothinteldataapi.helpers.items.impl;


import fr.bavencoff.wow.azerothinteldataapi.helpers.items.model.CreateItemApi;
import fr.bavencoff.wow.azerothinteldataapi.helpers.items.model.ItemApi;
import fr.bavencoff.wow.azerothinteldataapi.helpers.items.model.ItemClassApi;

import java.util.List;

public interface ItemApiService {

    List<ItemClassApi> findAll();

    ItemClassApi updadteOrCreateItemClass(Short id, ItemClassApi itemClassApi);

    ItemApi updateOrCreateItem(CreateItemApi information);
}
