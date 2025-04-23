package fr.bavencoff.wow.azerothinteldataapi.helpers.achievements.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class AchievementApi {
    private Integer id;
    private String name;
    private String description;
    private Integer points;
    private boolean isAccountWide;
    private Integer displayOrder;
    private String rewardDescription;
}
