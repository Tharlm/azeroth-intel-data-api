package fr.bavencoff.wow.azerothinteldataapi.db.postaze.mkleaderboard.impl;

import fr.bavencoff.wow.azerothinteldataapi.db.postaze.connectedrealms.dao.ConnectedRealmDao;
import fr.bavencoff.wow.azerothinteldataapi.db.postaze.mkdungeon.dao.MkDungeon;
import fr.bavencoff.wow.azerothinteldataapi.db.postaze.mkleaderboard.dao.MkLeaderboard;
import fr.bavencoff.wow.azerothinteldataapi.db.postaze.mkperiod.dao.MkPeriod;
import fr.bavencoff.wow.azerothinteldataapi.db.postaze.region.dao.RegionDao;
import lombok.NonNull;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MkLeaderboardRepository extends JpaRepository<MkLeaderboard, Integer> {
    @EntityGraph(value = "get-mkleaderboar-dentity-graph")
    @NonNull
    List<MkLeaderboard> findAll();

    @EntityGraph(value = "get-mkleaderboar-dentity-graph")
    @NonNull
    Optional<MkLeaderboard> findById(Integer id);

    @EntityGraph(value = "get-mkleaderboar-dentity-graph")
    Optional<MkLeaderboard> findByMkDungeonAndMkPeriodAndRegionAndConnectedRealm(
            MkDungeon mkDungeon,
            MkPeriod mkPeriod,
            RegionDao region,
            ConnectedRealmDao connectedRealm
    );
}
