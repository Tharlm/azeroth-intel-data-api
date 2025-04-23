package fr.bavencoff.wow.azerothinteldataapi.common.exceptions.mythicseason;

public class MkSeasonNotFoundException extends RuntimeException {
    public static final String MESSAGE = "MK season with id %d and region %d does not exists.";
    public static final String MESSAGE_TECH = "MK season with id tech %d does not exists.";
    private final Integer idMkSeason;
    private final Short idRegion;
    private final Integer idMkSeasonTech;

    public MkSeasonNotFoundException(Integer idTech) {
        super(String.format(MESSAGE_TECH, idTech));
        this.idMkSeason = null;
        this.idRegion = null;
        this.idMkSeasonTech = idTech;
    }

    public MkSeasonNotFoundException(
            Integer idMkSeason,
            Short idRegion
    ) {
        super(String.format(MESSAGE, idMkSeason, idRegion));
        this.idMkSeason = idMkSeason;
        this.idRegion = idRegion;
        this.idMkSeasonTech = null;
    }
}
