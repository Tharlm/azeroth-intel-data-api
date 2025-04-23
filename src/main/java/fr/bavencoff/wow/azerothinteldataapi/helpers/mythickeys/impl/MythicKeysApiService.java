package fr.bavencoff.wow.azerothinteldataapi.helpers.mythickeys.impl;


import fr.bavencoff.wow.azerothinteldataapi.helpers.mythickeys.model.MkLeaderboardApi;
import fr.bavencoff.wow.azerothinteldataapi.helpers.mythickeys.model.MkPeriodApi;
import fr.bavencoff.wow.azerothinteldataapi.helpers.mythickeys.model.MkSeasonApi;

import java.util.List;

public interface MythicKeysApiService {

    /**
     * Ajoute un nouveau MK season. Renvoie une exception s'il existe deja
     *
     * @param idMkSeason        ID mk season
     * @param idRegion          id de la region
     * @param seasonInformation Infos
     * @return MK season
     */
    MkSeasonApi createNewMkSeason(
            Integer idMkSeason,
            Short idRegion,
            MkSeasonApi seasonInformation
    );

    /**
     * Màj un nouveau MK season
     *
     * @param idMkSeason        ID mk season
     * @param idRegion          id de la region
     * @param seasonInformation Infos
     * @return MK season
     */
    MkSeasonApi putMkSeason(
            Integer idMkSeason,
            Short idRegion,
            MkSeasonApi seasonInformation
    );

    /**
     * Met à jour une period
     *
     * @param idMkPeriod  ID MK Period
     * @param idRegion    ID Region
     * @param information information
     * @return MK Period créée
     */
    MkPeriodApi updateMkPeriod(
            Integer idMkPeriod,
            Short idRegion,
            MkPeriodApi information
    );

    /**
     * Retourne la liste des MK seasons
     *
     * @return Lite des MK seasons
     */
    List<MkSeasonApi> findAll();

    MkLeaderboardApi updateMkLeaderboard(
            Integer idMkDungeon,
            Integer idMkPeriod,
            Short idRegion,
            Integer idConnectedRealm,
            MkLeaderboardApi information
    );
}
