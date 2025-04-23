package fr.bavencoff.wow.azerothinteldataapi.common.exceptions.achievements;

import lombok.Getter;

@Getter
public class AchievementCategoryAlreadyExistingException extends RuntimeException {
    public static final String MESSAGE = "Achievement Category with idAchievementCatergory %d already exists.";
    private final Integer idAchievementCatergory;

    public AchievementCategoryAlreadyExistingException(Integer idAchievementCatergory) {
        this(idAchievementCatergory, String.format(MESSAGE, idAchievementCatergory));
    }

    public AchievementCategoryAlreadyExistingException(Integer idAchievementCatergory, String message) {
        this(idAchievementCatergory, message, null);
    }

    public AchievementCategoryAlreadyExistingException(Integer idAchievementCatergory, String message, Throwable cause) {
        super(message, cause);
        this.idAchievementCatergory = idAchievementCatergory;
    }
}
