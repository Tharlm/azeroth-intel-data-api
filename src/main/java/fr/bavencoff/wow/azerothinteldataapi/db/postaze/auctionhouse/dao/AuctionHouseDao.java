package fr.bavencoff.wow.azerothinteldataapi.db.postaze.auctionhouse.dao;

import fr.bavencoff.wow.azerothinteldataapi.common.enums.AuctionHouseTimeLeft;
import fr.bavencoff.wow.azerothinteldataapi.db.postaze.connectedrealms.dao.ConnectedRealmDao;
import fr.bavencoff.wow.azerothinteldataapi.db.postaze.region.dao.RegionDao;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.proxy.HibernateProxy;

import java.io.Serial;
import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "tb_auction_house", indexes = {
        @Index(name = "idx_auctionhousedao_id_item", columnList = "id_item")
})
public class AuctionHouseDao implements Serializable {

    @Serial
    private static final long serialVersionUID = 2336893394486503891L;

    @EmbeddedId
    private AuctionHouseIdDao id;

    @NotNull
    @Column(name = "id_item", nullable = false)
    private Long idItem;

    @Column(name = "id_pet_breed")
    private Integer idPetBreed;

    @Column(name = "nb_pet_level")
    private Short nbPetLevel;

    @Column(name = "id_pet_quality")
    private Short idPetQuality;

    @Column(name = "id_pet_species")
    private Short idPetSpecies;

    @Column(name = "nb_bid")
    private Long bid;

    @Column(name = "nb_buyout")
    private Long buyout;

    @NotNull
    @Column(name = "nb_quantity", nullable = false)
    private Short quantity;

    @NotNull
    @ColumnDefault("false")
    @Column(name = "bo_deleted", nullable = false)
    private Boolean deleted = false;

    @NotNull
    @ColumnDefault("now()")
    @Column(name = "dt_imported", nullable = false)
    private Instant dateImported;

    @NotNull
    @ColumnDefault("now()")
    @Column(name = "dt_updated", nullable = false)
    private Instant dateUpdated;

    @Column(name = "lb_timeleft", nullable = false)
    @NotNull
    @Enumerated(EnumType.STRING)
//    @JdbcType(value = PostgreSQLEnumJdbcType.class)
    private AuctionHouseTimeLeft timeleft;

    @Column(name = "bo_sold")
    private Boolean Sold;

    @MapsId("idRegion")
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_region", nullable = false)
    @NotNull
    private RegionDao region;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_connected_realm", nullable = false)
    private ConnectedRealmDao connectedRealm;


    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        AuctionHouseDao that = (AuctionHouseDao) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "idItem = " + idItem + ")";
    }
}
