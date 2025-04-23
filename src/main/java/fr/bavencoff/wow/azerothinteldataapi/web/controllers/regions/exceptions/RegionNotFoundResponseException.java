package fr.bavencoff.wow.azerothinteldataapi.web.controllers.regions.exceptions;

import fr.bavencoff.wow.azerothinteldataapi.common.exceptions.regions.RegionNotFoundException;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.ErrorResponseException;

import java.net.URI;

@Getter
public class RegionNotFoundResponseException extends ErrorResponseException {
    public static final String TITLE = "Region doest not exist";
    private static final String MESSAGE = "Region with ID = %d does not exist.";
    private final Short idRegion;

    public RegionNotFoundResponseException(
            RegionNotFoundException exception
    ) {
        super(
                HttpStatus.NOT_FOUND,
                ProblemDetail.forStatusAndDetail(
                        HttpStatus.NOT_FOUND,
                        String.format(MESSAGE, exception.getIdRegion())
                ),
                exception
        );
        setType(URI.create("region-not-found"));
        this.idRegion = exception.getIdRegion();
        this.getBody().setProperty("idRegion", this.idRegion);
        this.setTitle(TITLE);
    }
}
