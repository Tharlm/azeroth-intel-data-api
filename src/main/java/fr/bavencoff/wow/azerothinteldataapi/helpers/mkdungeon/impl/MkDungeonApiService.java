package fr.bavencoff.wow.azerothinteldataapi.helpers.mkdungeon.impl;

import fr.bavencoff.wow.azerothinteldataapi.helpers.mkdungeon.model.MkDungeonApi;

import java.util.List;
import java.util.Set;

/**
 * Service général utilisé pour les MK dungeons.
 */
public interface MkDungeonApiService {

    /**
     * Ajoute un nouveau MK dungeon. Renvoie une execption s'il existe deja
     *
     * @param id          ID du MK dungeon
     * @param idDungeon   ID du dungeon lie
     * @param information infos
     * @return MK dungeon
     */
    MkDungeonApi createNewMkDungeon(
            Integer id,
            Integer idDungeon,
            MkDungeonApi information
    );

    /**
     * Ajoute un nouveau MK dungeon.
     * @param id ID du MK dungeon
     * @param idDungeon ID du dungeon lie
     * @param information infos
     * @return MK dungeon
     */
    MkDungeonApi createOrUpdateMkDungeon(
            Integer id,
            Integer idDungeon,
            MkDungeonApi information
    );

    /**
     * Récupère la liste des MK dungeons
     * @return liste des MK dungeons
     */
    List<MkDungeonApi> findAll();


    /**
     * @param idsDungeons
     * @param idMkseason
     * @param idRegion
     * @return
     */
    List<Integer> updateMkDungeonOfMkSeason(
            Set<Integer> idsDungeons,
            Integer idMkseason,
            Short idRegion
    );

    /**
     * @param idMkseason
     * @param idRegion
     * @return
     */
    List<MkDungeonApi> findMkDungeonOfMkSeason(
            Integer idMkseason,
            Short idRegion
    );

}
