package fr.bavencoff.wow.azerothinteldataapi.helpers.playables.impl;


import fr.bavencoff.wow.azerothinteldataapi.helpers.playables.model.PlayableClassApi;

public interface PlayableClassApiService {

    /**
     * Met à jour une Playable Class
     *
     * @param idPc        id playable class
     * @param information information
     * @return playable class
     */
    PlayableClassApi updatePlayableClass(
            Integer idPc,
            PlayableClassApi information
    );
}
