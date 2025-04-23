package fr.bavencoff.wow.azerothinteldataapi.common.exceptions.realms;

import lombok.Getter;

@Getter
public class RealmNotFoundException extends RuntimeException {
    private static final String MESSAGE = "Realm with ID = %d does not exist.";
    private final Integer idRealm;

    public RealmNotFoundException(Integer idRealm) {
        this(idRealm, String.format(MESSAGE, idRealm));
    }

    public RealmNotFoundException(Integer idRealm, String message) {
        this(idRealm, message, null);
    }

    public RealmNotFoundException(Integer idRealm, String message, Throwable cause) {
        super(message, cause);
        this.idRealm = idRealm;
    }
}
