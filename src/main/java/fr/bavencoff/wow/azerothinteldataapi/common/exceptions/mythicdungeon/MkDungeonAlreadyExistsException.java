package fr.bavencoff.wow.azerothinteldataapi.common.exceptions.mythicdungeon;

import lombok.Getter;

@Getter
public class MkDungeonAlreadyExistsException extends RuntimeException {

    public static final String MESSAGE = "MK Dungeon with id %d already exists.";
    private final Integer id;


    public MkDungeonAlreadyExistsException(Integer id) {
        this(id, String.format(MESSAGE, id));
    }

    public MkDungeonAlreadyExistsException(Integer id, String message) {
        this(id, message, null);
    }

    public MkDungeonAlreadyExistsException(Integer id, String message, Throwable cause) {
        super(message, cause);
        this.id = id;
    }

}
