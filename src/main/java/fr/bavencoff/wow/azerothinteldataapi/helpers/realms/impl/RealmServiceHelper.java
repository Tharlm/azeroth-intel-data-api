package fr.bavencoff.wow.azerothinteldataapi.helpers.realms.impl;

import fr.bavencoff.wow.azerothinteldataapi.common.enums.GlobalRegion;
import fr.bavencoff.wow.azerothinteldataapi.helpers.realms.model.ConnectedRealmBo;
import fr.bavencoff.wow.azerothinteldataapi.helpers.realms.model.RealmBo;
import fr.bavencoff.wow.azerothinteldataapi.helpers.realms.model.UpdateCrModel;

import java.util.List;

/**
 * Methode generique pour le realms et connected realms
 */
public interface RealmServiceHelper {

    /**
     * Récupère les informations d'un CR
     *
     * @param id ID du CR
     * @return CR
     */
    ConnectedRealmBo getConnectedRealm(Integer id);

    /**
     * Récupère la liste des CRs.
     * @return Liste des CRs.
     */
    List<ConnectedRealmBo> getConnectedRealms();


    /**
     * Récupère la liste des CRs par région
     *
     * @param region région
     * @return Liste des CRs de la région
     */
    List<ConnectedRealmBo> findConnectedRealmsByRegion(GlobalRegion region);

    /**
     * Crée un nouveau CR. Renvoie une exception s'il existe déjà un CR avec la même ID.
     * @param id ID du CR
     * @param idRegion id de la région
     * @param infos informations sur le CR
     * @return le nouveau CR.
     */
    ConnectedRealmBo createNewConnectedRealm(
            Integer id,
            Short idRegion,
            UpdateCrModel infos
    );

    /**
     * Créé ou met à jour un CR.
     * @param id ID du CR
     * @param idRegion id de la région
     * @param infos informations sur le CR
     * @return CR modifié / créé
     */
    ConnectedRealmBo createOrUpdateConnectedRealm(
            Integer id,
            Short idRegion,
            UpdateCrModel infos
    );

    /**
     * Get realm info by ID
     *
     * @param idRealm ID realm
     * @return Realm info
     */
    RealmBo getRealm(Integer idRealm);

    /**
     * List all realms
     *
     * @return List of realms
     */
    List<RealmBo> getRealms();

}
