package fr.bavencoff.wow.azerothinteldataapi.helpers.items.impl;

import fr.bavencoff.wow.azerothinteldataapi.db.postaze.items.dao.ItemClass;
import fr.bavencoff.wow.azerothinteldataapi.db.postaze.items.dao.ItemDao;
import fr.bavencoff.wow.azerothinteldataapi.db.postaze.items.dao.ItemSubclass;
import fr.bavencoff.wow.azerothinteldataapi.db.postaze.items.dao.ItemSubclassId;
import fr.bavencoff.wow.azerothinteldataapi.db.postaze.items.impl.ItemServiceExporter;
import fr.bavencoff.wow.azerothinteldataapi.db.postaze.parameters.dao.KeyParameterType;
import fr.bavencoff.wow.azerothinteldataapi.db.postaze.parameters.dao.ParameterTypeDao;
import fr.bavencoff.wow.azerothinteldataapi.db.postaze.parameters.impl.ParameterTypeServiceExporter;
import fr.bavencoff.wow.azerothinteldataapi.helpers.items.model.CreateItemApi;
import fr.bavencoff.wow.azerothinteldataapi.helpers.items.model.ItemApi;
import fr.bavencoff.wow.azerothinteldataapi.helpers.items.model.ItemClassApi;
import jakarta.transaction.Transactional;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemApiServiceImpl implements ItemApiService {
    private final ItemServiceExporter itemServiceExporter;
    private final ParameterTypeServiceExporter parameterTypeServiceExporter;
    private final ItemApiMapper itemApiMapper;

    public ItemApiServiceImpl(
            final ItemServiceExporter itemServiceExporter,
            final ParameterTypeServiceExporter parameterTypeServiceExporter,
            final ItemApiMapper itemApiMapper
    ) {
        this.itemServiceExporter = itemServiceExporter;
        this.parameterTypeServiceExporter = parameterTypeServiceExporter;
        this.itemApiMapper = itemApiMapper;
    }

    @Override
    public List<ItemClassApi> findAll() {
        return itemApiMapper.listItemClassApi(itemServiceExporter.getItemClasses());
    }

    @Override
    public ItemClassApi updadteOrCreateItemClass(
            Short id,
            ItemClassApi information
    ) {
        final Optional<ItemClass> optionalById = itemServiceExporter.findOptionalItemClassById(id);
        if (optionalById.isEmpty()) {
            final ItemClass itemClass = new ItemClass();
            itemClass.setId(id);
            return itemApiMapper.fromEntity(this.saveItemClass(itemClass, information));
        } else {
            return itemApiMapper.fromEntity(this.saveItemClass(optionalById.get(), information));
        }
    }

    private ItemClass saveItemClass(
            ItemClass itemClass,
            final ItemClassApi information
    ) {
        itemClass.setId(information.getId());
        itemClass.setName(information.getName());

        // pour chaque subclasse fournies
        information.getItemSubclasses().forEach(itemSubclassApi -> {

            final Optional<ItemSubclass> subclass = itemClass.getItemSubclasses().stream().filter(itemSubclass -> itemSubclass.getId().getId().equals(itemSubclassApi.getId()) && itemSubclass.getId().getIdClass().equals(itemClass.getId())).findFirst();

            // si elle est dans les items present, on met a jour simplement le nom, sinon on l'insère
            if (subclass.isPresent()) {
                final ItemSubclass itemSubclass = subclass.get();
                itemSubclass.setDisplayName(itemSubclassApi.getName());
            } else {
                final ItemSubclass itemSubclass = new ItemSubclass();
                final ItemSubclassId itemSubclassId = new ItemSubclassId();
                itemSubclassId.setId(itemSubclassApi.getId());
                itemSubclassId.setIdClass(itemClass.getId());
                itemSubclass.setId(itemSubclassId);
                itemSubclass.setDisplayName(itemSubclassApi.getName());
                itemClass.addSubclass(itemSubclass);
            }
        });

        return itemServiceExporter.save(itemClass);
    }

    @Override
    public ItemApi updateOrCreateItem(CreateItemApi information) {
        final Optional<ItemDao> itemById = this.itemServiceExporter.findOptionalItemById(information.getId());
        if (itemById.isEmpty()) {
            ItemDao item = new ItemDao();
            item.setId(information.getId());
            return itemApiMapper.fromEntity(saveItem(item, information));
        }
        return itemApiMapper.fromEntity(saveItem(itemById.get(), information));
    }

    @Transactional
    protected ItemDao saveItem(
            ItemDao dao,
            final CreateItemApi api
    ) {
        dao.setName(api.getName());
        dao.setLevel(api.getLevel());
        dao.setRequiredLevel(api.getRequiredLevel());
        dao.setPurchasePrice(api.getPurchasePrice());
        dao.setSellPrice(api.getSellPrice());
        dao.setMaxCount(api.getMaxCount());
        dao.setPurchaseQuantity(api.getPurchaseQuantity());
        dao.setEquippable(api.getEquippable());
        dao.setStackable(api.getStackable());

        final ItemClass itemClassById = this.itemServiceExporter.findItemClassById(api.getIdItemClass());
        dao.setItemClass(itemClassById);
        final ItemSubclassId itemSubclassId = new ItemSubclassId(
                api.getIdItemSubclass(),
                api.getIdItemClass()
        );
        final ItemSubclass itemSubclass = itemClassById.getItemSubclasses().stream().filter(subclass -> subclass.getId().equals(itemSubclassId)).findFirst().orElse(null);
        dao.setItemSubclass(itemSubclass);

        // gestion du type/label quality
        if (api.getQuality() == null) {
            dao.setQuality(null);
        } else if (
                dao.getQuality() == null ||
                        !StringUtils.equals(dao.getQuality().getType(), api.getQuality().getType())
        ) {
            ParameterTypeDao roleParam = this.parameterTypeServiceExporter.getParameterType(
                    KeyParameterType.ITQ,
                    api.getQuality().getType(),
                    api.getQuality().getName()
            );
            dao.setQuality(roleParam);
        } else {
            dao.getQuality().setName(api.getQuality().getName());
        }

        // gestion du type/label inventory
        if (api.getInventory() == null) {
            dao.setInventory(null);
        } else if (
                dao.getInventory() == null ||
                        !StringUtils.equals(dao.getInventory().getType(), api.getInventory().getType())
        ) {
            ParameterTypeDao roleParam = this.parameterTypeServiceExporter.getParameterType(
                    KeyParameterType.ITI,
                    api.getInventory().getType(),
                    api.getInventory().getName()
            );
            dao.setInventory(roleParam);
        } else {
            dao.getInventory().setName(api.getInventory().getName());
        }


        return this.itemServiceExporter.save(dao);
    }
}
