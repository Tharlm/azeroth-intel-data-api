package fr.bavencoff.wow.azerothinteldataapi.web.controllers.realms.exceptions;

import fr.bavencoff.wow.azerothinteldataapi.common.exceptions.AzerothException;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

import java.net.URI;

@Getter
public class RealmAlreadyExistResponseException extends AzerothException {
    public static final String TITLE = "Realm already exists";
    public static final String MESSAGE = "Realm with id %d already exist";
    private final Integer realmId;


    public RealmAlreadyExistResponseException(
            Integer realmId
    ) {
        super(
                HttpStatus.CONFLICT,
                ProblemDetail.forStatusAndDetail(
                        HttpStatus.CONFLICT,
                        String.format(MESSAGE, realmId)
                ),
                null
        );
        setType(URI.create("realm-already-exists"));
        this.realmId = realmId;
        this.getBody().setProperty("idRegion", this.realmId);
        this.setTitle(TITLE);
    }
}
