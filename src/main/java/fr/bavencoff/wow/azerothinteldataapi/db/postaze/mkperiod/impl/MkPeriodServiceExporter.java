package fr.bavencoff.wow.azerothinteldataapi.db.postaze.mkperiod.impl;

import fr.bavencoff.wow.azerothinteldataapi.common.exceptions.mkperiod.MkPeriodNotFoundException;
import fr.bavencoff.wow.azerothinteldataapi.db.postaze.mkperiod.dao.MkPeriod;
import fr.bavencoff.wow.azerothinteldataapi.db.postaze.region.dao.RegionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class MkPeriodServiceExporter {
    private final MkPeriodRepository repository;

    @Autowired
    public MkPeriodServiceExporter(
            final MkPeriodRepository repository
    ) {
        this.repository = repository;
    }

    /**
     * Récupère une MK Period par les IDs naturels
     *
     * @param regionDao  region
     * @param idMkPeriod id MK Period
     * @return Optional MK Period
     */
    public Optional<MkPeriod> findOptionalByRegionAndIdMkPeriod(
            RegionDao regionDao,
            Integer idMkPeriod
    ) {
        return this.repository.findByRegionAndIdMkPeriod(regionDao, idMkPeriod);
    }

    /**
     * Liste des Mk Periods par region et par liste d'ID naturel
     *
     * @param regionDao   region
     * @param idMkPeriods liste des IDs naturels
     * @return Liste des MK periods
     */
    public List<MkPeriod> findByRegionAndIdsMkPeriod(RegionDao regionDao, Collection<Integer> idMkPeriods) {
        return repository.findByRegionAndIdMkPeriodIn(regionDao, idMkPeriods);
    }

    /**
     * Récupère une MK Period par les IDs naturels. Renvoie une exception si non trouvé
     *
     * @param regionDao  region
     * @param idMkPeriod id MK Period
     * @return Optional MK Period
     */
    @Cacheable(cacheNames = "findMkPeriodDaoByRegionAndIdMkPeriod")
    public MkPeriod findByRegionAndIdMkPeriod(
            RegionDao regionDao,
            Integer idMkPeriod
    ) {
        Optional<MkPeriod> optionalById = this.findOptionalByRegionAndIdMkPeriod(regionDao, idMkPeriod);
        if (optionalById.isPresent()) {
            return optionalById.get();
        }
        throw new MkPeriodNotFoundException(
                regionDao.getId(),
                idMkPeriod
        );
    }

    public MkPeriod save(
            MkPeriod mkPeriod
    ) {
        return this.repository.save(mkPeriod);
    }
}
