package fr.bavencoff.wow.azerothinteldataapi.db.postaze.parameters.dao;

import fr.bavencoff.wow.azerothinteldataapi.common.enums.KeyParameterType;
import fr.bavencoff.wow.azerothinteldataapi.helpers.parameters.impl.ParametersHelper;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.proxy.HibernateProxy;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

@NoArgsConstructor
@Entity
@Table(name = "tb_parameter_type", uniqueConstraints = {
        @UniqueConstraint(name = "tb_parameter_type_lb_type_lb_key_uindex", columnNames = {"lb_key", "lb_type"})
})
@Getter
@Setter
public class ParameterTypeDao implements Serializable {
    @Serial
    private static final long serialVersionUID = -3245205040198374149L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Column(name = "lb_key", nullable = false, length = ParametersHelper.PARAMETER_KEY_MAX_LENGTH)
    @Enumerated(EnumType.STRING)
    private KeyParameterType key;
    @Column(name = "lb_type", nullable = false, length = ParametersHelper.PARAMETER_TYPE_MAX_LENGTH)
    @NotBlank
    private String type;
    @Column(name = "lb_name", length = ParametersHelper.PARAMETER_NAME_MAX_LENGTH)
    private String name;

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        ParameterTypeDao that = (ParameterTypeDao) o;
        return getKey() != null && Objects.equals(getKey(), that.getKey()) && getType() != null && Objects.equals(getType(), that.getType());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "key = " + key + ", " +
                "type = " + type + ")";
    }
}
