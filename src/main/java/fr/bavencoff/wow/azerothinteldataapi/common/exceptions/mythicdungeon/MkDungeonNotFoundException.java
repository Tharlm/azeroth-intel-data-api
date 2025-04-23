package fr.bavencoff.wow.azerothinteldataapi.common.exceptions.mythicdungeon;

import lombok.Getter;

@Getter
public class MkDungeonNotFoundException extends RuntimeException {
    public static final String MESSAGE = "MK Dungeon with id %d does not exists.";
    private final Integer idMkDungeon;

    public MkDungeonNotFoundException(Integer idMkDungeon) {
        this(idMkDungeon, String.format(MESSAGE, idMkDungeon));
    }

    public MkDungeonNotFoundException(Integer idMkDungeon, String message) {
        this(idMkDungeon, message, null);
    }

    public MkDungeonNotFoundException(Integer idMkDungeon, String message, Throwable cause) {
        super(message, cause);
        this.idMkDungeon = idMkDungeon;
    }
}
