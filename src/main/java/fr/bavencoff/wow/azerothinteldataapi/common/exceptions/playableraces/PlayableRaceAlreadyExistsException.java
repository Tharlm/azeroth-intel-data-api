package fr.bavencoff.wow.azerothinteldataapi.common.exceptions.playableraces;

import lombok.Getter;

@Getter
public class PlayableRaceAlreadyExistsException extends RuntimeException {

    public static final String MESSAGE = "Playable Race with id %d already exists.";
    private final Integer id;

    public PlayableRaceAlreadyExistsException(Integer id) {
        super(String.format(MESSAGE, id));
        this.id = id;
    }
}
