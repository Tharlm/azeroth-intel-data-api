package fr.bavencoff.wow.azerothinteldataapi.db.postaze.mkseason.dao;

import fr.bavencoff.wow.azerothinteldataapi.db.postaze.mkperiod.dao.MkPeriod;
import fr.bavencoff.wow.azerothinteldataapi.db.postaze.region.dao.RegionDao;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedAttributeNode;
import jakarta.persistence.NamedEntityGraph;
import jakarta.persistence.NamedSubgraph;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

/**
 * Entité liée à la table tb_mk_season.
 * <p>
 * Les ids naturels sont idMkSeason et la région.
 * Il peut y avoir la même idMkSeason pour plusieurs régions.
 * L'ID tech unifie tout ça.
 */
@NoArgsConstructor
@Entity
@Table(
        name = "tb_mk_season",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "tb_mk_season_id_region_id_mk_season_uindex",
                        columnNames = {"id_region", "id_mk_season"}
                )
        }
)
@Getter
@Setter
@NamedEntityGraph(
        name = "get-season-entity-graph",
        attributeNodes = {
                @NamedAttributeNode("region"),
                @NamedAttributeNode(value = "periods", subgraph = "periods-sub-graphs")
        },
        subgraphs = {
                @NamedSubgraph(
                        name = "periods-sub-graphs",
                        attributeNodes = {
                                @NamedAttributeNode("region")
                        }
                )
        }
)
public class MkSeason implements Serializable {
    @Serial
    private static final long serialVersionUID = 3359147309331212399L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tec_mk_season", nullable = false, updatable = false)
    private Integer idTech;
    @Column(
            name = "id_mk_season",
            updatable = false,
            nullable = false
    )
    private Integer idMkSeason;
    @Column(
            name = "id_region",
            updatable = false,
            nullable = false,
            insertable = false
    )
    private Short idRegion;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(
            name = "id_region",
            nullable = false,
            updatable = false
    )
    private RegionDao region;
    @Column(name = "lb_name", length = 50, updatable = false)
    private String name;
    @Column(name = "ts_start_date")
    private Instant startDate;
    @Column(name = "ts_end_date")
    private Instant endDate;

    @ManyToMany(
            cascade = {CascadeType.PERSIST, CascadeType.MERGE},
            fetch = FetchType.EAGER
    )
    @JoinTable(
            name = "lk_mk_season_period",
            joinColumns = @JoinColumn(
                    name = "id_tec_mk_season",
                    referencedColumnName = "id_tec_mk_season"
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "id_tec_mk_period",
                    referencedColumnName = "id_tec_mk_period"
            )
    )
    private Set<MkPeriod> periods = new HashSet<>();

    public void addMkPeriod(MkPeriod period) {
        periods.add(period);
        period.addSeason(this);
    }

    // TODO le hassh et equals doivent etre sur le cle unique non genere

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "idTech = " + idTech + ", " +
                "idMkSeason = " + idMkSeason + ", " +
                "idRegion = " + idRegion + ")";
    }
}
