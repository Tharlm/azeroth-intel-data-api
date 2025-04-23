package fr.bavencoff.wow.azerothinteldataapi.common.exceptions.item;

import lombok.Getter;

@Getter
public class ItemClassNotFoundException extends RuntimeException {
    public static final String MESSAGE = "Item class with id %d does not exists.";
    private final Short itemClassId;

    public ItemClassNotFoundException(Short itemClassId) {
        this(itemClassId, String.format(MESSAGE, itemClassId));
    }

    public ItemClassNotFoundException(Short itemClassId, String message) {
        this(itemClassId, message, null);
    }

    public ItemClassNotFoundException(Short itemClassId, String message, Throwable cause) {
        super(message, cause);
        this.itemClassId = itemClassId;
    }
}
