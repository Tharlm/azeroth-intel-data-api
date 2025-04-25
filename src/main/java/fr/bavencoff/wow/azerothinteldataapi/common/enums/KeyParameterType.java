package fr.bavencoff.wow.azerothinteldataapi.common.enums;

import lombok.Getter;

/**
 * Liste des clés pour les types.
 */
@Getter
public enum KeyParameterType {
    REA("REALM"),
    CRS("CONNECTED_REALM_STATUS"),
    CRP("CONNECTED_REALM_POPULATION"),
    PRF("PLAYABLE_RACE_FACTION"),
    PSR("PLAYABLE_SPE_ROLE"),
    PSP("PLAYABLE_SPE_PRIMARY_STAT"),
    ITQ("ITEM_QUALITY"),
    ITI("ITEM_INVENTORY");

    private final String code;

    KeyParameterType(String code) {
        this.code = code;
    }
}
