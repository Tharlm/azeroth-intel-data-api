package fr.bavencoff.wow.azerothinteldataapi.db.postaze.region.impl;

import fr.bavencoff.wow.azerothinteldataapi.common.enums.GlobalRegion;
import fr.bavencoff.wow.azerothinteldataapi.db.postaze.region.dao.RegionDao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RegionDaoRepository extends JpaRepository<RegionDao, Short> {

    List<RegionDao> findByTag(GlobalRegion tag);
}
