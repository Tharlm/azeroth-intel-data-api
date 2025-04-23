package fr.bavencoff.wow.azerothinteldataapi.db.postaze.lkmkseasonperiod.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LkMkSeasonPeriodKey implements Serializable {
    @Column(name = "id_tec_mk_season")
    private Integer idTecMkSeasson;

    @Column(name = "id_tec_mk_period")
    private Integer idTecMkPeriod;
}
