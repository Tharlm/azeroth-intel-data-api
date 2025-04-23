package fr.bavencoff.wow.azerothinteldataapi.db.postaze.items.impl;

import fr.bavencoff.wow.azerothinteldataapi.common.exceptions.item.ItemClassNotFoundException;
import fr.bavencoff.wow.azerothinteldataapi.common.exceptions.item.ItemNotFoundException;
import fr.bavencoff.wow.azerothinteldataapi.db.postaze.items.dao.ItemClass;
import fr.bavencoff.wow.azerothinteldataapi.db.postaze.items.dao.ItemDao;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemServiceExporter {

    private final ItemClassRepository repository;
    private final ItemDaoRepository itemDaoRepository;

    public ItemServiceExporter(
            final ItemClassRepository repository,
            final ItemDaoRepository itemDaoRepository
    ) {
        this.repository = repository;
        this.itemDaoRepository = itemDaoRepository;
    }

    public List<ItemClass> getItemClasses() {
        return repository.findAll();
    }

    public Optional<ItemClass> findOptionalItemClassById(short id) {
        return this.repository.findById(id);
    }

    public ItemClass findItemClassById(short id) {
        final Optional<ItemClass> byId = this.repository.findById(id);
        if (byId.isPresent()) {
            return byId.get();
        } else {
            throw new ItemClassNotFoundException(id);
        }
    }

    public ItemClass save(ItemClass item) {
        return this.repository.save(item);
    }

    public Optional<ItemDao> findOptionalItemById(long idItem) {
        return this.itemDaoRepository.findById(idItem);
    }

    public ItemDao findItemById(long idItem) {
        final Optional<ItemDao> byId = this.findOptionalItemById(idItem);
        if (byId.isPresent()) {
            return byId.get();
        } else {
            throw new ItemNotFoundException(idItem);
        }
    }

    public ItemDao save(ItemDao item) {
        return this.itemDaoRepository.save(item);
    }

}
