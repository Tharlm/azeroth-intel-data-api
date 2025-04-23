package fr.bavencoff.wow.azerothinteldataapi.db.postaze.mkdungeon.impl;

import fr.bavencoff.wow.azerothinteldataapi.common.exceptions.mythicdungeon.MkDungeonNotFoundException;
import fr.bavencoff.wow.azerothinteldataapi.db.postaze.mkdungeon.dao.MkDungeon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MkDungeonServiceExporter {
    private final MkDungeonRepository repository;

    @Autowired
    public MkDungeonServiceExporter(MkDungeonRepository repository) {
        this.repository = repository;
    }

    public List<MkDungeon> findAll() {
        return this.repository.findAll();
    }

    public Optional<MkDungeon> findOptionalById(
            Integer id
    ) {
        return this.repository.findById(id);
    }

    public MkDungeon save(
            MkDungeon mkDungeon
    ) {
        return this.repository.save(mkDungeon);
    }

    @Cacheable("findMkDungeonDaoById")
    public MkDungeon findById(
            Integer id
    ) {
        Optional<MkDungeon> mkDungeonOptional = this.repository.findById(id);
        if (mkDungeonOptional.isPresent()) {
            return mkDungeonOptional.get();
        }
        throw new MkDungeonNotFoundException(id);
    }
}
