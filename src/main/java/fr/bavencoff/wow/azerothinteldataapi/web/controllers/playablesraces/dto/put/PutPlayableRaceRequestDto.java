package fr.bavencoff.wow.azerothinteldataapi.web.controllers.playablesraces.dto.put;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class PutPlayableRaceRequestDto {
    @Size(max = 50)
    @NotBlank
    private String name;
    private boolean selectable;
    private boolean alliedRace;
    @Size(max = 50)
    @NotBlank
    private String factionType;
    @Size(max = 50)
    private String factionLabel;
    private List<@Valid PutPlayableRaceClassRequestDto> classes;

    @Getter
    @Setter
    @NoArgsConstructor
    public static class PutPlayableRaceClassRequestDto {
        @NotNull
        @Min(0)
        @Max(Integer.MAX_VALUE)
        private Integer id;
        @Size(max = 50)
        @NotBlank
        private String name;
    }
}
