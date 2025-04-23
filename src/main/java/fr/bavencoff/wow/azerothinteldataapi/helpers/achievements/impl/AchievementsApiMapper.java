package fr.bavencoff.wow.azerothinteldataapi.helpers.achievements.impl;

import fr.bavencoff.wow.azerothinteldataapi.common.mappers.GenericMapper;
import fr.bavencoff.wow.azerothinteldataapi.db.postaze.achievements.dao.Achievement;
import fr.bavencoff.wow.azerothinteldataapi.helpers.achievements.model.AchievementApi;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface AchievementsApiMapper extends GenericMapper<Achievement, AchievementApi> {
}
