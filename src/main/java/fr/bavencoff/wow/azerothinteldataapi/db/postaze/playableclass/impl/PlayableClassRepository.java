package fr.bavencoff.wow.azerothinteldataapi.db.postaze.playableclass.impl;

import fr.bavencoff.wow.azerothinteldataapi.db.postaze.playableclass.dao.PlayableClassDao;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PlayableClassRepository extends JpaRepository<PlayableClassDao, Integer> {
    @Override
    @EntityGraph(value = "get-playable-race-entity-graph")
    List<PlayableClassDao> findAll();

    @EntityGraph(value = "get-playable-race-entity-graph")
    Optional<PlayableClassDao> findById(Integer id);
}
