package fr.bavencoff.wow.azerothinteldataapi.db.postaze.mkseason.impl;

import fr.bavencoff.wow.azerothinteldataapi.db.postaze.mkseason.dao.MkSeason;
import fr.bavencoff.wow.azerothinteldataapi.db.postaze.region.dao.RegionDao;
import lombok.NonNull;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MkSeasonRepository extends JpaRepository<MkSeason, Integer> {
    @EntityGraph(value = "get-season-entity-graph")
    @NonNull
    List<MkSeason> findAll();

    @EntityGraph(value = "get-season-entity-graph")
    Optional<MkSeason> findById(Integer id);

    @EntityGraph(value = "get-season-entity-graph")
    Optional<MkSeason> findByIdMkSeasonAndRegion(@NonNull Integer seasonId, @NonNull RegionDao region);
}
