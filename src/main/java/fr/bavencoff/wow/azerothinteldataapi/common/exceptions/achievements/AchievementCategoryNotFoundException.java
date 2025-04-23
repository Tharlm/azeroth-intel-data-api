package fr.bavencoff.wow.azerothinteldataapi.common.exceptions.achievements;

import lombok.Getter;

@Getter
public class AchievementCategoryNotFoundException extends RuntimeException {
    public static final String MESSAGE = "Achievement Category with id %d does not exists.";
    private final Integer idAchievementCatergory;

    public AchievementCategoryNotFoundException(Integer idAchievementCatergory) {
        this(idAchievementCatergory, String.format(MESSAGE, idAchievementCatergory));
    }

    public AchievementCategoryNotFoundException(Integer idAchievementCatergory, String message) {
        this(idAchievementCatergory, message, null);
    }

    public AchievementCategoryNotFoundException(Integer idAchievementCatergory, String message, Throwable cause) {
        super(message, cause);
        this.idAchievementCatergory = idAchievementCatergory;
    }

}
