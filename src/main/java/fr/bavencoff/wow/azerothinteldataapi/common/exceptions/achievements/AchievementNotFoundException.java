package fr.bavencoff.wow.azerothinteldataapi.common.exceptions.achievements;

import lombok.Getter;

@Getter
public class AchievementNotFoundException extends RuntimeException {
    public static final String MESSAGE = "Achievement with id %d does not exists.";
    private final Integer idAchievement;

    public AchievementNotFoundException(Integer idAchievement) {
        this(idAchievement, String.format(MESSAGE, idAchievement));
    }

    public AchievementNotFoundException(Integer idAchievement, String message) {
        this(idAchievement, message, null);
    }

    public AchievementNotFoundException(Integer idAchievement, String message, Throwable cause) {
        super(message, cause);
        this.idAchievement = idAchievement;
    }
}
