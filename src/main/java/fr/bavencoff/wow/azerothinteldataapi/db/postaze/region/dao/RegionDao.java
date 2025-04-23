package fr.bavencoff.wow.azerothinteldataapi.db.postaze.region.dao;

import fr.bavencoff.wow.azerothinteldataapi.common.enums.GlobalRegion;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.proxy.HibernateProxy;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "tb_region")
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegionDao implements Serializable {
    @Serial
    private static final long serialVersionUID = -3361526619949018946L;
    @Id
    @Column(name = "id", nullable = false)
    private Short id;
    @Column(
            name = "lb_name",
            nullable = false,
            length = 50
    )
    private String name;
    @Column(name = "lb_tag", length = 10)
    @Enumerated(EnumType.STRING)
    private GlobalRegion tag;

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        RegionDao regionDao = (RegionDao) o;
        return getId() != null && Objects.equals(getId(), regionDao.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }

}
