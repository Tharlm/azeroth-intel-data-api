package fr.bavencoff.wow.azerothinteldataapi.helpers.mkdungeon.impl;

import fr.bavencoff.wow.azerothinteldataapi.common.exceptions.mythicdungeon.MkDungeonAlreadyExistsException;
import fr.bavencoff.wow.azerothinteldataapi.db.postaze.lklmkseasonmkdungeon.dao.LinkMkSeasonMkDungeon;
import fr.bavencoff.wow.azerothinteldataapi.db.postaze.lklmkseasonmkdungeon.dao.LinkMkSeasonMkDungeonId;
import fr.bavencoff.wow.azerothinteldataapi.db.postaze.lklmkseasonmkdungeon.impl.LkMkSeasonMkDungeonServiceExporter;
import fr.bavencoff.wow.azerothinteldataapi.db.postaze.mkdungeon.dao.MkDungeon;
import fr.bavencoff.wow.azerothinteldataapi.db.postaze.mkdungeon.impl.MkDungeonServiceExporter;
import fr.bavencoff.wow.azerothinteldataapi.db.postaze.mkseason.impl.MkSeasonServiceExporter;
import fr.bavencoff.wow.azerothinteldataapi.helpers.mkdungeon.model.MkDungeonApi;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class MkDungeonApiServiceImpl implements MkDungeonApiService {
    private final MkDungeonServiceExporter mkDungeonServiceExporter;
    private final LkMkSeasonMkDungeonServiceExporter lkMkSeasonMkDungeonServiceExporter;
    private final MkSeasonServiceExporter mkSeasonServiceExporter;
    private final MkDungeonApiMapper mkDungeonMapper;

    @Autowired
    public MkDungeonApiServiceImpl(
            final MkDungeonServiceExporter mkDungeonServiceExporter,
            final LkMkSeasonMkDungeonServiceExporter lkMkSeasonMkDungeonServiceExporter,
            final MkSeasonServiceExporter mkSeasonServiceExporter,
            final MkDungeonApiMapper mkDungeonMapper
    ) {
        this.mkDungeonServiceExporter = mkDungeonServiceExporter;
        this.lkMkSeasonMkDungeonServiceExporter = lkMkSeasonMkDungeonServiceExporter;
        this.mkSeasonServiceExporter = mkSeasonServiceExporter;
        this.mkDungeonMapper = mkDungeonMapper;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public MkDungeonApi createNewMkDungeon(
            Integer id,
            Integer idDungeon,
            MkDungeonApi information
    ) {
        Optional<MkDungeon> mkDungeonOptional = this.mkDungeonServiceExporter.findOptionalById(id);

        if (mkDungeonOptional.isPresent()) {
            throw new MkDungeonAlreadyExistsException(id);
        }

        MkDungeon mkDungeon = new MkDungeon();
        mkDungeon.setId(id);
        mkDungeon.setIdDungeon(idDungeon);
        return mkDungeonMapper.daoToApi(this.saveMkDungeon(mkDungeon, information));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public MkDungeonApi createOrUpdateMkDungeon(
            Integer id,
            Integer idDungeon,
            MkDungeonApi information
    ) {
        Optional<MkDungeon> mkDungeonOptional = this.mkDungeonServiceExporter.findOptionalById(id);
        if (mkDungeonOptional.isEmpty()) {
            MkDungeon mkDungeon = new MkDungeon();
            mkDungeon.setId(id);
            mkDungeon.setIdDungeon(idDungeon);
            return mkDungeonMapper.daoToApi(this.saveMkDungeon(mkDungeon, information));
        }
        return mkDungeonMapper.daoToApi(this.saveMkDungeon(mkDungeonOptional.get(), information));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<MkDungeonApi> findAll() {
        // todo
        return List.of();
    }

    /**
     * Enregistre les infos d'un MK Dungeon
     *
     * @param mkDungeon   MK dungeon
     * @param information infos
     * @return MK dungeon màj
     */
    private MkDungeon saveMkDungeon(
            @NotNull MkDungeon mkDungeon,
            MkDungeonApi information
    ) {
        mkDungeon.setName(information.getName());
        mkDungeon.setTracked(information.isTracked());
        return this.mkDungeonServiceExporter.save(mkDungeon);
    }


    @Override
    public List<MkDungeonApi> findMkDungeonOfMkSeason(
            Integer idMkseason,
            Short idRegion
    ) {
        return this.lkMkSeasonMkDungeonServiceExporter.findAllByIdRegionAndIdMkSeason(idRegion, idMkseason)
                .stream()
                .map(LinkMkSeasonMkDungeon::getMkDungeon)
                .map(mkDungeonMapper::daoToApi)
                .toList();
    }

    @Override
    public List<Integer> updateMkDungeonOfMkSeason(
            Set<Integer> idsDungeons,
            Integer idMkseason,
            Short idRegion
    ) {
        final List<LinkMkSeasonMkDungeon> allByIdRegionAndIdMkSeason = this.lkMkSeasonMkDungeonServiceExporter.findAllByIdRegionAndIdMkSeason(idRegion, idMkseason);

        // pour chaque ID de MK Dungeon que l'on souhaite inserer
        idsDungeons.forEach(id -> {

            // on regarde s'il est deja associe a ce mk dungeon
            final Optional<LinkMkSeasonMkDungeon> mkSeasonMkDungeon = allByIdRegionAndIdMkSeason.
                    stream()
                    .filter(linkMkSeasonMkDungeon -> linkMkSeasonMkDungeon.getId().getIdMkDungeon().equals(id))
                    .findAny();
            // s'il n'est pas associé alors on l'insere.
            if (mkSeasonMkDungeon.isEmpty()) {
                final LinkMkSeasonMkDungeon linkMkSeasonMkDungeon = new LinkMkSeasonMkDungeon();
                final LinkMkSeasonMkDungeonId linkMkSeasonMkDungeonId = new LinkMkSeasonMkDungeonId();
                linkMkSeasonMkDungeonId.setIdMkDungeon(id);
                linkMkSeasonMkDungeonId.setIdMkSeason(idMkseason);
                linkMkSeasonMkDungeonId.setIdRegion(idRegion);
                linkMkSeasonMkDungeon.setId(linkMkSeasonMkDungeonId);
                linkMkSeasonMkDungeon.setIdMkSeason(idMkseason);
                linkMkSeasonMkDungeon.setIdRegion(idRegion);
                linkMkSeasonMkDungeon.setMkDungeon(this.mkDungeonServiceExporter.findById(id));
                linkMkSeasonMkDungeon.setMkSeason(this.mkSeasonServiceExporter.findById(idMkseason));
                final LinkMkSeasonMkDungeon save = this.lkMkSeasonMkDungeonServiceExporter.save(linkMkSeasonMkDungeon);
                allByIdRegionAndIdMkSeason.add(save);
            }

        });
        // TODO il y a une erreur ici:  org.hibernate.LazyInitializationException: could not initialize proxy [fr.bavencoff.wow.azerothinteldataapi.db.postaze.mkdungeon.dao.MkDungeon#353] - no Session
        return allByIdRegionAndIdMkSeason
                .stream()
                .map(LinkMkSeasonMkDungeon::getMkDungeon)
                .map(MkDungeon::getId)
                .toList();
    }
}
