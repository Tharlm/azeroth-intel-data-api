package fr.bavencoff.wow.azerothinteldataapi.db.postaze.playableclass.dao;

import fr.bavencoff.wow.azerothinteldataapi.db.postaze.playablerace.dao.PlayableRaceDao;
import fr.bavencoff.wow.azerothinteldataapi.db.postaze.playablespe.dao.PlayableSpecializationDao;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.proxy.HibernateProxy;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@NoArgsConstructor
@Entity
@Table(name = "tb_playable_class")
@Getter
@Setter
public class PlayableClassDao {

    @Id
    @Column(name = "id", nullable = false)
    private Integer id;
    @Column(name = "lb_name", length = 50, nullable = false)
    @NotNull
    private String name;
    @ManyToMany(mappedBy = "classes", cascade = CascadeType.MERGE)
    private Set<PlayableRaceDao> races = new HashSet<>();
    @OneToMany(mappedBy = "classDao", cascade = CascadeType.ALL)
    private Set<PlayableSpecializationDao> specializations = new HashSet<>();

    public void addPlayableRace(PlayableRaceDao race) {
        this.races.add(race);
        race.getClasses().add(this);
    }

    public void addPlayableSpecialization(PlayableSpecializationDao specialization) {
        this.specializations.add(specialization);
        specialization.setClassDao(this);
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        PlayableClassDao that = (PlayableClassDao) o;
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
