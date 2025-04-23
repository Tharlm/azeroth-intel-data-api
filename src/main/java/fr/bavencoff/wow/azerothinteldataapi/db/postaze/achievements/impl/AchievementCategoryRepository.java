package fr.bavencoff.wow.azerothinteldataapi.db.postaze.achievements.impl;

import fr.bavencoff.wow.azerothinteldataapi.db.postaze.achievements.dao.AchievementCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AchievementCategoryRepository extends JpaRepository<AchievementCategory, Integer> {
    List<AchievementCategory> findByIdIn(List<Integer> ids);
}
