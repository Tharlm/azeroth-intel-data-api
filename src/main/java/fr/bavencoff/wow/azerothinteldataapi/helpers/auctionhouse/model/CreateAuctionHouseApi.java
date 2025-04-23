package fr.bavencoff.wow.azerothinteldataapi.helpers.auctionhouse.model;

import fr.bavencoff.wow.azerothinteldataapi.common.enums.AuctionHouseTimeLeft;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

@NoArgsConstructor
@Getter
@Setter
public class CreateAuctionHouseApi implements Serializable {
    @Serial
    private static final long serialVersionUID = -1265715973258185878L;
    private Long id;
    private Long idItem;
    private Integer idConnectedRealm;
    private Integer idPetBreed;
    private Short nbPetLevel;
    private Short idPetQuality;
    private Short idPetSpecies;
    private Long bid;
    private Long buyout;
    private Short quantity;
    private AuctionHouseTimeLeft timeleft;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        CreateAuctionHouseApi that = (CreateAuctionHouseApi) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
