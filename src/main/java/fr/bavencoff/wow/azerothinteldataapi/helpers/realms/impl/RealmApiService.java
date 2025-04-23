package fr.bavencoff.wow.azerothinteldataapi.helpers.realms.impl;

import fr.bavencoff.wow.azerothinteldataapi.common.enums.GlobalRegion;
import fr.bavencoff.wow.azerothinteldataapi.helpers.realms.model.ConnectedRealmApi;
import fr.bavencoff.wow.azerothinteldataapi.helpers.realms.model.RealmApi;

import java.util.List;

/**
 * Methode generique pour le realms et connected realms
 */
public interface RealmApiService {

    /**
     * Récupère les informations d'un CR
     *
     * @param id ID du CR
     * @return CR
     */
    ConnectedRealmApi getConnectedRealm(Integer id);

    /**
     * Récupère la liste des CRs.
     * @return Liste des CRs.
     */
    List<ConnectedRealmApi> getConnectedRealms();


    List<ConnectedRealmApi> searchConnectedRealms(GlobalRegion region);

    /**
     * Crée un nouveau CR. Renvoie une exception s'il existe déjà un CR avec la même ID.
     * @param id ID du CR
     * @param idRegion id de la région
     * @param infos informations sur le CR
     * @return le nouveau CR.
     */
    ConnectedRealmApi createNewConnectedRealm(
            Integer id,
            Short idRegion,
            ConnectedRealmApi infos
    );

    /**
     * Créé ou met à jour un CR.
     * @param id ID du CR
     * @param idRegion id de la région
     * @param infos informations sur le CR
     * @return CR modifié / créé
     */
    ConnectedRealmApi createOrUpdateConnectedRealm(
            Integer id,
            Short idRegion,
            ConnectedRealmApi infos
    );


    RealmApi getRealm(Integer idRealm);
    List<RealmApi> getRealms();

    /**
     * Insere un nouveau realm.
     * Dans la pratique, les realms sont inseres depuis les cr, car l'API blizzard renvoie toutes les informations relatives á un realm depuis le endpoint des CRs.
     * @param id ID du realm
     * @param idConnectedRealm id du CR
     * @param idRegion id region
     * @param info information sur le realm
     * @return le realm créé.
     */
    RealmApi createNewRealm(
            Integer id,
            Integer idConnectedRealm,
            Short idRegion,
            RealmApi info
    );
    RealmApi createOrUpdateRealm(
            Integer id,
            Integer idConnectedRealm,
            Short idRegion,
            RealmApi info
    );

}
