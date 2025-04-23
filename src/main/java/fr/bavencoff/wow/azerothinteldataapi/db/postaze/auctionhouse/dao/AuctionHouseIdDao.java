package fr.bavencoff.wow.azerothinteldataapi.db.postaze.auctionhouse.dao;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.proxy.HibernateProxy;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

@NoArgsConstructor
@Getter
@Setter
@Embeddable
public class AuctionHouseIdDao implements Serializable {

    @Serial
    private static final long serialVersionUID = 4080627800449161242L;

    @NotNull
    @Column(name = "id", nullable = false)
    private Long naturalId;
    @NotNull
    @Column(name = "id_region", nullable = false)
    private Short idRegion;

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        AuctionHouseIdDao that = (AuctionHouseIdDao) o;
        return getNaturalId() != null && Objects.equals(getNaturalId(), that.getNaturalId())
                && getIdRegion() != null && Objects.equals(getIdRegion(), that.getIdRegion());
    }

    @Override
    public final int hashCode() {
        return Objects.hash(naturalId, idRegion);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "naturalId = " + naturalId + ", " +
                "idRegion = " + idRegion + ")";
    }
}
