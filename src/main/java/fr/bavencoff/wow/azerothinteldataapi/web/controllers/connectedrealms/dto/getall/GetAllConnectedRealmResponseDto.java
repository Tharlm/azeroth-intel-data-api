package fr.bavencoff.wow.azerothinteldataapi.web.controllers.connectedrealms.dto.getall;

import fr.bavencoff.wow.azerothinteldataapi.common.enums.GlobalRegion;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class GetAllConnectedRealmResponseDto {
    private List<GetAllConnectedRealmResultDto> results = new ArrayList<>();

    @Getter
    @Setter
    @NoArgsConstructor
    public static class GetAllConnectedRealmResultDto {
        private Integer id;
        private boolean queue;
        private String population;
        private String status;
        private GlobalRegion region;
        private List<GetAllConnectedRealmRealmDto> realms;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    public static class GetAllConnectedRealmRealmDto {
        private Integer id;
        private String name;
        private String slug;
        private GlobalRegion region;
    }
}
