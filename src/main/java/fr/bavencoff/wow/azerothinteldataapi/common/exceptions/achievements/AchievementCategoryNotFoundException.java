package fr.bavencoff.wow.azerothinteldataapi.common.exceptions.achievements;

import lombok.Getter;

@Getter
public class AchievementCategoryNotFoundException extends RuntimeException {
    public static final String MESSAGE = "Achievement Category with id %d does not exists.";
    private final Integer idAchievementCategory;

    public AchievementCategoryNotFoundException(Integer idAchievementCategory) {
        this(idAchievementCategory, String.format(MESSAGE, idAchievementCategory));
    }

    public AchievementCategoryNotFoundException(Integer idAchievementCategory, String message) {
        this(idAchievementCategory, message, null);
    }

    public AchievementCategoryNotFoundException(Integer idAchievementCategory, String message, Throwable cause) {
        super(message, cause);
        this.idAchievementCategory = idAchievementCategory;
    }

}
