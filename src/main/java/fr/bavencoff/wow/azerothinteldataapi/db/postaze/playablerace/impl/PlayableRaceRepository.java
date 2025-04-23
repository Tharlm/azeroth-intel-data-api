package fr.bavencoff.wow.azerothinteldataapi.db.postaze.playablerace.impl;

import fr.bavencoff.wow.azerothinteldataapi.db.postaze.playablerace.dao.PlayableRaceDao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayableRaceRepository extends JpaRepository<PlayableRaceDao, Integer> {
}
