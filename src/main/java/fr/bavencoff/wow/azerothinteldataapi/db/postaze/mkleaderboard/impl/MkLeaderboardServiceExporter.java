package fr.bavencoff.wow.azerothinteldataapi.db.postaze.mkleaderboard.impl;

import fr.bavencoff.wow.azerothinteldataapi.db.postaze.connectedrealms.dao.ConnectedRealmDao;
import fr.bavencoff.wow.azerothinteldataapi.db.postaze.mkdungeon.dao.MkDungeon;
import fr.bavencoff.wow.azerothinteldataapi.db.postaze.mkleaderboard.dao.MkLeaderboard;
import fr.bavencoff.wow.azerothinteldataapi.db.postaze.mkleaderboard.dao.MkLeaderboardGroup;
import fr.bavencoff.wow.azerothinteldataapi.db.postaze.mkleaderboard.dao.MkLeaderboardMember;
import fr.bavencoff.wow.azerothinteldataapi.db.postaze.mkperiod.dao.MkPeriod;
import fr.bavencoff.wow.azerothinteldataapi.db.postaze.region.dao.RegionDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Service exporter pour les tables MK Leaderboard
 */
@Slf4j
@Service
public class MkLeaderboardServiceExporter {
    private final MkLeaderboardRepository repository;
    private final MkLeaderboardGroupRepository groupRepository;
    private final MkLeaderboardMemberRepository memberRepository;

    @Autowired
    public MkLeaderboardServiceExporter(
            final MkLeaderboardRepository repository,
            final MkLeaderboardGroupRepository groupRepository,
            final MkLeaderboardMemberRepository memberRepository
    ) {
        this.repository = repository;
        this.groupRepository = groupRepository;
        this.memberRepository = memberRepository;
    }

    /**
     * Enregistre un MK leaderboard
     *
     * @param mkLeaderboard entite a enregisrer
     * @return entite a enregisrée
     */
    public MkLeaderboard save(
            MkLeaderboard mkLeaderboard
    ) {
        final MkLeaderboard leaderboard = repository.save(mkLeaderboard);

        groupRepository.saveAll(mkLeaderboard.getGroups());

        final Set<MkLeaderboardMember> members = mkLeaderboard.getGroups().stream().unordered().flatMap(group -> group.getMkLeaderboardMembers().stream()).collect(Collectors.toSet());
        memberRepository.saveAll(members);
        return leaderboard;
    }

    /**
     * Récupère un MK Leaderboard pour les IDs naturels a savoir
     *
     * @param mkDungeon      le MK dungeon
     * @param mkPeriod       la periode
     * @param region         la region
     * @param connectedRealm le CR
     * @return un MK leaderboard optionnel
     */
    public Optional<MkLeaderboard> findOptionalByNaturalId(
            MkDungeon mkDungeon,
            MkPeriod mkPeriod,
            RegionDao region,
            ConnectedRealmDao connectedRealm
    ) {
        return this.repository.findByMkDungeonAndMkPeriodAndRegionAndConnectedRealm(
                mkDungeon,
                mkPeriod,
                region,
                connectedRealm
        );
    }

    /**
     * Supprime un MK leaderboard
     *
     * @param mkLeaderboard entite a supprimer
     */
    public void delete(MkLeaderboard mkLeaderboard) {
        this.repository.delete(mkLeaderboard);
    }

    public List<MkLeaderboardGroup> findGroupsByRegionMkPeriodMkDungeonDuration(
            List<Integer> duration,
            RegionDao region,
            MkPeriod mkPeriod,
            MkDungeon mkDungeon
    ) {
        return this.groupRepository.findByDurationInAndMkLeaderboard_RegionAndMkLeaderboard_MkPeriodAndMkLeaderboard_MkDungeon(
                duration, region, mkPeriod, mkDungeon
        );
    }
}
