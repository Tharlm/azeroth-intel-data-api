package fr.bavencoff.wow.azerothinteldataapi.common.exceptions.mkperiod;

import lombok.Getter;

@Getter
public class MkPeriodNotFoundException extends RuntimeException {

    public static final String MESSAGE = "MK period with id %d and region %d does not exists.";
    private final Integer idMkPeriod;
    private final Short idRegion;

    public MkPeriodNotFoundException(
            Short idRegion,
            Integer idMkPeriod
    ) {
        this(idRegion, idMkPeriod, String.format(MESSAGE, idMkPeriod, idRegion));
    }

    public MkPeriodNotFoundException(Short idRegion, Integer idMkPeriod, String message) {
        this(idRegion, idMkPeriod, message, null);
    }

    public MkPeriodNotFoundException(Short idRegion, Integer idMkPeriod, String message, Throwable cause) {
        super(message, cause);
        this.idRegion = idRegion;
        this.idMkPeriod = idMkPeriod;
    }
}
