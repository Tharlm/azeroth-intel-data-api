package fr.bavencoff.wow.azerothinteldataapi.db.postaze.playableclass.impl;

import fr.bavencoff.wow.azerothinteldataapi.db.postaze.playableclass.dao.PlayableClassDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class PlayableClassServiceExporter {

    private final PlayableClassRepository repository;

    @Autowired
    public PlayableClassServiceExporter(
            final PlayableClassRepository repository
    ) {
        this.repository = repository;
    }

    public Optional<PlayableClassDao> findOptionalById(final Integer id) {
        return repository.findById(id);
    }

    public List<PlayableClassDao> findAll() {
        return this.repository.findAll();
    }

    public Map<Integer, PlayableClassDao> mapAll() {
        return this.repository.findAll().stream()
                .collect(Collectors.toMap(PlayableClassDao::getId, Function.identity()));
    }

    public PlayableClassDao save(PlayableClassDao dao) {
        return this.repository.save(dao);
    }
}
