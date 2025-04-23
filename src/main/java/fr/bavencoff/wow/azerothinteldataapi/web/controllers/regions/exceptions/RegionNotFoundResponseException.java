package fr.bavencoff.wow.azerothinteldataapi.web.controllers.regions.exceptions;

import fr.bavencoff.wow.azerothinteldataapi.common.exceptions.AzerothException;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

import java.net.URI;

@Getter
public class RegionNotFoundResponseException extends AzerothException {
    public static final String TITLE = "Region doest not exist";
    private static final String MESSAGE = "Region with ID = %d does not exist.";
    private final Short idRegion;

    public RegionNotFoundResponseException(
            Short idRegion
    ) {
        super(
                HttpStatus.NOT_FOUND,
                ProblemDetail.forStatusAndDetail(
                        HttpStatus.NOT_FOUND,
                        String.format(MESSAGE, idRegion)
                ),
                null
        );
        setType(URI.create("region-not-found"));
        this.idRegion = idRegion;
        this.getBody().setProperty("idRegion", this.idRegion);
        this.setTitle(TITLE);
    }
}
