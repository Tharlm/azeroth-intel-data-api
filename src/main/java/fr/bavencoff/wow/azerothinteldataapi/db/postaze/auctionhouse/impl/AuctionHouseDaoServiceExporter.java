package fr.bavencoff.wow.azerothinteldataapi.db.postaze.auctionhouse.impl;

import fr.bavencoff.wow.azerothinteldataapi.db.postaze.auctionhouse.dao.AuctionHouseDao;
import fr.bavencoff.wow.azerothinteldataapi.db.postaze.connectedrealms.dao.ConnectedRealmDao;
import fr.bavencoff.wow.azerothinteldataapi.db.postaze.region.dao.RegionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Set;

@Service
public class AuctionHouseDaoServiceExporter {

    private final AuctionHouseDaoRepository auctionHouseDaoRepository;
    private final CommoditiesDaoRepository commoditiesDaoRepository;

    @Autowired
    public AuctionHouseDaoServiceExporter(
            final AuctionHouseDaoRepository auctionHouseDaoRepository,
            final CommoditiesDaoRepository commoditiesDaoRepository
    ) {
        this.auctionHouseDaoRepository = auctionHouseDaoRepository;
        this.commoditiesDaoRepository = commoditiesDaoRepository;
    }

    public List<AuctionHouseDao> findAuctionHouseDaoByNaturalIdInAndDeletedAndConnectedRealm(Set<Long> auctionIds, boolean deleted, ConnectedRealmDao connectedRealmDao, RegionDao regionDao) {
        return auctionHouseDaoRepository.findById_NaturalIdInAndDeletedAndConnectedRealmAndRegion(auctionIds, deleted, connectedRealmDao, regionDao);
    }

    public List<AuctionHouseDao> findAuctionHouseDaoByDeletedAndDateUpdatedBefore(boolean deleted, Instant dateImported, ConnectedRealmDao connectedRealmDao, RegionDao regionDao) {
        return auctionHouseDaoRepository.findByDeletedAndConnectedRealmAndDateUpdatedBeforeAndRegion(deleted, connectedRealmDao, dateImported, regionDao);
    }

    public List<AuctionHouseDao> saveAll(List<AuctionHouseDao> auctionHouseDaos) {
        return auctionHouseDaoRepository.saveAll(auctionHouseDaos);
    }

    public List<Long> findAllMissingIdsItem() {
        return auctionHouseDaoRepository.findAllMissingIdsItem();
    }


}
