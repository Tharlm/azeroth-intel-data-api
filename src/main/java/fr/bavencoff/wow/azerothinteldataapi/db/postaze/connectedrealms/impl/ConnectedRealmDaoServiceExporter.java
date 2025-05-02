package fr.bavencoff.wow.azerothinteldataapi.db.postaze.connectedrealms.impl;

import fr.bavencoff.wow.azerothinteldataapi.common.enums.GlobalRegion;
import fr.bavencoff.wow.azerothinteldataapi.db.postaze.connectedrealms.dao.ConnectedRealmDao;
import fr.bavencoff.wow.azerothinteldataapi.web.controllers.connectedrealms.exceptions.ConnectedRealmNotFoundResponseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ConnectedRealmDaoServiceExporter {

    private final ConnectedRealmDaoRepository repository;

    @Autowired
    public ConnectedRealmDaoServiceExporter(final ConnectedRealmDaoRepository repository) {
        this.repository = repository;
    }

    @Cacheable(
            value = "findConnectedRealmDaoById",
            unless = "#result == null"
    )
    public ConnectedRealmDao findById(Integer id) {
        Optional<ConnectedRealmDao> connectedRealm = this.repository.findById(id);
        if (connectedRealm.isEmpty()) {
            throw new ConnectedRealmNotFoundResponseException(id);
        }
        return connectedRealm.get();
    }

    public Optional<ConnectedRealmDao> findOptionalById(Integer id) {
        return this.repository.findById(id);
    }

    @CacheEvict(
            cacheNames = "findConnectedRealmDaoById",
            key = "#dao.id"
    )
    public ConnectedRealmDao saveConnectedRealmDao(ConnectedRealmDao dao) {
        return this.repository.save(dao);
    }

    public List<ConnectedRealmDao> findAll() {
        return this.repository.findAll();
    }

    public List<ConnectedRealmDao> findAllByRegion_Tag(GlobalRegion globalRegion) {
        return this.repository.findAllByRegion_Tag(globalRegion);
    }
}
