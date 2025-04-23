package fr.bavencoff.wow.azerothinteldataapi.helpers.playables.impl;


import fr.bavencoff.wow.azerothinteldataapi.helpers.playables.model.PlayableRaceApi;

public interface PlayableRaceApiService {

    /**
     * Insert a new Playable race
     *
     * @param id          ID playable race
     * @param information playable information
     * @return Playable race newly created
     */
    PlayableRaceApi createNewPlayableRace(
            Integer id,
            PlayableRaceApi information
    );

    /**
     * Create or update a playable race
     * @param id ID playable race
     * @param information playable information
     * @return Playable race created/updated
     */
    PlayableRaceApi createOrUpdatePlayableRace(
            Integer id,
            PlayableRaceApi information
    );

}
