package fr.bavencoff.wow.azerothinteldataapi.db.postaze.playablerace.impl;

import fr.bavencoff.wow.azerothinteldataapi.db.postaze.playablerace.dao.PlayableRaceDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlayableRaceServiceExporter {

    private final PlayableRaceRepository playableRaceRepository;

    @Autowired
    public PlayableRaceServiceExporter(
            final PlayableRaceRepository playableRaceRepository
    ) {
        this.playableRaceRepository = playableRaceRepository;
    }

    public List<PlayableRaceDao> findAll() {
        return this.playableRaceRepository.findAll();
    }

    public Optional<PlayableRaceDao> findOptionalById(
            Integer id
    ) {
        return this.playableRaceRepository.findById(id);
    }

    public PlayableRaceDao save(PlayableRaceDao dao) {
        return this.playableRaceRepository.save(dao);
    }
}
