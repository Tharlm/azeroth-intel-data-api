package fr.bavencoff.wow.azerothinteldataapi.db.postaze.mkseason.impl;

import fr.bavencoff.wow.azerothinteldataapi.common.exceptions.mythicseason.MkSeasonNotFoundException;
import fr.bavencoff.wow.azerothinteldataapi.db.postaze.mkseason.dao.MkSeason;
import fr.bavencoff.wow.azerothinteldataapi.db.postaze.region.dao.RegionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MkSeasonServiceExporter {
    private final MkSeasonRepository repository;

    @Autowired
    public MkSeasonServiceExporter(MkSeasonRepository repository) {
        this.repository = repository;
    }

    public List<MkSeason> findAll() {
        return this.repository.findAll();
    }

    /**
     * Recherche une MK season par ID tech
     *
     * @param idTech ID tech
     * @return Optional MK season
     */
    public Optional<MkSeason> findOptionalById(
            Integer idTech
    ) {
        return this.repository.findById(idTech);
    }

    /**
     * Recherche une MK season par ID tech. Retourne une exception si non trouvé.
     * @param idTech ID tech
     * @return Optional MK season
     */
    public MkSeason findById(
            Integer idTech
    ) {
        Optional<MkSeason> optionalById = this.findOptionalById(idTech);
        if (optionalById.isEmpty()) {
            throw new MkSeasonNotFoundException(idTech);
        }
        return optionalById.get();
    }

    public MkSeason save(
            MkSeason mkSeason
    ) {
        return this.repository.save(mkSeason);
    }

    /**
     * Retourne une MkSeason par les Ids naturels, càd l'id MK season et la region
     * @param idMkSeason ID MK season
     * @param regionDao region
     * @return MK season
     */
    public Optional<MkSeason> findOptionalByIdMkSeasonAndRegion(
            Integer idMkSeason,
            RegionDao regionDao
    ) {
        return this.repository.findByIdMkSeasonAndRegion(idMkSeason, regionDao);
    }

    /**
     * Retourne une MkSeason par les Ids naturels, càd l'id MK season et la region. Retourne exception si non trouvé.
     * @param idMkSeason ID MK season
     * @param regionDao region
     * @return MK season
     */
    public MkSeason findByIdMkSeasonAndRegion(
            Integer idMkSeason,
            RegionDao regionDao
    ) {
        Optional<MkSeason> optionalByIdMkSeasonAndRegion = this.findOptionalByIdMkSeasonAndRegion(idMkSeason, regionDao);
        if (optionalByIdMkSeasonAndRegion.isEmpty()) {
            throw new MkSeasonNotFoundException(idMkSeason, regionDao.getId());
        }
        return optionalByIdMkSeasonAndRegion.get();
    }
}
