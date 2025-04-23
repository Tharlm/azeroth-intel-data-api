package fr.bavencoff.wow.azerothinteldataapi.db.postaze.mkleaderboard.impl;

import fr.bavencoff.wow.azerothinteldataapi.db.postaze.mkleaderboard.dao.MkLeaderboardMember;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MkLeaderboardMemberRepository extends JpaRepository<MkLeaderboardMember, Integer> {
}
