package fr.bavencoff.wow.azerothinteldataapi.helpers.mkdungeon.impl;

import fr.bavencoff.wow.azerothinteldataapi.common.mappers.GenericMapper;
import fr.bavencoff.wow.azerothinteldataapi.db.postaze.mkdungeon.dao.MkDungeon;
import fr.bavencoff.wow.azerothinteldataapi.helpers.mkdungeon.model.MkDungeonApi;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING
)
public interface MkDungeonApiMapper extends GenericMapper<MkDungeon, MkDungeonApi> {
}
