package fr.bavencoff.wow.azerothinteldataapi.db.postaze.realms.dao;

import fr.bavencoff.wow.azerothinteldataapi.db.postaze.connectedrealms.dao.ConnectedRealmDao;
import fr.bavencoff.wow.azerothinteldataapi.db.postaze.parameters.dao.ParameterTypeDao;
import fr.bavencoff.wow.azerothinteldataapi.db.postaze.realms.impl.RealmHelper;
import fr.bavencoff.wow.azerothinteldataapi.db.postaze.region.dao.RegionDao;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedAttributeNode;
import jakarta.persistence.NamedEntityGraph;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.proxy.HibernateProxy;

import java.io.Serial;
import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(
        name = "tb_realm",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "tb_realm_lb_slug_id_region_uindex",
                        columnNames = {
                                "lb_slug", "id_region"
                        }
                )
        }
)
@Getter
@Setter
@Builder
@NamedEntityGraph(
        name = "realm-entity-graph",
        attributeNodes = {
                @NamedAttributeNode("type"),
                @NamedAttributeNode("region"),
                @NamedAttributeNode("connectedRealm")
        }
)
public class RealmDao implements Serializable {
    @Serial
    private static final long serialVersionUID = 6767869386354231607L;
    @Id
    @Column(name = "id", nullable = false, updatable = false)
    private Integer id;
    @ManyToOne(fetch = FetchType.LAZY, optional = false, cascade = CascadeType.ALL)
    @JoinColumn(
            name = "id_region",
            nullable = false,
            updatable = false
    )
    @NotNull
    private RegionDao region;
    @Column(name = "lb_name", length = RealmHelper.REALM_NAME_MAX_LENGTH)
    @Size(max = RealmHelper.REALM_NAME_MAX_LENGTH)
    private String name;
    @Column(name = "lb_category", length = RealmHelper.REALM_CATEGORY_MAX_LENGTH)
    @Size(max = RealmHelper.REALM_CATEGORY_MAX_LENGTH)
    private String category;
    @Column(name = "lb_locale", length = RealmHelper.REALM_LOCALE_MAX_LENGTH)
    @Size(max = RealmHelper.REALM_LOCALE_MAX_LENGTH)
    private String locale;
    @Column(name = "lb_timezone", length = RealmHelper.REALM_TIMEZONE_MAX_LENGTH)
    @Size(max = RealmHelper.REALM_TIMEZONE_MAX_LENGTH)
    private String timezone;
    @Column(name = "lb_slug", nullable = false, length = RealmHelper.REALM_SLUG_MAX_LENGTH)
    @Size(max = RealmHelper.REALM_SLUG_MAX_LENGTH)
    private String slug;
    @Column(name = "bo_tournament", nullable = false)
    private boolean isTournament;
    @Column(name = "bo_active", nullable = false)
    private boolean isActive;
    @Column(name = "dt_last_update", nullable = false)
    @NotNull
    private Instant dateLastUpdate;
    @ManyToOne(
            cascade = CascadeType.ALL
    )
    @JoinColumn(name = "id_param")
    private ParameterTypeDao type;

    @ManyToOne()
    @JoinColumn(name = "id_connected_realm")
    private ConnectedRealmDao connectedRealm;

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        RealmDao realmDao = (RealmDao) o;
        return getId() != null && Objects.equals(getId(), realmDao.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "connectedRealm = " + connectedRealm + ", " +
                "name = " + name + ")";
    }
}
