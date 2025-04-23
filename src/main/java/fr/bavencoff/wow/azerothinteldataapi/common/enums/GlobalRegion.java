package fr.bavencoff.wow.azerothinteldataapi.common.enums;

import lombok.Getter;

@Getter
public enum GlobalRegion {
    EU("EU", (short) 3),
    US("US", (short) 1),
    TW("TW", (short) 4),
    KR("KR", (short) 2);

    private final String value;
    private final Short id;

    GlobalRegion(
            String value,
            Short id
    ) {
        this.value = value;
        this.id = id;
    }
}
