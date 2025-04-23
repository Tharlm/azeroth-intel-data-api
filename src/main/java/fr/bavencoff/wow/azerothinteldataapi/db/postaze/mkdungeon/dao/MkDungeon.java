package fr.bavencoff.wow.azerothinteldataapi.db.postaze.mkdungeon.dao;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.proxy.HibernateProxy;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "tb_mk_dungeon")
public class MkDungeon implements Serializable {

    @Serial
    private static final long serialVersionUID = -951109222262028854L;
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;
    // TODO faire le lien quand la table des donjons existera
    @NotNull
    @Column(name = "id_dungeon", nullable = false)
    private Integer idDungeon;
    @Column(name = "lb_name", length = 50)
    private String name;
    @Column(name = "bo_tracked", nullable = false)
    private boolean isTracked;

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        MkDungeon mkDungeon = (MkDungeon) o;
        return getId() != null && Objects.equals(getId(), mkDungeon.getId());
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
