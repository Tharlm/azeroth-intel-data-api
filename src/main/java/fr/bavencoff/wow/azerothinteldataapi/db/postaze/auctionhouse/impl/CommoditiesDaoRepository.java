package fr.bavencoff.wow.azerothinteldataapi.db.postaze.auctionhouse.impl;

import fr.bavencoff.wow.azerothinteldataapi.db.postaze.auctionhouse.dao.CommoditiesDao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommoditiesDaoRepository extends JpaRepository<CommoditiesDao, Long> {
}
