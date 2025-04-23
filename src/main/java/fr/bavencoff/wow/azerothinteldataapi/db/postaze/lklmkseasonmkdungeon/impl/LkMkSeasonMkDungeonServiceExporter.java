package fr.bavencoff.wow.azerothinteldataapi.db.postaze.lklmkseasonmkdungeon.impl;

import fr.bavencoff.wow.azerothinteldataapi.db.postaze.lklmkseasonmkdungeon.dao.LinkMkSeasonMkDungeon;
import fr.bavencoff.wow.azerothinteldataapi.db.postaze.mkseason.dao.MkSeason;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LkMkSeasonMkDungeonServiceExporter {

    private final LinkMkSeasonMkDungeonRepository repository;

    @Autowired
    public LkMkSeasonMkDungeonServiceExporter(
            final LinkMkSeasonMkDungeonRepository repository
    ) {
        this.repository = repository;
    }

    public List<LinkMkSeasonMkDungeon> findAllBySeason(final MkSeason mkSeason) {
        return this.repository.findAllByMkSeason(mkSeason);
    }

    public List<LinkMkSeasonMkDungeon> findAllByIdRegionAndIdMkSeason(final Short idRegion, final Integer idMkSeason) {
        return this.repository.findAllByIdRegionAndIdMkSeason(idRegion, idMkSeason);
    }

    public LinkMkSeasonMkDungeon save(final LinkMkSeasonMkDungeon mkSeason) {
        return this.repository.save(mkSeason);
    }
}
