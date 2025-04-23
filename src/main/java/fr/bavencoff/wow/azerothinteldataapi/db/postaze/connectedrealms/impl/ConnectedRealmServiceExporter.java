package fr.bavencoff.wow.azerothinteldataapi.db.postaze.connectedrealms.impl;

import fr.bavencoff.wow.azerothinteldataapi.common.enums.GlobalRegion;
import fr.bavencoff.wow.azerothinteldataapi.common.exceptions.connectedrealms.ConnectedRealmNotFoundException;
import fr.bavencoff.wow.azerothinteldataapi.db.postaze.connectedrealms.dao.ConnectedRealmDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ConnectedRealmServiceExporter {

    private final ConnectedRealmDaoRepository repository;

    @Autowired
    public ConnectedRealmServiceExporter(final ConnectedRealmDaoRepository repository) {
        this.repository = repository;
    }

    @Cacheable("findConnectedRealmDaoById")
    public ConnectedRealmDao findById(Integer id) {
        Optional<ConnectedRealmDao> connectedRealm = this.repository.findById(id);
        if (connectedRealm.isEmpty()) {
            throw new ConnectedRealmNotFoundException(id);
        }
        return connectedRealm.get();
    }

    public Optional<ConnectedRealmDao> findOptionalById(Integer id) {
        return this.repository.findById(id);
    }

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
