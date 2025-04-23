package fr.bavencoff.wow.azerothinteldataapi.helpers.auctionhouse.impl;

import fr.bavencoff.wow.azerothinteldataapi.db.postaze.auctionhouse.dao.AuctionHouseDao;
import fr.bavencoff.wow.azerothinteldataapi.db.postaze.auctionhouse.dao.AuctionHouseIdDao;
import fr.bavencoff.wow.azerothinteldataapi.db.postaze.auctionhouse.impl.AuctionHouseDaoServiceExporter;
import fr.bavencoff.wow.azerothinteldataapi.db.postaze.connectedrealms.dao.ConnectedRealmDao;
import fr.bavencoff.wow.azerothinteldataapi.db.postaze.connectedrealms.impl.ConnectedRealmServiceExporter;
import fr.bavencoff.wow.azerothinteldataapi.db.postaze.region.dao.RegionDao;
import fr.bavencoff.wow.azerothinteldataapi.db.postaze.region.impl.RegionDaoServiceExporter;
import fr.bavencoff.wow.azerothinteldataapi.helpers.auctionhouse.model.CreateAuctionHouseApi;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AuctionHouseApiServiceImpl implements AuctionHouseApiService {

    private final AuctionHouseDaoServiceExporter auctionHouseDaoServiceExporter;
    private final ConnectedRealmServiceExporter connectedRealmServiceExporter;
    private final RegionDaoServiceExporter regionDaoServiceExporter;

    public AuctionHouseApiServiceImpl(
            final AuctionHouseDaoServiceExporter auctionHouseDaoServiceExporter,
            final ConnectedRealmServiceExporter connectedRealmServiceExporter,
            RegionDaoServiceExporter regionDaoServiceExporter) {
        this.auctionHouseDaoServiceExporter = auctionHouseDaoServiceExporter;
        this.connectedRealmServiceExporter = connectedRealmServiceExporter;
        this.regionDaoServiceExporter = regionDaoServiceExporter;
    }

    /**
     * To update auction we have to process with batch due to the amount of data.
     * The batch size will be noted N
     * <p>
     * First we retrieve N rows from the DB with IDs equals to the new ones.
     * 2 cases are posssible:
     * <ul>
     *     <li>the row is present is both DB and API: we update the row in our DB</li>
     *     <li>the row does not exist in our DB: we add this one in our DB</li>
     * </ul>
     * We repeat the process anytime necessary for processsing of data from api
     * <p>
     * Then we delete logically data in our DB that are still active but no more existing in the API
     *
     * @param apis
     */
    @Override
    public void updateOrCreateAuctionHouse(List<CreateAuctionHouseApi> apis, Integer idConnectedRealm, Short idRegion) {

        // retrieve cr and region linked
        final ConnectedRealmDao connectedRealmDao = connectedRealmServiceExporter.findById(idConnectedRealm);
        final RegionDao regionDao = regionDaoServiceExporter.findById(idRegion);

        // we process by batch size, for every element
        int batchSize = 500;
        for (int i = 0; i < apis.size(); i += batchSize) {
            int toIndex = Math.min(i + batchSize, apis.size());
            List<CreateAuctionHouseApi> apisBatch = apis.subList(i, toIndex);
            // we retrieve those existing in our DB
            final List<AuctionHouseDao> daos = auctionHouseDaoServiceExporter.findAuctionHouseDaoByNaturalIdInAndDeletedAndConnectedRealm(
                    apisBatch.stream().unordered().map(CreateAuctionHouseApi::getId).collect(Collectors.toSet()),
                    false,
                    connectedRealmDao,
                    regionDao
            );

            // for every element in our batch size, we check if it exists in our DB
            for (CreateAuctionHouseApi api : apisBatch) {
                final Optional<AuctionHouseDao> firstMatch = daos.stream().unordered().filter(dao -> dao.getId().getNaturalId().equals(api.getId())).findFirst();
                // if it exists, we update it, otherwise, we add it.
                if (firstMatch.isPresent()) {
                    updateAuctionHouse(firstMatch.get(), api);
                } else {
                    daos.add(createAuctionHouse(api, connectedRealmDao, regionDao));
                }
            }
            auctionHouseDaoServiceExporter.saveAll(daos);
        }

        // to remove auction that are not still existing we have three choices:
        // those who are sold or those with their timer finished or those we cannot know (data not updated)
        // we considered item with unknown status when the date update
        // TODO a terminer
        final List<AuctionHouseDao> daosToDelete = auctionHouseDaoServiceExporter.findAuctionHouseDaoByDeletedAndDateUpdatedBefore(false, Instant.now(), connectedRealmDao, regionDao);
        for (AuctionHouseDao auctionHouseDao : daosToDelete) {
//            if (auctionHouseDao.getTimeleft() == AuctionHouseTimeLeft.SHORT) {
//                auctionHouseDao.setSold(true);
//            }
            auctionHouseDao.setDeleted(true);
        }
        auctionHouseDaoServiceExporter.saveAll(daosToDelete);

    }

    @Override
    public List<Long> getMissingItemsFromAuctionHouse() {
        return auctionHouseDaoServiceExporter.findAllMissingIdsItem();
    }

    private void updateAuctionHouse(
            AuctionHouseDao dao,
            CreateAuctionHouseApi api
    ) {
        dao.setDateUpdated(Instant.now());
        dao.setTimeleft(api.getTimeleft());
    }


    private AuctionHouseDao createAuctionHouse(
            CreateAuctionHouseApi api,
            ConnectedRealmDao connectedRealmDao,
            RegionDao regionDao
    ) {
        final AuctionHouseDao dao = new AuctionHouseDao();
        final AuctionHouseIdDao auctionHouseIdDao = new AuctionHouseIdDao();
        auctionHouseIdDao.setNaturalId(api.getId());
        auctionHouseIdDao.setIdRegion(regionDao.getId());
        dao.setId(auctionHouseIdDao);
        dao.setIdItem(api.getIdItem());
        dao.setIdPetBreed(api.getIdPetBreed());
        dao.setNbPetLevel(api.getNbPetLevel());
        dao.setIdPetQuality(api.getIdPetQuality());
        dao.setIdPetSpecies(api.getIdPetSpecies());
        dao.setBid(api.getBid());
        dao.setBuyout(api.getBuyout());
        dao.setQuantity(api.getQuantity());
        dao.setDeleted(false);
        dao.setDateImported(Instant.now());
        dao.setDateUpdated(Instant.now());
        dao.setConnectedRealm(connectedRealmDao);
        dao.setRegion(regionDao);
        dao.setTimeleft(api.getTimeleft());
        return dao;
    }
}
