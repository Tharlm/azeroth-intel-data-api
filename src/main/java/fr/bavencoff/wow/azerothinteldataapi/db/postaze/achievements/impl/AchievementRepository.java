package fr.bavencoff.wow.azerothinteldataapi.db.postaze.achievements.impl;

import fr.bavencoff.wow.azerothinteldataapi.db.postaze.achievements.dao.Achievement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AchievementRepository extends JpaRepository<Achievement, Integer> {
    List<Achievement> findByIdIn(List<Integer> ids);
}
