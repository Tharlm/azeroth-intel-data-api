package fr.bavencoff.wow.azerothinteldataapi.common.exceptions.connectedrealms;

import lombok.Getter;

@Getter
public class ConnectedRealmAlreadyExistsException extends RuntimeException {
    public static final String MESSAGE = "Connected Realm with id %d already exists.";
    private final Integer id;

    public ConnectedRealmAlreadyExistsException(Integer id) {
        this(id, String.format(MESSAGE, id));
    }

    public ConnectedRealmAlreadyExistsException(Integer id, String message) {
        this(id, message, null);
    }

    public ConnectedRealmAlreadyExistsException(Integer id, String message, Throwable cause) {
        super(message, cause);
        this.id = id;
    }
}
