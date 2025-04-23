package fr.bavencoff.wow.azerothinteldataapi.helpers.achievements.impl;

import fr.bavencoff.wow.azerothinteldataapi.helpers.achievements.model.AchievementApi;
import fr.bavencoff.wow.azerothinteldataapi.helpers.achievements.model.AchievementCategoryApi;

import java.util.List;

public interface AchievementApiService {

    AchievementCategoryApi addCategoryAchievement(
            Integer idCategory,
            Integer idParentCategory,
            AchievementCategoryApi information
    );

    AchievementCategoryApi putCategoryAchievement(
            Integer idAchievementCategory,
            Integer idParentCategory,
            AchievementCategoryApi information
    );

    List<AchievementCategoryApi> findAllCategoryAchievement();

    List<AchievementApi> findAllAchievement();
}
