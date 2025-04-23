package fr.bavencoff.wow.azerothinteldataapi.db.postaze.playablerace.dao;

import fr.bavencoff.wow.azerothinteldataapi.db.postaze.parameters.dao.ParameterTypeDao;
import fr.bavencoff.wow.azerothinteldataapi.db.postaze.playableclass.dao.PlayableClassDao;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedAttributeNode;
import jakarta.persistence.NamedEntityGraph;
import jakarta.persistence.NamedSubgraph;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.proxy.HibernateProxy;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;


@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "tb_playable_race")
@NamedEntityGraph(
        name = "get-playable-race-entity-graph",
        attributeNodes = {
                @NamedAttributeNode("faction"),
                @NamedAttributeNode(value = "classes", subgraph = "classes-sub-graph")
        },
        subgraphs = {
                @NamedSubgraph(
                        name = "classes-sub-graph",
                        attributeNodes = {
                        }
                )
        }
)
public class PlayableRaceDao {
    @Id
    @Column(name = "id", nullable = false, updatable = false)
    private Integer id;
    @Column(name = "lb_name", length = 50, updatable = false, nullable = false)
    private String name;
    @ManyToOne(
            fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH}
    )
    @JoinColumn(name = "id_type_faction", nullable = false, updatable = false)
    private ParameterTypeDao faction;
    @Column(name = "bo_selectable", nullable = false, updatable = false)
    private boolean isSelectable;
    @Column(name = "bo_allied_race", nullable = false, updatable = false)
    private boolean isAlliedRace;
    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(
            name = "lk_race_class",
            joinColumns = @JoinColumn(
                    name = "id_race",
                    referencedColumnName = "id"
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "id_class",
                    referencedColumnName = "id"
            )
    )
    private Set<PlayableClassDao> classes = new HashSet<>();

    public void addPlayableClass(PlayableClassDao playableClass) {
        classes.add(playableClass);
        playableClass.addPlayableRace(this);
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        PlayableRaceDao that = (PlayableRaceDao) o;
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
                "name = " + name + ")";
    }
}
