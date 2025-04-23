package fr.bavencoff.wow.azerothinteldataapi.helpers.mythickeys.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class MkSeasonApi {
    private Integer idTech;
    private Integer idMkSeason;
    private String name;
    private Instant startDate;
    private Instant endDate;
    private Set<MkPeriodApi> periodInformations = new HashSet<>();
}
