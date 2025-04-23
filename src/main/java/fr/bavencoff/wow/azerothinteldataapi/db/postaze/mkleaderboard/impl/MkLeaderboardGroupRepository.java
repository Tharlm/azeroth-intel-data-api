package fr.bavencoff.wow.azerothinteldataapi.db.postaze.mkleaderboard.impl;

import fr.bavencoff.wow.azerothinteldataapi.db.postaze.mkdungeon.dao.MkDungeon;
import fr.bavencoff.wow.azerothinteldataapi.db.postaze.mkleaderboard.dao.MkLeaderboardGroup;
import fr.bavencoff.wow.azerothinteldataapi.db.postaze.mkperiod.dao.MkPeriod;
import fr.bavencoff.wow.azerothinteldataapi.db.postaze.region.dao.RegionDao;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.Instant;
import java.util.List;

public interface MkLeaderboardGroupRepository extends JpaRepository<MkLeaderboardGroup, Integer> {

    List<MkLeaderboardGroup> findByDurationInAndMkLeaderboard_RegionAndMkLeaderboard_MkPeriodAndMkLeaderboard_MkDungeon(
            List<Integer> duration,
            RegionDao region,
            MkPeriod mkPeriod,
            MkDungeon mkDungeon
    );


}
