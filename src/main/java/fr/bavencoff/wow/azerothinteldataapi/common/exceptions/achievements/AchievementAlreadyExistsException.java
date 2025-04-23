package fr.bavencoff.wow.azerothinteldataapi.common.exceptions.achievements;

import lombok.Getter;

@Getter
public class AchievementAlreadyExistsException extends RuntimeException {
    public static final String MESSAGE = "Achievement with idAchievement %d already exists.";
    private final Integer idAchievement;

    public AchievementAlreadyExistsException(Integer idAchievement) {
        this(idAchievement, String.format(MESSAGE, idAchievement));
    }

    public AchievementAlreadyExistsException(Integer idAchievement, String message) {
        this(idAchievement, message, null);
    }

    public AchievementAlreadyExistsException(Integer idAchievement, String message, Throwable cause) {
        super(message, cause);
        this.idAchievement = idAchievement;
    }
}
