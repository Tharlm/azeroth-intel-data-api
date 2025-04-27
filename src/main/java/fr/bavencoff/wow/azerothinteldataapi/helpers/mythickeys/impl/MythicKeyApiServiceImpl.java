package fr.bavencoff.wow.azerothinteldataapi.helpers.mythickeys.impl;

import fr.bavencoff.wow.azerothinteldataapi.common.exceptions.mythicseason.MkSeasonAlreadyExistsException;
import fr.bavencoff.wow.azerothinteldataapi.common.utils.NumberUtils;
import fr.bavencoff.wow.azerothinteldataapi.db.postaze.connectedrealms.dao.ConnectedRealmDao;
import fr.bavencoff.wow.azerothinteldataapi.db.postaze.connectedrealms.impl.ConnectedRealmDaoServiceExporter;
import fr.bavencoff.wow.azerothinteldataapi.db.postaze.mkdungeon.dao.MkDungeon;
import fr.bavencoff.wow.azerothinteldataapi.db.postaze.mkdungeon.impl.MkDungeonServiceExporter;
import fr.bavencoff.wow.azerothinteldataapi.db.postaze.mkleaderboard.dao.MkLeaderboard;
import fr.bavencoff.wow.azerothinteldataapi.db.postaze.mkleaderboard.dao.MkLeaderboardGroup;
import fr.bavencoff.wow.azerothinteldataapi.db.postaze.mkleaderboard.dao.MkLeaderboardMember;
import fr.bavencoff.wow.azerothinteldataapi.db.postaze.mkleaderboard.impl.MkLeaderboardServiceExporter;
import fr.bavencoff.wow.azerothinteldataapi.db.postaze.mkperiod.dao.MkPeriod;
import fr.bavencoff.wow.azerothinteldataapi.db.postaze.mkperiod.impl.MkPeriodServiceExporter;
import fr.bavencoff.wow.azerothinteldataapi.db.postaze.mkseason.dao.MkSeason;
import fr.bavencoff.wow.azerothinteldataapi.db.postaze.mkseason.impl.MkSeasonServiceExporter;
import fr.bavencoff.wow.azerothinteldataapi.db.postaze.region.dao.RegionDao;
import fr.bavencoff.wow.azerothinteldataapi.db.postaze.region.impl.RegionDaoServiceExporter;
import fr.bavencoff.wow.azerothinteldataapi.helpers.mythickeys.model.MkLeaderboardApi;
import fr.bavencoff.wow.azerothinteldataapi.helpers.mythickeys.model.MkPeriodApi;
import fr.bavencoff.wow.azerothinteldataapi.helpers.mythickeys.model.MkSeasonApi;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Slf4j
@Service
public class MythicKeyApiServiceImpl implements MythicKeysApiService {
    private final MkSeasonServiceExporter mkSeasonServiceExporter;
    private final RegionDaoServiceExporter regionsServiceExporter;
    private final MkPeriodServiceExporter mkPeriodServiceExporter;
    private final ConnectedRealmDaoServiceExporter connectedRealmDaoServiceExporter;
    private final MkDungeonServiceExporter mkDungeonServiceExporter;
    private final MkLeaderboardServiceExporter leaderboardServiceExporter;
    private final MkLeaderboardServiceExporter mkLeaderboardServiceExporter;
    private final MkSeasonApiMapper mkSeasonApiMapper;
    private final MkLeaderBoardApiMapper mkLeaderBoardApiMapper;
    private final MkPeriodApiMapper mkPeriodApiMapper;

