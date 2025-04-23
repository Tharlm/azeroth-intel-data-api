package fr.bavencoff.wow.azerothinteldataapi.db.postaze.playablespe.impl;

import fr.bavencoff.wow.azerothinteldataapi.db.postaze.playablespe.dao.PlayableSpecializationDao;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PlayableSpecializationDaoServiceExporter {
    private final PlayableSpecializationDaoRepository repository;

    public PlayableSpecializationDaoServiceExporter(
            final PlayableSpecializationDaoRepository repository
    ) {
        this.repository = repository;
    }

    public Optional<PlayableSpecializationDao> findOptionalById(final int id) {
        return this.repository.findById(id);
    }

    public PlayableSpecializationDao findById(final int id) {
        // TODO custom exception
        return this.findOptionalById(id).orElseThrow();
    }

    public PlayableSpecializationDao save(final PlayableSpecializationDao specialization) {
        return this.repository.save(specialization);
    }
}
