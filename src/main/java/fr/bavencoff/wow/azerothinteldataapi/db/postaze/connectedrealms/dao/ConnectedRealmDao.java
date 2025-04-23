package fr.bavencoff.wow.azerothinteldataapi.db.postaze.connectedrealms.dao;

import fr.bavencoff.wow.azerothinteldataapi.db.postaze.parameters.dao.ParameterTypeDao;
import fr.bavencoff.wow.azerothinteldataapi.db.postaze.realms.dao.RealmDao;
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
import jakarta.persistence.NamedSubgraph;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.proxy.HibernateProxy;

import java.io.Serial;
import java.io.Serializable;
import java.time.Instant;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;


@NamedEntityGraph(
        name = "cr-entity-graph",
        attributeNodes = {
                @NamedAttributeNode("population"),
                @NamedAttributeNode("status"),
                @NamedAttributeNode("region"),
                @NamedAttributeNode(value = "realms", subgraph = "realm-region")
        },
        subgraphs = {
                @NamedSubgraph(
                        name = "realm-region",
                        attributeNodes = {
                                @NamedAttributeNode("region"),
                                @NamedAttributeNode("type")
                        }
                )
        }
)
@Entity
@Table(name = "tb_connected_realm")
@Getter
@Setter
@NoArgsConstructor
public class ConnectedRealmDao implements Serializable {

    @Serial
    private static final long serialVersionUID = -4343329658923983942L;
    @Id
    @Column(name = "id", nullable = false, updatable = false)
    private Integer id;
    @Column(name = "bo_queue")
    private boolean queue;
    @ManyToOne(fetch = FetchType.LAZY, optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_param_population", nullable = false)
    private ParameterTypeDao population;
    @ManyToOne(fetch = FetchType.LAZY, optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_param_status", nullable = false)
    private ParameterTypeDao status;
    @ManyToOne(fetch = FetchType.LAZY, optional = false, cascade = CascadeType.ALL)
    @JoinColumn(
            name = "id_region",
            nullable = false,
            updatable = false
    )
    private RegionDao region;
    @Column(name = "bo_active", nullable = false)
    private boolean active;
    @Column(name = "dt_last_update", nullable = false)
    private Instant dateLastUpdate;
    @OneToMany(mappedBy = "connectedRealm")
    private Set<RealmDao> realms = new LinkedHashSet<>();


    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        ConnectedRealmDao that = (ConnectedRealmDao) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ")";
    }
}
