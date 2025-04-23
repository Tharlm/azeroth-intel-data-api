package fr.bavencoff.wow.azerothinteldataapi.db.postaze.items.dao;

import fr.bavencoff.wow.azerothinteldataapi.db.postaze.parameters.dao.ParameterTypeDao;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinColumns;
import jakarta.persistence.ManyToOne;
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
@Table(name = "tb_item", schema = "public")
@NamedEntityGraph(
        name = "get-item-graph",
        attributeNodes = {
                @NamedAttributeNode("quality"),
                @NamedAttributeNode("inventory"),
                @NamedAttributeNode("itemClass"),
                @NamedAttributeNode("itemSubclass")
        }
)
public class ItemDao implements Serializable {
    @Serial
    private static final long serialVersionUID = 27970007605737576L;

    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Size(max = 100)
    @NotNull
    @Column(name = "lb_name", nullable = false, length = 100)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_param_quality")
    private ParameterTypeDao quality;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_param_inventory")
    private ParameterTypeDao inventory;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_item_class", insertable = false, updatable = false)
    private ItemClass itemClass;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({
            @JoinColumn(name = "id_item_class", referencedColumnName = "id_class"),
            @JoinColumn(name = "id_item_subclass", referencedColumnName = "id"),
    })
    private ItemSubclass itemSubclass;

    @Column(name = "nb_level")
    private Short level;

    @Column(name = "nb_required_level")
    private Short requiredLevel;

    @Column(name = "nb_purchase_price")
    private Integer purchasePrice;

    @Column(name = "nb_sell_price")
    private Integer sellPrice;

    @Column(name = "nb_max_count")
    private Short maxCount;

    @Column(name = "nb_purchase_quantity")
    private Short purchaseQuantity;

    @NotNull
    @ColumnDefault("false")
    @Column(name = "bo_equippable", nullable = false)
    private Boolean equippable = false;

    @NotNull
    @ColumnDefault("false")
    @Column(name = "bo_stackable", nullable = false)
    private Boolean stackable = false;

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        ItemDao itemDao = (ItemDao) o;
        return getId() != null && Objects.equals(getId(), itemDao.getId());
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
