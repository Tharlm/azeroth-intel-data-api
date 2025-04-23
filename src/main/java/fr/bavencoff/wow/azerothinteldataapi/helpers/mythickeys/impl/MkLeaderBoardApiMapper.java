package fr.bavencoff.wow.azerothinteldataapi.helpers.mythickeys.impl;

import fr.bavencoff.wow.azerothinteldataapi.common.mappers.GenericMapper;
import fr.bavencoff.wow.azerothinteldataapi.db.postaze.mkleaderboard.dao.MkLeaderboard;
import fr.bavencoff.wow.azerothinteldataapi.helpers.mythickeys.model.MkLeaderboardApi;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING
)
public interface MkLeaderBoardApiMapper extends GenericMapper<MkLeaderboard, MkLeaderboardApi> {
}
