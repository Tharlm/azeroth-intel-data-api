package fr.bavencoff.wow.azerothinteldataapi.helpers.mythickeys.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class MkLeaderboardApi {
    private Integer idKeystoneAffixe1;
    private Integer startLevelAffixe1;
    private Integer idKeystoneAffixe2;
    private Integer startLevelAffixe2;
    private Integer idKeystoneAffixe3;
    private Integer startLevelAffixe3;
    private List<GroupInformatiom> groups = new ArrayList<>();

    @Getter
    @Setter
    @NoArgsConstructor
    @ToString
    public static class GroupInformatiom {
        private Integer duration;
        private Integer ranking;
        private Integer keyLevel;
        private Instant dateCompleted;
        private List<MemberInformatiom> members = new ArrayList<>();
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @ToString
    public static class MemberInformatiom {
        private Integer idPlayer;
        private String namePlayer;
        private String faction;
        private Integer idRealm;
        private Integer idSpecialization;
    }
}
