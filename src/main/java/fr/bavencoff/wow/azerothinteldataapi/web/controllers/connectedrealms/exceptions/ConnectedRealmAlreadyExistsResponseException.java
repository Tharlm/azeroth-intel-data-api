package fr.bavencoff.wow.azerothinteldataapi.web.controllers.connectedrealms.exceptions;

import fr.bavencoff.wow.azerothinteldataapi.common.exceptions.AzerothException;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

import java.net.URI;

@Getter
public class ConnectedRealmAlreadyExistsResponseException extends AzerothException {
    public static final String TITLE = "Connected realm already exists";
    public static final String MESSAGE = "Connected Realm with id %d already exists.";
    private final Integer id;

    public ConnectedRealmAlreadyExistsResponseException(Integer id) {
        super(
                HttpStatus.CONFLICT,
                ProblemDetail.forStatusAndDetail(
                        HttpStatus.CONFLICT,
                        String.format(MESSAGE, id)),
                null
        );
        this.id = id;
        this.getBody().setProperty("idConnectedRealm", this.id);
        setType(URI.create("connected-realm-already-exists"));
        this.setTitle(TITLE);
    }

}
