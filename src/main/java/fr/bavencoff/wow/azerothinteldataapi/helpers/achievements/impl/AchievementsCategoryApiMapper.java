package fr.bavencoff.wow.azerothinteldataapi.helpers.achievements.impl;

import fr.bavencoff.wow.azerothinteldataapi.common.mappers.GenericMapper;
import fr.bavencoff.wow.azerothinteldataapi.db.postaze.achievements.dao.AchievementCategory;
import fr.bavencoff.wow.azerothinteldataapi.helpers.achievements.model.AchievementCategoryApi;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface AchievementsCategoryApiMapper extends GenericMapper<AchievementCategory, AchievementCategoryApi> {
}
