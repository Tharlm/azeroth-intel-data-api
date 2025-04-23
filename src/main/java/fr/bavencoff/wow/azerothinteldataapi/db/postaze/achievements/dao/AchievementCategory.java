package fr.bavencoff.wow.azerothinteldataapi.db.postaze.achievements.dao;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.proxy.HibernateProxy;

import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "tb_achievement_category")
public class AchievementCategory {
    @Id
    @NotNull
    @Column(name = "id", nullable = false, updatable = false)
    private Integer id;

    @NotNull
    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "bo_guild_category", nullable = false)
    private boolean isGuildCategory;

    @Column(name = "nb_display_order")
    private Integer displayOrder;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_parent_category", referencedColumnName = "id")
    private AchievementCategory parentCategory;

    @Column(name = "nb_qtt_alliance")
    private Integer quantityAlliance;

    @Column(name = "nb_point_alliance")
    private Integer pointAlliance;

    @Column(name = "nb_qtt_horde")
    private Integer quantityHorde;

    @Column(name = "nb_point_horde")
    private Integer pointHorde;

    @OneToMany(mappedBy = "achievementCategory", cascade = CascadeType.ALL)
    private Set<Achievement> achievements = new LinkedHashSet<>();

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        AchievementCategory that = (AchievementCategory) o;
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
