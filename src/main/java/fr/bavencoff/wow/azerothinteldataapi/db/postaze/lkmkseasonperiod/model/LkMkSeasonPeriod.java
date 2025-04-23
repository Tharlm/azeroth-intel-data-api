package fr.bavencoff.wow.azerothinteldataapi.db.postaze.lkmkseasonperiod.model;

import fr.bavencoff.wow.azerothinteldataapi.db.postaze.mkperiod.dao.MkPeriod;
import fr.bavencoff.wow.azerothinteldataapi.db.postaze.mkseason.dao.MkSeason;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "lk_mk_season_period")
@NoArgsConstructor
@AllArgsConstructor
public class LkMkSeasonPeriod {

    @EmbeddedId
    private LkMkSeasonPeriodKey id = new LkMkSeasonPeriodKey();

    @NotNull
    @MapsId("idTecMkSeasson")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_tec_mk_season", nullable = false)
    private MkSeason mkSeason;

    @NotNull
    @MapsId("idTecMkPeriod")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_tec_mk_period", nullable = false)
    private MkPeriod mkPeriod;

}
