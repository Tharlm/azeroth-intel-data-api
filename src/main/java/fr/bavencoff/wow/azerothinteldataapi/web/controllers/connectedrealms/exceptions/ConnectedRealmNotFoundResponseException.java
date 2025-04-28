package fr.bavencoff.wow.azerothinteldataapi.web.controllers.connectedrealms.exceptions;

import fr.bavencoff.wow.azerothinteldataapi.common.exceptions.AzerothException;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

import java.net.URI;

@Getter
public class ConnectedRealmNotFoundResponseException extends AzerothException {
    public static final String TITLE = "Connected Realm doest not exist";
    public static final String MESSAGE = "Connected Realm with ID = %d doest not exist.";
    private final Integer idConnectedRealm;

    public ConnectedRealmNotFoundResponseException(Integer idConnectedRealm) {
        super(
                HttpStatus.NOT_FOUND,
                ProblemDetail.forStatusAndDetail(
                        HttpStatus.NOT_FOUND,
                        String.format(MESSAGE, idConnectedRealm)),
                null
        );
        this.idConnectedRealm = idConnectedRealm;
        this.getBody().setProperty("idConnectedRealm", this.idConnectedRealm);
        setType(URI.create("cr-not-found"));
        this.setTitle(TITLE);
    }
}
