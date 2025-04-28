package fr.bavencoff.wow.azerothinteldataapi.web.controllers.realms.exceptions;

import fr.bavencoff.wow.azerothinteldataapi.common.exceptions.AzerothException;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

import java.net.URI;

@Getter
public class RealmNotFoundResponseException extends AzerothException {
    public static final String TITLE = "Realm does not exist";
    private static final String MESSAGE = "Realm with ID = %d does not exist.";
    private final Integer idRealm;


    public RealmNotFoundResponseException(
            Integer idRealm
    ) {
        super(
                HttpStatus.CONFLICT,
                ProblemDetail.forStatusAndDetail(
                        HttpStatus.CONFLICT,
                        String.format(MESSAGE, idRealm)
                ),
                null
        );
        setType(URI.create("realm-doests-not-exist"));
        this.idRealm = idRealm;
        this.getBody().setProperty("idRegion", this.idRealm);
        this.setTitle(TITLE);
    }
}
