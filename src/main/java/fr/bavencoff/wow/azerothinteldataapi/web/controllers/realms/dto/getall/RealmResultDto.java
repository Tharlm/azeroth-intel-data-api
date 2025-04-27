package fr.bavencoff.wow.azerothinteldataapi.web.controllers.realms.dto.getall;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RealmResultDto {
    private Integer id;
    private String name;
    private String category;
    private String region;
}
