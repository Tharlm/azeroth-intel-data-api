package fr.bavencoff.wow.azerothinteldataapi.db.postaze.realms.impl;

import fr.bavencoff.wow.azerothinteldataapi.db.postaze.realms.dao.RealmDao;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RealmsRepository extends JpaRepository<RealmDao, Integer> {
    @EntityGraph(value = "realm-entity-graph")
    Optional<RealmDao> findById(Integer id);

    @EntityGraph(value = "realm-entity-graph")
    List<RealmDao> findAll();
}
