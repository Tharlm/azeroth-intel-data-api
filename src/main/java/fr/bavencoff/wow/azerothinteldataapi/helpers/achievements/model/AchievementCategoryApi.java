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
public class AchievementCategoryApi {
    private Integer id;
    private String name;
    private boolean isGuildCategory;
    private Integer displayOrder;
    private Integer quantityAlliance;
    private Integer pointAlliance;
    private Integer quantityHorde;
    private Integer pointHorde;
//    private List<AchievementInformation> achievements = new ArrayList<>();
}

