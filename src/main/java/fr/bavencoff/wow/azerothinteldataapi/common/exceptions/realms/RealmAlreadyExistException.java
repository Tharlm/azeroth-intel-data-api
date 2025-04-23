package fr.bavencoff.wow.azerothinteldataapi.common.exceptions.realms;

import lombok.Getter;

@Getter
public class RealmAlreadyExistException extends RuntimeException {
    public static final String MESSAGE = "Realm with id %d already exist";
    private final Integer realmId;


    public RealmAlreadyExistException(Integer idRealm) {
        this(idRealm, String.format(MESSAGE, idRealm));
    }

    public RealmAlreadyExistException(Integer idRealm, String message) {
        this(idRealm, message, null);
    }

    public RealmAlreadyExistException(Integer idRealm, String message, Throwable cause) {
        super(message, cause);
        this.realmId = idRealm;
    }

}
