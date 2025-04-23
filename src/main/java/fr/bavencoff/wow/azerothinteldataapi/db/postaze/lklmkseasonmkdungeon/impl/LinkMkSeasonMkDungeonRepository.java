package fr.bavencoff.wow.azerothinteldataapi.db.postaze.lklmkseasonmkdungeon.impl;

import fr.bavencoff.wow.azerothinteldataapi.db.postaze.lklmkseasonmkdungeon.dao.LinkMkSeasonMkDungeon;
import fr.bavencoff.wow.azerothinteldataapi.db.postaze.lklmkseasonmkdungeon.dao.LinkMkSeasonMkDungeonId;
import fr.bavencoff.wow.azerothinteldataapi.db.postaze.mkseason.dao.MkSeason;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LinkMkSeasonMkDungeonRepository extends JpaRepository<LinkMkSeasonMkDungeon, LinkMkSeasonMkDungeonId> {

    @EntityGraph("get-link-mk-season-mk-dungeon-entity-graph")
    List<LinkMkSeasonMkDungeon> findAllByMkSeason(MkSeason mkSeason);

    @EntityGraph("get-link-mk-season-mk-dungeon-entity-graph")
    List<LinkMkSeasonMkDungeon> findAllByIdRegionAndIdMkSeason(Short idRegion, Integer idMkSeason);
}
