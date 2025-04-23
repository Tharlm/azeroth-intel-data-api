package fr.bavencoff.wow.azerothinteldataapi.helpers.playables.impl;


import fr.bavencoff.wow.azerothinteldataapi.helpers.playables.model.PlayableSpecializationApi;

public interface PlayableSpeApiService {
    PlayableSpecializationApi updatePlayableSpecializationApi(
            Integer idPs,
            PlayableSpecializationApi api
    );
}
