package fr.bavencoff.wow.azerothinteldataapi.db.postaze.items.dao;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.Hibernate;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class ItemSubclassId implements Serializable {
    @Serial
    private static final long serialVersionUID = -1231078266465290773L;
    @NotNull
    @Column(name = "id", nullable = false)
    private Short id;

    @NotNull
    @Column(name = "id_class", nullable = false)
    private Short idClass;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        ItemSubclassId entity = (ItemSubclassId) o;
        return Objects.equals(this.idClass, entity.idClass) &&
                Objects.equals(this.id, entity.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idClass, id);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "idClass = " + idClass + ")";
    }
}
