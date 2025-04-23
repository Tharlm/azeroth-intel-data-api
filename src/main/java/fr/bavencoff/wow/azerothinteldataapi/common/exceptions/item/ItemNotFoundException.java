package fr.bavencoff.wow.azerothinteldataapi.common.exceptions.item;

import lombok.Getter;

@Getter
public class ItemNotFoundException extends RuntimeException {
    public static final String MESSAGE = "Item with id %d does not exists.";
    private final long itemId;

    public ItemNotFoundException(long itemClassId) {
        this(itemClassId, String.format(MESSAGE, itemClassId));
    }

    public ItemNotFoundException(long itemClassId, String message) {
        this(itemClassId, message, null);
    }

    public ItemNotFoundException(long itemId, String message, Throwable cause) {
        super(message, cause);
        this.itemId = itemId;
    }
}
