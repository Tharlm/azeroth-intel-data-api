package fr.bavencoff.wow.azerothinteldataapi.helpers.auctionhouse.impl;

import fr.bavencoff.wow.azerothinteldataapi.helpers.auctionhouse.model.CreateAuctionHouseApi;

import java.util.List;

public interface AuctionHouseApiService {

    void updateOrCreateAuctionHouse(List<CreateAuctionHouseApi> api, Integer idConnectedRealm, Short idRegion);

    List<Long> getMissingItemsFromAuctionHouse();
}
