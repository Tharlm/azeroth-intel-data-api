package fr.bavencoff.wow.azerothinteldataapi.helpers.mythickeys.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class MkPeriodApi {
    private Integer idTech;
    private Integer idMkPeriod;
    private Instant startDate;
    private Instant endDate;
}