    @Autowired
    public MythicKeyApiServiceImpl(
            final MkSeasonServiceExporter mkSeasonServiceExporter,
            final RegionDaoServiceExporter regionsServiceExporter,
            final MkPeriodServiceExporter mkPeriodServiceExporter,
            final ConnectedRealmDaoServiceExporter connectedRealmDaoServiceExporter,
            final MkDungeonServiceExporter mkDungeonServiceExporter,
            final MkLeaderboardServiceExporter leaderboardServiceExporter,
            final MkLeaderboardServiceExporter mkLeaderboardServiceExporter,
            final MkSeasonApiMapper mkSeasonApiMapper,
            final MkLeaderBoardApiMapper mkLeaderBoardApiMapper,
            final MkPeriodApiMapper mkPeriodApiMapper
    ) {
        this.mkSeasonServiceExporter = mkSeasonServiceExporter;
        this.regionsServiceExporter = regionsServiceExporter;
        this.mkPeriodServiceExporter = mkPeriodServiceExporter;
        this.connectedRealmDaoServiceExporter = connectedRealmDaoServiceExporter;
        this.mkDungeonServiceExporter = mkDungeonServiceExporter;
        this.leaderboardServiceExporter = leaderboardServiceExporter;
        this.mkLeaderboardServiceExporter = mkLeaderboardServiceExporter;
        this.mkSeasonApiMapper = mkSeasonApiMapper;
        this.mkLeaderBoardApiMapper = mkLeaderBoardApiMapper;
        this.mkPeriodApiMapper = mkPeriodApiMapper;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public MkSeasonApi createNewMkSeason(
            Integer idMkSeason,
            Short idRegion,
            MkSeasonApi seasonInformation
    ) {

        RegionDao regionDao = this.regionsServiceExporter.findById(idRegion);
        Optional<MkSeason> mkSeasonAndRegion = this.mkSeasonServiceExporter.findOptionalByIdMkSeasonAndRegion(idMkSeason, regionDao);
        if (mkSeasonAndRegion.isPresent()) {
            throw new MkSeasonAlreadyExistsException(idMkSeason, idRegion);
        }

        MkSeason mkSeason = new MkSeason();
        mkSeason.setIdMkSeason(idMkSeason);

        return mkSeasonApiMapper.daoToApi(this.saveMkSeason(mkSeason, regionDao, seasonInformation));
    }

    /**
     * {@inheritDoc}
     */
    @Transactional
    @Override
    public MkSeasonApi putMkSeason(
            Integer idMkSeason,
            Short idRegion,
            MkSeasonApi seasonInformation
    ) {
        RegionDao regionDao = this.regionsServiceExporter.findById(idRegion);
        final Optional<MkSeason> optionalMkSeason = this.mkSeasonServiceExporter.findOptionalByIdMkSeasonAndRegion(idMkSeason, regionDao);
        if (optionalMkSeason.isEmpty()) {
            MkSeason mkSeason = new MkSeason();
            mkSeason.setIdMkSeason(idMkSeason);
            return mkSeasonApiMapper.daoToApi(this.saveMkSeason(mkSeason, regionDao, seasonInformation));
        }
        return mkSeasonApiMapper.daoToApi(this.saveMkSeason(optionalMkSeason.get(), regionDao, seasonInformation));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<MkSeasonApi> findAll() {
        return mkSeasonApiMapper.daosToApis(this.mkSeasonServiceExporter.findAll());
    }

    /**
     * Enregistre une MK season
     *
     * @param mkSeason          MK season
     * @param region            region
     * @param seasonInformation infos
     * @return MK seasons màj
     */
    private MkSeason saveMkSeason(
            MkSeason mkSeason,
            RegionDao region,
            MkSeasonApi seasonInformation
    ) {
        mkSeason.setRegion(region);
        mkSeason.setName(seasonInformation.getName());
        mkSeason.setStartDate(seasonInformation.getStartDate());
        mkSeason.setEndDate(seasonInformation.getEndDate());

//        final List<MkPeriod> byRegionAndIdsMkPeriod = this.mkPeriodServiceExporter.findByRegionAndIdsMkPeriod(region, seasonInformation.getPeriodInformations().stream().unordered().map(MkPeriodApi::getIdMkPeriod).toList());

        // pour chaque période fournies
        seasonInformation.getPeriodInformations().forEach(periodInformation -> {

            // on regarde si elle existe dans l'entite
            Optional<MkPeriod> optionalMkPeriod = mkSeason.getPeriods().stream().filter(mkPeriod ->
                    NumberUtils.equals(periodInformation.getIdMkPeriod(), mkPeriod.getIdMkPeriod()) && NumberUtils.equals(region.getId(), mkPeriod.getRegion().getId())
            ).findFirst();

            MkPeriod mkPeriod;
            // si elle n'existe pas, on vérifie si elle existe en DB, en effet une période peut être attachée a plusieurs saison
            // si elle existe, on met a jour les infos sur les dates
            if (optionalMkPeriod.isEmpty()) {
                // TODO on pourrait optimiser la requete avec un IN
//                final boolean mkPeriodAlreadyExisting = byRegionAndIdsMkPeriod.stream().anyMatch(mkPeriodDao -> mkPeriodDao.getRegion().getId().equals(region.getId()) && mkPeriodDao.getIdMkPeriod().equals(periodInformation.getIdMkPeriod()));
                Optional<MkPeriod> optionalByRegionAndIdMkRegion = this.mkPeriodServiceExporter.findOptionalByRegionAndIdMkPeriod(region, periodInformation.getIdMkPeriod());

                // si la periode n'existe toujours pas, alors on l'insére
                // sinon si elle existe on met a jour les infos sur les dates ainsi que sur les MK seasons rattachées
                if (optionalByRegionAndIdMkRegion.isEmpty()) {
                    mkPeriod = new MkPeriod();
                    mkPeriod.setIdMkPeriod(periodInformation.getIdMkPeriod());
                    mkPeriod.setStartDate(periodInformation.getStartDate());
                    mkPeriod.setEndDate(periodInformation.getEndDate());
                    mkPeriod.setRegion(region);
                    mkPeriod.addSeason(mkSeason);
                } else {
                    mkPeriod = optionalByRegionAndIdMkRegion.get();
                    if (mkPeriod.getStartDate() == null) {
                        mkPeriod.setStartDate(periodInformation.getStartDate());
                    }
                    if (mkPeriod.getEndDate() == null) {
                        mkPeriod.setEndDate(periodInformation.getEndDate());
                    }
                    mkSeason.addMkPeriod(mkPeriod);
                }
            } else {
                mkPeriod = optionalMkPeriod.get();
                if (mkPeriod.getStartDate() == null) {
                    mkPeriod.setStartDate(periodInformation.getStartDate());
                }
                if (mkPeriod.getEndDate() == null) {
                    mkPeriod.setEndDate(periodInformation.getEndDate());
                }
            }
        });

        return this.mkSeasonServiceExporter.save(mkSeason);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public MkPeriodApi updateMkPeriod(
            Integer idMkPeriod,
            Short idRegion,
            MkPeriodApi information
    ) {
        RegionDao regionDao = this.regionsServiceExporter.findById(idRegion);
        MkPeriod mkPeriod = this.mkPeriodServiceExporter.findByRegionAndIdMkPeriod(regionDao, idMkPeriod);

        mkPeriod.setStartDate(information.getStartDate());
        mkPeriod.setEndDate(information.getEndDate());

        return mkPeriodApiMapper.daoToApi(mkPeriodServiceExporter.save(mkPeriod));
    }

    /**
     * {@inheritDoc}
     */
    @Transactional
    public MkLeaderboardApi updateMkLeaderboard(
            Integer idMkDungeon,
            Integer idMkPeriod,
            Short idRegion,
            Integer idConnectedRealm,
            MkLeaderboardApi information
    ) {
        RegionDao region = this.regionsServiceExporter.findById(idRegion);
        MkPeriod mkPeriod = this.mkPeriodServiceExporter.findByRegionAndIdMkPeriod(region, idMkPeriod);
        MkDungeon mkDungeon = this.mkDungeonServiceExporter.findById(idMkDungeon);
        ConnectedRealmDao connectedRealm = this.connectedRealmDaoServiceExporter.findById(idConnectedRealm);

        log.debug("Recuperation du mkLeaderboard");
        final Optional<MkLeaderboard> byNaturalId = this.leaderboardServiceExporter.findOptionalByNaturalId(mkDungeon, mkPeriod, region, connectedRealm);
        log.debug("Recuperation du mkLeaderboard termine");

        log.debug("Suppression du mkLeaderboard si celui ci existe");
        byNaturalId.ifPresent(mkLeaderboardServiceExporter::delete);
        log.debug("Suppression du mkLeaderboard termine");

        MkLeaderboard mkLeaderboard = new MkLeaderboard();

        mkLeaderboard.setMkDungeon(mkDungeon);
        mkLeaderboard.setMkPeriod(mkPeriod);
        mkLeaderboard.setRegion(region);
        mkLeaderboard.setConnectedRealm(connectedRealm);

        mkLeaderboard.setIdKeystoneAffixe1(information.getIdKeystoneAffixe1());
        mkLeaderboard.setStartLevelAffixe1(information.getStartLevelAffixe1());
        mkLeaderboard.setIdKeystoneAffixe2(information.getIdKeystoneAffixe2());
        mkLeaderboard.setStartLevelAffixe2(information.getStartLevelAffixe2());
        mkLeaderboard.setIdKeystoneAffixe3(information.getIdKeystoneAffixe3());
        mkLeaderboard.setStartLevelAffixe3(information.getStartLevelAffixe3());

        log.debug("Recuperation du MkLeaderboardGroup");
        final List<MkLeaderboardGroup> groupsByRegionMkPeriodMkDungeonDuration = this.mkLeaderboardServiceExporter.findGroupsByRegionMkPeriodMkDungeonDuration(
                information.getGroups().stream().unordered().map(MkLeaderboardApi.GroupInformatiom::getRanking).toList(),
                region,
                mkPeriod,
                mkDungeon
        );
        log.debug("Recuperation du MkLeaderboardGroup terminee");
        List<MkLeaderboardGroup> groups = new ArrayList<>();
        log.debug("Traitement des groupes");
        information.getGroups().forEach(groupInformatiom -> {
            final boolean groupExistingAmongOthers = isGroupExistingAmongOthers(groupInformatiom, groupsByRegionMkPeriodMkDungeonDuration);

            // s'il existe deja un groupe (associe a un autre connected realm) alors on ne fait rien
            if (!groupExistingAmongOthers) {
                MkLeaderboardGroup mkLeaderboardGroup = new MkLeaderboardGroup();

                mkLeaderboardGroup.setMkLeaderboard(mkLeaderboard);
                mkLeaderboardGroup.setDuration(groupInformatiom.getDuration());
                mkLeaderboardGroup.setRanking(groupInformatiom.getRanking());
                mkLeaderboardGroup.setKeyLevel(groupInformatiom.getKeyLevel());
                mkLeaderboardGroup.setDateCompleted(groupInformatiom.getDateCompleted());

                Set<MkLeaderboardMember> members = new LinkedHashSet<>();
                groupInformatiom.getMembers().forEach(memberInformatiom -> {
                    MkLeaderboardMember mkLeaderboardMember = new MkLeaderboardMember();
                    mkLeaderboardMember.setMkLeaderboardGroup(mkLeaderboardGroup);
                    mkLeaderboardMember.setIdPlayer(memberInformatiom.getIdPlayer());
                    mkLeaderboardMember.setNamePlayer(memberInformatiom.getNamePlayer());
                    mkLeaderboardMember.setFaction(memberInformatiom.getFaction());
                    // TODO revoir ces 2 champs si on ne fait pas des cles etrangeres
                    mkLeaderboardMember.setIdRealm(memberInformatiom.getIdRealm());
                    mkLeaderboardMember.setIdSpecialization(memberInformatiom.getIdSpecialization());
                    members.add(mkLeaderboardMember);
                });

                mkLeaderboardGroup.setMkLeaderboardMembers(members);
                groups.add(mkLeaderboardGroup);
            }

        });

        log.debug("Traitement des groupes termine");
        mkLeaderboard.setGroups(groups);

        log.debug("Sauvegarde des leaderboard");
        final MkLeaderboard save = this.leaderboardServiceExporter.save(mkLeaderboard);
        log.debug("Sauvegarde des leaderboard terminee");
        return mkLeaderBoardApiMapper.daoToApi(save);
    }

    private static boolean isGroupExistingAmongOthers(
            MkLeaderboardApi.GroupInformatiom group,
            List<MkLeaderboardGroup> groups
    ) {
        if (group == null || groups.isEmpty()) {
            return false;
        }
        return groups.stream()
                .unordered()
                .anyMatch(groupInformatiom -> groupInformatiom.getRanking().equals(group.getRanking()) && groupInformatiom.getDateCompleted().equals(group.getDateCompleted()));
    }

}
