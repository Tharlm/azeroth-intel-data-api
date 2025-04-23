package fr.bavencoff.wow.azerothinteldataapi.common.exceptions.connectedrealms;

import lombok.Getter;

@Getter
public class ConnectedRealmNotFoundException extends RuntimeException {
    public static final String MESSAGE = "Connected Realm with ID = %d doest not exist.";
    private final Integer idConnectedRealm;

    public ConnectedRealmNotFoundException(Integer idConnectedRealm) {
        this(idConnectedRealm, String.format(MESSAGE, idConnectedRealm));
    }

    public ConnectedRealmNotFoundException(Integer idConnectedRealm, String message) {
        this(idConnectedRealm, message, null);
    }

    public ConnectedRealmNotFoundException(Integer idConnectedRealm, String message, Throwable cause) {
        super(message, cause);
        this.idConnectedRealm = idConnectedRealm;
    }
}
