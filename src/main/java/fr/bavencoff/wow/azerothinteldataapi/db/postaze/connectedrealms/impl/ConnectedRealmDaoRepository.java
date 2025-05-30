package fr.bavencoff.wow.azerothinteldataapi.db.postaze.connectedrealms.impl;

import fr.bavencoff.wow.azerothinteldataapi.common.enums.GlobalRegion;
import fr.bavencoff.wow.azerothinteldataapi.db.postaze.connectedrealms.dao.ConnectedRealmDao;
import lombok.NonNull;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ConnectedRealmDaoRepository extends JpaRepository<ConnectedRealmDao, Integer> {

    @EntityGraph(value = "cr-entity-graph")
    @NonNull
    List<ConnectedRealmDao> findAll();

    @EntityGraph(value = "cr-entity-graph")
    Optional<ConnectedRealmDao> findById(Integer id);

    @EntityGraph(value = "cr-entity-graph")
    @NonNull
    List<ConnectedRealmDao> findAllByRegion_Tag(@NonNull GlobalRegion globalRegion);

    @EntityGraph(value = "cr-entity-graph")
    List<ConnectedRealmDao> findAllByRegion_TagIn(@NonNull List<GlobalRegion> globalRegions);
}
