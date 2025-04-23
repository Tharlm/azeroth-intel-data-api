package fr.bavencoff.wow.azerothinteldataapi.db.postaze.items.dao;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.NamedAttributeNode;
import jakarta.persistence.NamedEntityGraph;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.proxy.HibernateProxy;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@Entity
@Table(name = "tb_item_subclass", schema = "public")
@NamedEntityGraph(
        name = "get-item-subclass-graph",
        attributeNodes = {
                @NamedAttributeNode("itemClass")
        }
)
public class ItemSubclass implements Serializable {
    @Serial
    private static final long serialVersionUID = -2115690688042975647L;
    @EmbeddedId
    private ItemSubclassId id;

    @MapsId("idClass")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_class", nullable = false)
    private ItemClass itemClass;

    @Size(max = 100)
    @NotNull
    @Column(name = "lb_display_name", nullable = false, length = 100)
    private String displayName;

    @Size(max = 100)
    @Column(name = "lb_verbose_name", length = 100)
    private String verboseName;

    @NotNull
    @ColumnDefault("false")
    @Column(name = "bo_hide_subclass_in_tooltips", nullable = false)
    private Boolean hideSubclassInTooltips = false;

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        ItemSubclass that = (ItemSubclass) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public final int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "EmbeddedId = " + id + ", " +
                "lbDisplayName = " + displayName + ")";
    }
}
