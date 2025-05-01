package fr.bavencoff.wow.azerothinteldataapi.db.postaze.realms.impl;

import fr.bavencoff.wow.azerothinteldataapi.db.postaze.realms.dao.RealmDao;
import fr.bavencoff.wow.azerothinteldataapi.web.controllers.realms.exceptions.RealmNotFoundResponseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RealmDaoServiceExporter {

    private final RealmsRepository repository;

    @Autowired
    public RealmDaoServiceExporter(
            final RealmsRepository repository
    ) {
        this.repository = repository;
    }

    public Optional<RealmDao> findOptionalById(Integer id) {
        return repository.findById(id);
    }

    @Cacheable("findRealmById")
    public RealmDao findById(Integer id) {
        final Optional<RealmDao> realmDao = this.findOptionalById(id);
        if (realmDao.isEmpty()) {
            throw new RealmNotFoundResponseException(id);
        }
        return realmDao.get();
    }

    public List<RealmDao> findAll() {
        return repository.findAll();
    }

    // TODO faire la cache evict
    public RealmDao save(RealmDao request) {
        return repository.save(request);
    }
}
