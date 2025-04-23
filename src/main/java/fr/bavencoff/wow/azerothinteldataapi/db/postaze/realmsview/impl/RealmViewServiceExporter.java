package fr.bavencoff.wow.azerothinteldataapi.db.postaze.realmsview.impl;

import fr.bavencoff.wow.azerothinteldataapi.common.exceptions.realms.RealmNotFoundException;
import fr.bavencoff.wow.azerothinteldataapi.db.postaze.realmsview.dao.RealmView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RealmViewServiceExporter {
    private final RealmViewRepository repository;

    @Autowired
    public RealmViewServiceExporter(final RealmViewRepository repository) {
        this.repository = repository;
    }

    public RealmView findOneRealm(Integer idRealm) {
        Optional<RealmView> byId = this.repository.findById(idRealm);
        if (byId.isEmpty()) {
            throw new RealmNotFoundException(idRealm);
        }
        return byId.get();
    }

    public List<RealmView> findAll() {
        return this.repository.findAll();
    }
}
