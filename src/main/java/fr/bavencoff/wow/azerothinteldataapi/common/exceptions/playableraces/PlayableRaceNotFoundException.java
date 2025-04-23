package fr.bavencoff.wow.azerothinteldataapi.common.exceptions.playableraces;

import lombok.Getter;

@Getter
public class PlayableRaceNotFoundException extends RuntimeException {

    public static final String MESSAGE = "Playable Race with id %d does not exists.";
    private final Integer id;

    public PlayableRaceNotFoundException(Integer id) {
        super(String.format(MESSAGE, id));
        this.id = id;
    }
}
