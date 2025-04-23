package fr.bavencoff.wow.azerothinteldataapi.db.postaze.auctionhouse.impl;

import fr.bavencoff.wow.azerothinteldataapi.db.postaze.auctionhouse.dao.AuctionHouseDao;
import fr.bavencoff.wow.azerothinteldataapi.db.postaze.auctionhouse.dao.AuctionHouseIdDao;
import fr.bavencoff.wow.azerothinteldataapi.db.postaze.connectedrealms.dao.ConnectedRealmDao;
import fr.bavencoff.wow.azerothinteldataapi.db.postaze.region.dao.RegionDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.Instant;
import java.util.Collection;
import java.util.List;

public interface AuctionHouseDaoRepository extends JpaRepository<AuctionHouseDao, AuctionHouseIdDao> {

    List<AuctionHouseDao> findById_NaturalIdInAndDeletedAndConnectedRealmAndRegion(Collection<Long> auctionIds, boolean deleted, ConnectedRealmDao connectedRealmDao, RegionDao regionDao);

    List<AuctionHouseDao> findByDeletedAndConnectedRealmAndDateUpdatedBeforeAndRegion(boolean deleted, ConnectedRealmDao connectedRealmDao, Instant dateTime, RegionDao regionDao);

    // on pourrait l'optimiser car si elle tourne pluieurs fois, les id deja recherches reviendraient a chaque fois
    @Query(value = "SELECT distinct idItem from AuctionHouseDao where idItem not in (select distinct id from ItemDao )")
    List<Long> findAllMissingIdsItem();

}
