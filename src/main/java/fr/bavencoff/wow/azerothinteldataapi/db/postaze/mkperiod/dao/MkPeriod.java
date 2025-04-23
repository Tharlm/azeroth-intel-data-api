package fr.bavencoff.wow.azerothinteldataapi.db.postaze.mkperiod.dao;

import fr.bavencoff.wow.azerothinteldataapi.db.postaze.mkseason.dao.MkSeason;
import fr.bavencoff.wow.azerothinteldataapi.db.postaze.region.dao.RegionDao;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedAttributeNode;
import jakarta.persistence.NamedEntityGraph;
import jakarta.persistence.NamedSubgraph;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.proxy.HibernateProxy;

import java.io.Serial;
import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Entité liée à la table tb_mk_period.
 * <p>
 * Les ids naturels sont idMkPeriod et la région.
 * Il peut y avoir la même idMkPeriod pour plusieurs régions.
 * L'ID tech unifie tout ca.
 */
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(
        name = "tb_mk_period",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "tb_mk_period_id_region_id_mk_period_uindex",
                        columnNames = {"id_region", "id_mk_period"}
                )
        }
)
@Getter
@Setter
@NamedEntityGraph(
        name = "get-period-entity-graph",
        attributeNodes = {
                @NamedAttributeNode("region"),
                @NamedAttributeNode(value = "seasons", subgraph = "seasons-sub-graph")
        },
        subgraphs = {
                @NamedSubgraph(
                        name = "seasons-sub-graph",
                        attributeNodes = {
                                @NamedAttributeNode("region")
                        }
                )
        }
)
public class MkPeriod implements Serializable {
    @Serial
    private static final long serialVersionUID = -3664764416264092318L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tec_mk_period", nullable = false, updatable = false)
    private Integer idTech;
    @Column(
            name = "id_mk_period",
            updatable = false,
            nullable = false
    )
    private Integer idMkPeriod;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(
            name = "id_region",
            nullable = false,
            updatable = false
    )
    private RegionDao region;
    @Column(name = "ts_start_date")
    private Instant startDate;
    @Column(name = "ts_end_date")
    private Instant endDate;

    @ManyToMany(
            mappedBy = "periods",
            cascade = {CascadeType.MERGE},
            fetch = FetchType.EAGER
    )
    private Set<MkSeason> seasons = new HashSet<>();

    public void addSeason(MkSeason season) {
        this.seasons.add(season);
        season.getPeriods().add(this);
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        MkPeriod mkPeriod = (MkPeriod) o;
        // TODO revoir pourrait causer des pbs
        return getIdTech() != null && Objects.equals(getIdTech(), mkPeriod.getIdTech());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "idTech = " + idTech + ", " +
                "idMkPeriod = " + idMkPeriod + ")";
    }
}
