package fr.bavencoff.wow.azerothinteldataapi.db.postaze.mkperiod.impl;

import fr.bavencoff.wow.azerothinteldataapi.db.postaze.mkperiod.dao.MkPeriod;
import fr.bavencoff.wow.azerothinteldataapi.db.postaze.region.dao.RegionDao;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface MkPeriodRepository extends JpaRepository<MkPeriod, Integer> {
    @EntityGraph(value = "get-period-entity-graph")
    Optional<MkPeriod> findByRegionAndIdMkPeriod(RegionDao region, Integer id);

    @EntityGraph(value = "get-period-entity-graph")
    List<MkPeriod> findByRegionAndIdMkPeriodIn(RegionDao region, Collection<Integer> idMkPeriods);
}
