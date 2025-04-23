package fr.bavencoff.wow.azerothinteldataapi.db.postaze.realmsview.dao;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.proxy.HibernateProxy;

import java.util.Objects;

@Table(name = "vw_realms")
@Entity
@NoArgsConstructor
@Getter
@Setter
public class RealmView {

    @Id
    @Column(name = "id")
    private Integer id;

    @Size(max = 50)
    @Column(name = "realm_name", length = 50)
    private String realmName;

    @Size(max = 30)
    @Column(name = "realm_category", length = 30)
    private String realmCategory;

    @Size(max = 10)
    @Column(name = "realm_locale", length = 10)
    private String realmLocale;

    @Size(max = 25)
    @Column(name = "realm_timezone", length = 25)
    private String realmTimezone;

    @Size(max = 50)
    @Column(name = "realm_slug", length = 50)
    private String realmSlug;

    @Column(name = "bo_tournament")
    private boolean boTournament;

    @Column(name = "realm_id_type")
    private Integer realmIdType;

    @Size(max = 3)
    @Column(name = "realm_type_key", length = 3)
    private String realmTypeKey;

    @Size(max = 50)
    @Column(name = "realm_type_name", length = 50)
    private String realmTypeName;

    @Size(max = 50)
    @Column(name = "realm_type_type", length = 50)
    private String realmTypeType;

    @Column(name = "connected_realm_id")
    private Integer idConnectedRealm;

    @Column(name = "bo_queue")
    private boolean boQueue;

    @Column(name = "id_param_population")
    private Integer idParamPopulation;

    @Size(max = 3)
    @Column(name = "population_key", length = 3)
    private String populationKey;

    @Size(max = 50)
    @Column(name = "population_name", length = 50)
    private String populationName;

    @Size(max = 50)
    @Column(name = "population_type", length = 50)
    private String populationType;

    @Column(name = "id_param_status")
    private Integer idParamStatus;

    @Size(max = 3)
    @Column(name = "status_key", length = 3)
    private String statusKey;

    @Size(max = 50)
    @Column(name = "status_name", length = 50)
    private String statusName;

    @Size(max = 50)
    @Column(name = "status_type", length = 50)
    private String statusType;

    @Column(name = "id_region")
    private Short idRegion;

    @Size(max = 50)
    @Column(name = "region_name", length = 50)
    private String regionName;

    @Size(max = 10)
    @Column(name = "region_tag", length = 10)
    private String regionTag;

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        RealmView realmView = (RealmView) o;
        return getId() != null && Objects.equals(getId(), realmView.getId());
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
