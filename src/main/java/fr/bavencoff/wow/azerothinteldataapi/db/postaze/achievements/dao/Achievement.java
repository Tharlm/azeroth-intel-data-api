package fr.bavencoff.wow.azerothinteldataapi.db.postaze.achievements.dao;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.proxy.HibernateProxy;

import java.util.Objects;

@Getter
@Setter
@Entity
@Table(name = "tb_achievement")
public class Achievement {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_achievement_category", nullable = false, referencedColumnName = "id")
    private AchievementCategory achievementCategory;

    @Column(name = "lb_name", length = 100)
    private String name;

    @Column(name = "lb_description", length = 500)
    private String description;

    @Column(name = "nb_points")
    private Integer points;

    @Column(name = "bo_account_wide", nullable = false)
    private boolean isAccountWide;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_previous_achievement", referencedColumnName = "id")
    private Achievement previousAchievement;

    @Column(name = "nb_display_order")
    private Integer displayOrder;

    @Column(name = "lb_reward_description", length = 500)
    private String rewardDescription;


    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        Achievement that = (Achievement) o;
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
