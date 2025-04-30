package fr.bavencoff.wow.azerothinteldataapi.web.controllers.regions.exceptions;

import fr.bavencoff.wow.azerothinteldataapi.common.enums.GlobalRegion;
import fr.bavencoff.wow.azerothinteldataapi.common.exceptions.AzerothException;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

import java.net.URI;

@Getter
public class RegionNotFoundResponseException extends AzerothException {
    public static final String TITLE = "Region doest not exist";
    private static final String MESSAGE_ID = "Region with ID = %d does not exist.";
    private static final String MESSAGE_TAG = "Region with tag = %s does not exist.";
    private final Short idRegion;
    private final GlobalRegion tag;

    public RegionNotFoundResponseException(
            Short idRegion
    ) {
        super(
                HttpStatus.NOT_FOUND,
                ProblemDetail.forStatusAndDetail(
                        HttpStatus.NOT_FOUND,
                        String.format(MESSAGE_ID, idRegion)
                ),
                null
        );
        setType(URI.create("region-not-found"));
        this.idRegion = idRegion;
        this.tag = null;
        this.getBody().setProperty("idRegion", this.idRegion);
        this.setTitle(TITLE);
    }

    public RegionNotFoundResponseException(
            final GlobalRegion tag
    ) {
        super(
                HttpStatus.NOT_FOUND,
                ProblemDetail.forStatusAndDetail(
                        HttpStatus.NOT_FOUND,
                        String.format(MESSAGE_TAG, tag)
                ),
                null
        );
        setType(URI.create("region-not-found"));
        this.idRegion = null;
        this.tag = tag;
        this.getBody().setProperty("tag", this.tag);
        this.setTitle(TITLE);
    }
}
