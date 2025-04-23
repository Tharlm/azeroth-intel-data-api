package fr.bavencoff.wow.azerothinteldataapi.db.postaze.items.dao;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.NamedAttributeNode;
import jakarta.persistence.NamedEntityGraph;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.proxy.HibernateProxy;

import java.io.Serial;
import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "tb_item_class", schema = "public")
@NamedEntityGraph(
        name = "get-item-class-graph",
        attributeNodes = {
                @NamedAttributeNode("itemSubclasses")
        }
)
public class ItemClass implements Serializable {
    @Serial
    private static final long serialVersionUID = 5903165346639600506L;
    @Id
    @Column(name = "id", nullable = false)
    private Short id;

    @Size(max = 100)
    @NotNull
    @Column(name = "lb_name", nullable = false, length = 100)
    private String name;

    @OneToMany(mappedBy = "itemClass", cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private Set<ItemSubclass> itemSubclasses = new LinkedHashSet<>();

    public void addSubclass(ItemSubclass itemSubclass) {
        if (itemSubclass == null) {
            return;
        }

        if (this.itemSubclasses == null) {
            this.itemSubclasses = new LinkedHashSet<>();
        }

        itemSubclasses.add(itemSubclass);
        itemSubclass.setItemClass(this);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "name = " + name + ")";
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        ItemClass itemClass = (ItemClass) o;
        return getId() != null && Objects.equals(getId(), itemClass.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}
