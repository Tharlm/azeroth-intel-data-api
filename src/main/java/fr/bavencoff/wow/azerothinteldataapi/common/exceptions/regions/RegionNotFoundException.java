package fr.bavencoff.wow.azerothinteldataapi.common.exceptions.regions;

import lombok.Getter;

@Getter
public class RegionNotFoundException extends RuntimeException {
    private static final String MESSAGE = "Region with ID = %d does not exist.";

    private final Short idRegion;

    public RegionNotFoundException(Short idRegion) {
        this(idRegion, String.format(MESSAGE, idRegion));
    }

    public RegionNotFoundException(Short idRegion, String message) {
        this(idRegion, message, null);
    }

    public RegionNotFoundException(Short idRegion, String message, Throwable cause) {
        super(message, cause);
        this.idRegion = idRegion;
    }

}
