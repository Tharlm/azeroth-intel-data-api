package fr.bavencoff.wow.azerothinteldataapi.db.postaze.region.impl;

import fr.bavencoff.wow.azerothinteldataapi.db.postaze.region.dao.RegionDao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegionDaoRepository extends JpaRepository<RegionDao, Short> {

}
