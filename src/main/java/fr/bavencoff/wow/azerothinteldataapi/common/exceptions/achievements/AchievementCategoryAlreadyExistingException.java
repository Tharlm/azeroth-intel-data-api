package fr.bavencoff.wow.azerothinteldataapi.common.exceptions.achievements;

import lombok.Getter;

@Getter
public class AchievementCategoryAlreadyExistingException extends RuntimeException {
    public static final String MESSAGE = "Achievement Category with idAchievementCategory %d already exists.";
    private final Integer idAchievementCategory;

    public AchievementCategoryAlreadyExistingException(Integer idAchievementCategory) {
        this(idAchievementCategory, String.format(MESSAGE, idAchievementCategory));
    }

    public AchievementCategoryAlreadyExistingException(Integer idAchievementCategory, String message) {
        this(idAchievementCategory, message, null);
    }

    public AchievementCategoryAlreadyExistingException(Integer idAchievementCategory, String message, Throwable cause) {
        super(message, cause);
        this.idAchievementCategory = idAchievementCategory;
    }
}
