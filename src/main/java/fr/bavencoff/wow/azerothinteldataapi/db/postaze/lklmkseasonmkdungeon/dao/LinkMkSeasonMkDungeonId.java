package fr.bavencoff.wow.azerothinteldataapi.db.postaze.lklmkseasonmkdungeon.dao;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@Embeddable
public class LinkMkSeasonMkDungeonId implements Serializable {
    @Serial
    private static final long serialVersionUID = 1143613409183990791L;
    @NotNull
    @Column(name = "id_mk_season", nullable = false)
    private Integer idMkSeason;

    @NotNull
    @Column(name = "id_mk_dungeon", nullable = false)
    private Integer idMkDungeon;

    @NotNull
    @Column(name = "id_region", nullable = false)
    private Short idRegion;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        LinkMkSeasonMkDungeonId entity = (LinkMkSeasonMkDungeonId) o;
        return Objects.equals(this.idMkSeason, entity.idMkSeason) &&
                Objects.equals(this.idRegion, entity.idRegion) &&
                Objects.equals(this.idMkDungeon, entity.idMkDungeon);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idMkSeason, idRegion, idMkDungeon);
    }

}
