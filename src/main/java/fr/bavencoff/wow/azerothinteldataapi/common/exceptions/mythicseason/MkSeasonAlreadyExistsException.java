package fr.bavencoff.wow.azerothinteldataapi.common.exceptions.mythicseason;

import lombok.Getter;

@Getter
public class MkSeasonAlreadyExistsException extends RuntimeException {
    public static final String MESSAGE = "MK season with id %d and region %d already exists.";
    private final Integer idMkSeason;
    private final Short idRegion;

    public MkSeasonAlreadyExistsException(Integer idMkSeason, Short idRegion) {
        this(idMkSeason, idRegion, String.format(MESSAGE, idMkSeason, idRegion));
    }

    public MkSeasonAlreadyExistsException(Integer idMkSeason, Short idRegion, String message) {
        this(idMkSeason, idRegion, message, null);
    }

    public MkSeasonAlreadyExistsException(Integer idMkSeason, Short idRegion, String message, Throwable cause) {
        super(message, cause);
        this.idMkSeason = idMkSeason;
        this.idRegion = idRegion;
    }
}
