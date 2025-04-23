package fr.bavencoff.wow.azerothinteldataapi.db.postaze.auctionhouse.dao;

import fr.bavencoff.wow.azerothinteldataapi.common.enums.AuctionHouseTimeLeft;
import fr.bavencoff.wow.azerothinteldataapi.db.postaze.region.dao.RegionDao;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "tb_commodities", schema = "public")
public class CommoditiesDao {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @MapsId
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_region", nullable = false)
    private RegionDao region;

    @NotNull
    @Column(name = "nb_quantity", nullable = false)
    private Short quantity;

    @NotNull
    @Column(name = "nb_unit_price", nullable = false)
    private Integer unitPrice;

    @NotNull
    @Column(name = "id_item", nullable = false)
    private Long idItem;

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
    private Instant dattUpdated;

    @Column(name = "lb_timeleft", columnDefinition = "auction_timeleft not null", nullable = false)
    @Enumerated(EnumType.STRING)
    @NotNull
    private AuctionHouseTimeLeft timeleft;
}
